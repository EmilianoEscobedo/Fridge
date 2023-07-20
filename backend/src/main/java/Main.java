import repository.impl.IngredientRepository;
import repository.impl.RecipeRepository;
import repository.impl.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        emf.close();
    }
}