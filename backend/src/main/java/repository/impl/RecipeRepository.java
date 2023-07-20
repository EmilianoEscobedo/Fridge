package repository.impl;

import entity.Recipe;
import repository.Repository;
import util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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
    public Recipe findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Recipe recipe = em.find(Recipe.class, id);
        em.close();
        return recipe;
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
}

