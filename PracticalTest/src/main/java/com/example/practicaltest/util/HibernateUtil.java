package com.example.practicaltest.util;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Player.class)
                    .addAnnotatedClass(Indexer.class)
                    .addAnnotatedClass(PlayerIndex.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void close() {
        sessionFactory.close();
    }
}
