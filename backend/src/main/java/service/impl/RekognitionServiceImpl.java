package service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionAsyncClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import dto.ticket.ProcessedTicket;
import dto.ticket.TicketRequest;
import dto.ingredient.UserIngredientDto;
import service.IngredientService;
import service.RekognitionService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RekognitionServiceImpl implements RekognitionService {

    private final AmazonRekognition rekognitionClient = AmazonRekognitionAsyncClientBuilder
            .standard().withRegion("us-east-1").build();
    private final Logger logger = Logger.getLogger(RekognitionServiceImpl.class.getName());
    private final IngredientService ingredientService = new IngredientServiceImpl();

    public TicketRequest mockRequest() {
        TicketRequest request = new TicketRequest();
        request.setUserId(1L);
        String imagePath = "images/Ticket.jpg";
        try{
            Path path = Paths.get(ClassLoader.getSystemResource(imagePath).toURI());
            byte[] image = Files.readAllBytes(path);
            request.setImage(image);
            return request;
        }catch (IOException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void handleRequest(TicketRequest request) {
        System.out.println("Verifying ticket");
        Image ticket = new Image().withBytes(ByteBuffer.wrap(request.getImage()));

        //imageIsAValidTicket(ticket);
        ProcessedTicket processedTicket = extractInformationFromTicket(ticket);
        processedTicket.setUserId(request.getUserId());

        System.out.println(processedTicket);
    }

    @Override
    public boolean imageIsAValidTicket(Image ticket) {
        DetectLabelsRequest labelRequest = new DetectLabelsRequest()
                .withMaxLabels(5);
        DetectLabelsResult ticketLabelResult = rekognitionClient.detectLabels(labelRequest.withImage(ticket));

        float ticketLabelConfidence = getTicketLabelConfidence(ticketLabelResult.getLabels());

        System.out.println("Ticket label confidence: " + ticketLabelConfidence);
        if (ticketLabelConfidence > 70){
            System.out.println("Valid ticket format");
            return true;
        }
        System.out.println("Invalid ticket format");
        return false;
    }

    private static Float getTicketLabelConfidence(List<Label> labels) {
        for (Label label : labels) {
            if (label.getName().equals("Receipt")) {
                return label.getConfidence();
            }
        }
        return 0.0f;
    }

    @Override
    public ProcessedTicket extractInformationFromTicket(Image ticket) {
        BoundingBox boundingBox = new BoundingBox()
                .withWidth(0.6f) // 60% of the image width
                .withHeight(0.90f) // 85% of the image height (exclude top 15%)
                .withLeft(0.0f) // Starting from the left edge of the image
                .withTop(0.10f);

        RegionOfInterest region = new RegionOfInterest().withBoundingBox(boundingBox);
        DetectTextFilters filters = new DetectTextFilters().withRegionsOfInterest(region);

        DetectTextRequest request = new DetectTextRequest()
                .withImage(ticket)
                .withFilters(filters);

        DetectTextResult result = rekognitionClient.detectText(request);

        String boughDate = extractBoughDateFromResponse(result);
        List <UserIngredientDto> ingredients = extractIngredientsFromResponse(result);

        ProcessedTicket processedTicket = new ProcessedTicket();
        processedTicket.setBoughtDate(boughDate);
        processedTicket.setIngredients(ingredients);

        //System.out.println(processedTicket);
        return processedTicket;
    }



    public List<UserIngredientDto> extractIngredientsFromResponse(DetectTextResult response){
        Predicate<TextDetection> isAValidTextLine = input -> {
            String regex = "\\b\\w*\\p{Lu}{3}\\w*\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input.getDetectedText());
            return matcher.find();
        };
        Predicate<TextDetection> isATextLine = input -> input.getType().equals("LINE");

        return response.getTextDetections()
                .stream()
                .filter(isATextLine)
                .filter(isAValidTextLine)
                .map(TextDetection::getDetectedText)
                .map(UserIngredientDto::new)
                .collect(Collectors.toList());
    }

    public String extractBoughDateFromResponse(DetectTextResult response){
        Predicate<TextDetection> isBoughtDate = input ->
                input.getDetectedText().matches("\\b\\d{1,2}/\\d{1,2}/\\d{2,4}\\b");
        Function<TextDetection,String> getDateFromTextLine = e ->
                e.getDetectedText().split(" ")[0];

        return response.getTextDetections()
                .stream()
                .filter(isBoughtDate)
                .findFirst()
                .map(getDateFromTextLine)
                .orElse(null);
    }
}
