package com.otus.hw_09.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil
{
    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error building factory");
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
