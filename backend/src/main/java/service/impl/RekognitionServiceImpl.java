package service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionAsyncClientBuilder;
import com.amazonaws.services.rekognition.model.*;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        logger.log(Level.INFO,"Verifying ticket");

        Image ticket = new Image().withBytes(ByteBuffer.wrap(request.getImage()));

        imageIsAValidTicket(ticket);
        extractIngredientsFromTicket(ticket);
    }

    @Override
    public boolean imageIsAValidTicket(Image ticket) {
        DetectLabelsRequest labelRequest = new DetectLabelsRequest()
                .withMaxLabels(5);
        DetectLabelsResult ticketLabelResult = rekognitionClient.detectLabels(
                labelRequest.withImage(ticket));

        float ticketLabelConfidence = getTicketLabelConfidence(ticketLabelResult.getLabels());

        if (ticketLabelConfidence > 70){
            logger.log(Level.INFO,"Valid ticket format");
            return true;
        }
        logger.log(Level.INFO,"Invalid ticket format");
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
    public List<UserIngredientDto> extractIngredientsFromTicket(Image ticket) {
        return null;
    }
}
