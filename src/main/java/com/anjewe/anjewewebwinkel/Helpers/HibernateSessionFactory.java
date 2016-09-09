/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.anjewe.anjewewebwinkel.Helpers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Wendy
 */

//Hibernate SessionFactory is the factory class through which we get sessions and perform database operations.
// http://www.journaldev.com/3522/hibernate-sessionfactory

public class HibernateSessionFactory {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();

    @SuppressWarnings("deprecation")
    private static SessionFactory buildSessionFactory()
    {
        try
        {

            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            return sessionFactory;        
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    
}
//Configuration conf = new Configuration();
//SessionFactory firebirdSF = Configuration.configure("hibernateFB.cfg.xml").buildSessionFactory();
//
//SessionFactory mySQLSF = Configuration.configure("hibernateMS.cfg.xml").buildSessionFactory();