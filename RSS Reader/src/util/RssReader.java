/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import rss.entities.RssItem;

/**
 *
 * @author Евдокимова
 */
public class RssReader {
    
    public List<RssItem> listNews(String address) throws IOException, FeedException {
        SyndFeed feed;
        URL url = new URL(address);
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        SyndFeedInput input = new SyndFeedInput();
        feed = input.build(new XmlReader(httpcon));
        //final RssChannel rssChannel = new RssChannel(feed.getTitle(), feed.getLink(), feed.getDescription());
        final RssChannelInfo channelInfo = new RssChannelInfo(feed.getTitle(), feed.getLink());
        return (List<RssItem>) feed.getEntries().stream().filter(item -> item instanceof SyndEntryImpl)
                .map(entry -> convertToRssItem((SyndEntryImpl) entry, channelInfo))
                .collect(Collectors.toCollection(() -> new ArrayList<RssItem>()));
    }

    private static RssItem convertToRssItem(SyndEntryImpl entry, RssChannelInfo rssChannel) {
        RssItem item = new RssItem();
        item.setTitle(entry.getTitle());
        item.setLink(entry.getLink());
        item.setDescription(entry.getDescription().getValue().trim());
        item.setPubDate(entry.getPublishedDate());
        if (rssChannel != null){
            item.setChannelLink(rssChannel.getLink());
            item.setRssChannelTitle(rssChannel.getTitle());
        }
        return item;
        
    }

}
