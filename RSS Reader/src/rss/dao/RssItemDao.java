/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rss.entities.RssItem;
import util.HibernateUtil;

/**
 *
 * @author Евдокимова
 */
public  class RssItemDao {

    private static final int MAX_ROW_COUNT = 1000;

    public List<RssItem> findAll() {
        return getListByQuery("from RssItem order by pubDate desc");
    }

    static volatile Object mutex = new Object();
    private List<RssItem> getListByQuery(String query) throws HibernateException {
        List<RssItem> list;
        synchronized(mutex){
            Session openSession = HibernateUtil.openSession();
            try {
                Query hqlQuery = openSession.createQuery(query);
                hqlQuery.setMaxResults(MAX_ROW_COUNT);
                list = hqlQuery.list();
            } catch (HibernateException ex) {
                ex.printStackTrace(System.err);
                list = Collections.EMPTY_LIST;
            } finally {
                openSession.close();
            }
            return list;
        }
    }

    public List<RssItem> findByDate(Date date) {
        return Collections.EMPTY_LIST;
    }

    public List<RssItem> findByChannel(String channelName) {
        return getListByQuery("select it from RssItem it where channelTitle like '%"+channelName+"%'");
    }

    public List<RssItem> findByTitle(String titlePart) {
        return getListByQuery(String.format("from RssItem  where title like '%%%s%%' ", titlePart));
    }

    public List<RssItem> findbyDecscription(String descriptionPart) {
        return getListByQuery(String.format("from RssItem  where title like '%%%s%%' ", descriptionPart));
    }
    
    public List<RssItem> findbySubstring(String template) {
        return getListByQuery(String.format("from RssItem  where lower(title) like '%%%s%%' or lower(description) like '%%%s%%' ", template, template));
    }

    public List<RssItem> findbyItem(RssItem item) {
        return getListByQuery(String.format("from RssItem  where title ='%s' and link='%s'", item.getTitle(), item.getLink()));
    }
    
    public List<RssItem> findbyGreaterId(int id) {
        return getListByQuery(String.format("from RssItem where id > %d", id));
    }
    
    public void addRssItem(RssItem item){
        synchronized(mutex){
            Session session = HibernateUtil.openSession();
            Transaction tr = session.beginTransaction();
            
            session.save(item);
            tr.commit();
            session.close();
        }
    }
    
    public boolean tryAddIfNotExist(RssItem item) {
        List<RssItem> list = findbyItem(item);
        if (list == null || list.isEmpty()) {
            addRssItem(item);
            return true;
        }
        return false;
    }
    
    public int add(List<RssItem> items){
        int count= 0;
        for (RssItem item : items) {
            if (tryAddIfNotExist(item))
                count++;
        }
        return count;
    }
}
