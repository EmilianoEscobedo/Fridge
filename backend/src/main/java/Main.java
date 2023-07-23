
import service.RekognitionService;
import service.impl.IngredientServiceImpl;
import service.impl.RecipeServiceImpl;
import service.impl.RekognitionServiceImpl;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        /*Endpoint.publish("http://localhost:8080/users", new UserServiceImpl());
        Endpoint.publish("http://localhost:8080/ingredients", new IngredientServiceImpl());
        Endpoint.publish("http://localhost:8080/recipes", new RecipeServiceImpl());*/

        RekognitionServiceImpl rekognition = new RekognitionServiceImpl();
        rekognition.handleRequest(rekognition.mockRequest());

        emf.close();
    }
}