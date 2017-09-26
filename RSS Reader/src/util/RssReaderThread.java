/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.syndication.io.FeedException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.MessageBus;
import rss.entities.RssItem;
import rss.dao.RssItemDao;
import rss.entities.Subscription;

/**
 *
 * @author Евдокимова
 */
public class RssReaderThread extends Thread{
    
    private volatile boolean isWorking = false;
    private long sleepTimeoutSec = 10*60;
    
    private Set<String> rssLinks = new HashSet<>();
    RssReader reader = new RssReader();
    RssItemDao rssItemDao = new RssItemDao();

    @Override
    public void run() {
        isWorking = true;
        while (isWorking){
            for (String rssLink : rssLinks) {
                try {
                    List<RssItem> listNews = reader.listNews(rssLink);
                    int add = rssItemDao.add(listNews);
                    if (add > 0)
                        MessageBus.getInstance().sendMessage();
                    
                } catch (IOException | FeedException ex) {
                    Logger.getLogger(RssReaderThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
            try {
                Thread.sleep(sleepTimeoutSec*1000);
            } catch (InterruptedException ex) {
                
            }
        }
    }

    public void addSubscribeTo(String rss){
        rssLinks.add(rss);
    }
    
    public void stopListening(){
        isWorking = false;
    }
    public void setSleepTimeoutSec(long sleepTimeoutSec) {
        this.sleepTimeoutSec = sleepTimeoutSec;
    }

    public void addSubscribeTo(Subscription s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
