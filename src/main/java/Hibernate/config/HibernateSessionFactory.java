package Hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {

    private static SessionFactory SESSION_FACTORY = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try {

            SESSION_FACTORY = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {

            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError(e);
        }

        return SESSION_FACTORY;
    }

    public static SessionFactory getSessionFactory() {

        return SESSION_FACTORY;
    }

    public static void close() {

        getSessionFactory().close();
    }
}