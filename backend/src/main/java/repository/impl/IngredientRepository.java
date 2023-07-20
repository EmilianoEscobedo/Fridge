package repository.impl;

import entity.Ingredient;
import repository.Repository;
import util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class IngredientRepository implements Repository<Ingredient, Long> {

    private final EntityManagerFactory emf;

    public IngredientRepository() {
        emf = EntityManagerFactoryProvider.getEntityManagerFactory();
    }

    @Override
    public void save(Ingredient ingredient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Ingredient findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = em.find(Ingredient.class, id);
        em.close();
        return ingredient;
    }

    @Override
    public List<Ingredient> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Ingredient> ingredients = em.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
        em.close();
        return ingredients;
    }

    @Override
    public void deleteById(Long id) {
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

