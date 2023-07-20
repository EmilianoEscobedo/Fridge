package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    private EntityManagerFactoryProvider() {
        // Private constructor to prevent external instantiation
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
