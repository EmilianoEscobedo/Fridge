package repository.impl;

import entity.Recipe;
import entity.RecipeIngredient;
import jakarta.enterprise.context.ApplicationScoped;
import repository.Repository;
import util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RecipeRepository implements Repository<Recipe, Long> {

    private EntityManagerFactory emf;

    public RecipeRepository() {
        emf = EntityManagerFactoryProvider.getEntityManagerFactory();
    }

    @Override
    public void save(Recipe recipe) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(recipe);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Recipe recipe = em.find(Recipe.class, id);
        em.close();
        return Optional.ofNullable(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Recipe> recipes = em.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
        em.close();
        return recipes;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Recipe recipe = em.find(Recipe.class, id);
        if (recipe != null) {
            em.remove(recipe);
        }
        em.getTransaction().commit();
        em.close();
    }

    public boolean existsByName(String name) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(r) FROM Recipe r WHERE r.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    public void saveRecipeIngredient(RecipeIngredient recipeIngredient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(recipeIngredient);
        em.getTransaction().commit();
        em.close();
    }
}

