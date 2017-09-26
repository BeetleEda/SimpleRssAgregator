/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;


/**
 *
 * @author Евдокимова
 */
@Entity
public class RssItem {
    private String title;
    private String description;
    private Date pubDate;
    private String link;
    private String channelTitle;
    private String channelLink;
    
    
    private Integer id;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRssChannelTitle() {
        return channelTitle;
    }

    public void setRssChannelTitle(String rssChannelTitle) {
        this.channelTitle = rssChannelTitle;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }

    @Override
    public String toString() {
        return String.format("%s \n\t\"%s\"\n\t[%s]", title, description, link);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RssItem other = (RssItem) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.pubDate, other.pubDate)) {
            return false;
        }
        return true;
    }

    
    
     
}
