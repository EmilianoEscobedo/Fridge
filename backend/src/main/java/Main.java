import dto.RegisterRequest;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        UserServiceImpl userService = new UserServiceImpl();

        RegisterRequest dto = new RegisterRequest();
        dto.setName("Emi");
        dto.setPassword("lala");
        dto.setEmail("emi@emi.com");

        userService.createUser(dto);

        emf.close();
    }
}