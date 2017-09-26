/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import rss.entities.RssItem;
import rss.entities.Subscription;

/**
 *
 * @author Евдокимова
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {

        try {
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.SEVERE);
            Configuration config = new Configuration();
            //config.addAnnotatedClass(RssChannel.class);
            config.addAnnotatedClass(Subscription.class);
            config.addAnnotatedClass(RssItem.class);
            config.configure();
//            new SchemaExport(config).create(true, true);
//            new SchemaExport(config).execute(true, true, false, true);
            sessionFactory = config.buildSessionFactory();
        } catch (HibernateException ex) {
            Logger.getLogger("app").log(Level.WARNING, ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
    
    public static  void closeSessionFactory(){
        sessionFactory.close();
    }

}
