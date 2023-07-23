package util;

import jakarta.enterprise.context.ApplicationScoped;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    private EntityManagerFactoryProvider() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
