package service.impl;

import dto.ticket.TicketRequest;
import dto.ticket.UnprocessedTicket;
import service.AiService;
import service.IngredientService;
import service.RekognitionService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketServiceImpl {

    private final RekognitionService rekognitionService = new RekognitionServiceImpl();
    private final AiService aiService = new AiServiceImpl();
    private final IngredientService ingredientService = new IngredientServiceImpl();

    public void processTicket(TicketRequest request) {
        UnprocessedTicket unprocessedTicket = rekognitionService.handleRequest(request);
        List<String> unprocessedIngredients = unprocessedTicket.getIngredients();
        unprocessedIngredients.forEach(System.out::println);
        String prompt = generatePromptFromList(unprocessedIngredients);
        String response = aiService.request(prompt);
        List<String> ingredients = listIngredientFromResponse(response);
        ingredients.forEach(System.out::println);
    }

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

    public static String generatePromptFromList(List<String> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            String name = ingredients.get(i);
            sb.append((i + 1)).append(". ").append(name).append(" ");
        }
        return sb.toString();
    }

    public static List<String> listIngredientFromResponse(String input) {
        List<String> wordsInUpperCase = new ArrayList<>();
        String regex = "\\b[A-ZÁÉÍÓÚÜÑ]+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            wordsInUpperCase.add(matcher.group());
        }
        return wordsInUpperCase;
    }
}
