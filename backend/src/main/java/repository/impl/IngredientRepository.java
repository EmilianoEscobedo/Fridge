package repository.impl;

import entity.Ingredient;
import entity.UserIngredient;
import jakarta.enterprise.context.ApplicationScoped;
import repository.Repository;
import util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
@ApplicationScoped
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
    public Optional<Ingredient> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = em.find(Ingredient.class, id);
        em.close();
        return Optional.ofNullable(ingredient);
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

    public void saveUserIngredient(UserIngredient userIngredient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userIngredient);
        em.getTransaction().commit();
        em.close();
    }

    public boolean existsByName(String name) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(i) FROM Ingredient i WHERE i.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    public List<UserIngredient> findUserIngredientsByUserId(Long userId) {
        EntityManager em = emf.createEntityManager();
        List<UserIngredient> userIngredients = em.createQuery(
                        "SELECT ui FROM UserIngredient ui WHERE ui.user.id = :userId", UserIngredient.class)
                .setParameter("userId", userId)
                .getResultList();
        em.close();
        return userIngredients;
    }

    public List<UserIngredient> findUserIngredientsByIngredientId(Long ingredientId) {
        EntityManager em = emf.createEntityManager();
        List<UserIngredient> userIngredients = em.createQuery(
                        "SELECT ui FROM UserIngredient ui WHERE ui.ingredient.id = :ingredientId", UserIngredient.class)
                .setParameter("ingredientId", ingredientId)
                .getResultList();
        em.close();
        return userIngredients;
    }

    public Long findIdByName(String name) {
        EntityManager em = emf.createEntityManager();
        Long id = em.createQuery("SELECT i.id FROM Ingredient i WHERE i.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return id;
    }
}
