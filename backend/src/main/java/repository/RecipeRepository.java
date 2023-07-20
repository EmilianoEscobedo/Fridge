package repository;

import entity.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RecipeRepository {

    private final EntityManagerFactory emf;

    public RecipeRepository() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    public void saveRecipe(Recipe recipe) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(recipe);
        em.getTransaction().commit();
        em.close();
    }

    public Recipe findRecipeById(Long id) {
        EntityManager em = emf.createEntityManager();
        Recipe recipe = em.find(Recipe.class, id);
        em.close();
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        EntityManager em = emf.createEntityManager();
        List<Recipe> recipes = em.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
        em.close();
        return recipes;
    }

    public void deleteRecipeById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Recipe recipe = em.find(Recipe.class, id);
        if (recipe != null) {
            em.remove(recipe);
        }
        em.getTransaction().commit();
        em.close();
    }
}
