import controller.IngredientController;
import controller.RecipeController;
import controller.UserController;
import dto.RegisterRequest;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

//        UserServiceImpl userService = new UserServiceImpl();
//
//        RegisterRequest dto = new RegisterRequest();
//        dto.setName("Emi");
//        dto.setPassword("lala");
//        dto.setEmail("emi@emi.com");
//        userService.createUser(dto);
        Endpoint.publish("http://localhost:8080/ws/user", new UserController());
        Endpoint.publish("http://localhost:8080/ws/ingredient", new IngredientController());
        emf.close();
    }
}