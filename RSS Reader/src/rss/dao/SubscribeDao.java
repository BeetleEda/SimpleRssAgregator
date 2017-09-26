/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.dao;

import com.sun.syndication.feed.atom.Person;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rss.entities.Subscription;
import util.HibernateUtil;

/**
 *
 * @author Евдокимова
 */
public class SubscribeDao {
    
    
    public static List<Subscription> getAllSubcriptions(){
        Session s = HibernateUtil.openSession();
        List<Subscription> list;
        try {

             list = s.createCriteria(Subscription.class).list();

        } catch (HibernateException ex) {
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
            list = Collections.EMPTY_LIST;
        } finally {
            s.close();
        }
        return list;
    }
    
    public static void addSubcription(Subscription subscription) {
        Session session = HibernateUtil.openSession();
        Transaction tr = session.beginTransaction();

        session.save(subscription);
        tr.commit();
        session.close();
    }

}
