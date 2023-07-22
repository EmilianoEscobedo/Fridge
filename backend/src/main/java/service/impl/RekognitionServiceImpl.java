package service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionAsyncClientBuilder;
import com.amazonaws.services.rekognition.model.Image;
import dto.TicketRequest;
import dto.UserIngredientDto;
import service.IngredientService;
import service.RekognitionService;

import java.util.List;
import java.util.logging.Logger;

public class RekognitionServiceImpl implements RekognitionService {

    private final AmazonRekognition rekognitionClient = AmazonRekognitionAsyncClientBuilder
            .standard().withRegion("us-east-1").build();
    private final Logger logger = Logger.getLogger(RekognitionServiceImpl.class.getName());
    private final IngredientService ingredientService = new IngredientServiceImpl();


    @Override
    public void handleRequest(TicketRequest request) {
    }

    @Override
    public boolean imageIsAValidTicket(Image ticket) {
        return false;
    }

    @Override
    public List<UserIngredientDto> extractIngredientsFromTicket(Image ticket) {
        return null;
    }
}
