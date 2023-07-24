package service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionAsyncClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import dto.ticket.TicketRequest;
import dto.ticket.UnprocessedTicket;
import service.RekognitionService;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RekognitionServiceImpl implements RekognitionService {

    private final AmazonRekognition rekognitionClient = AmazonRekognitionAsyncClientBuilder
            .standard().withRegion("us-east-1").build();

    @Override
    public UnprocessedTicket handleRequest(TicketRequest request) {
        System.out.println("Verifying ticket");
        Image ticket = new Image().withBytes(ByteBuffer.wrap(request.getImage()));

        //imageIsAValidTicket(ticket);
        UnprocessedTicket unprocessedTicket = extractInformationFromTicket(ticket);
        unprocessedTicket.setUserId(request.getUserId());

        return unprocessedTicket;
        //System.out.println(processedTicket);
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
    public UnprocessedTicket extractInformationFromTicket(Image ticket) {
        BoundingBox boundingBox = new BoundingBox()
                .withWidth(0.6f) // 60% of the image width
                .withHeight(0.85f) // 85% of the image height (exclude top 15%)
                .withLeft(0.0f) // Starting from the left edge of the image
                .withTop(0.15f);

        RegionOfInterest region = new RegionOfInterest().withBoundingBox(boundingBox);
        DetectTextFilters filters = new DetectTextFilters().withRegionsOfInterest(region);

        DetectTextRequest request = new DetectTextRequest()
                .withImage(ticket)
                .withFilters(filters);

        DetectTextResult result = rekognitionClient.detectText(request);

        String boughDate = extractBoughDateFromResponse(result);
        List <String> unprocessedIngredients = extractIngredientsFromResponse(result);

        //unprocessedIngredients.forEach(System.out::println);
        return new UnprocessedTicket(boughDate,unprocessedIngredients);
    }


    public List<String> extractIngredientsFromResponse(DetectTextResult response){
        // matches if text line has at least one word with at least 3 letters
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
                .collect(Collectors.toList());
    }

    public String extractBoughDateFromResponse(DetectTextResult response){
        //matches if text line has a substring with date format: **/**/**
        Predicate<TextDetection> isBoughtDate = input ->
                input.getDetectedText().matches("\\b\\d{1,2}/\\d{1,2}/\\d{2,4}\\b");
        //get just the date from the entire string
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
