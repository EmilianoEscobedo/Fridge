package repository;

import entity.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class IngredientRepository {

    private final EntityManagerFactory emf;

    public IngredientRepository() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    public void saveIngredient(Ingredient ingredient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
        em.close();
    }

    public Ingredient findIngredientById(Long id) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = em.find(Ingredient.class, id);
        em.close();
        return ingredient;
    }

    public List<Ingredient> getAllIngredients() {
        EntityManager em = emf.createEntityManager();
        List<Ingredient> ingredients = em.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
        em.close();
        return ingredients;
    }

    public void deleteIngredientById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Ingredient ingredient = em.find(Ingredient.class, id);
        if (ingredient != null) {
            em.remove(ingredient);
        }
        em.getTransaction().commit();
        em.close();
    }
}

