/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Евдокимова
 */
@Entity
public class Subscription implements Serializable{
    private int id;
    private String name;
    private String rssLink;

    public Subscription() {
    }

    public Subscription(String name, String rssLink) {
        this.name = name;
        this.rssLink = rssLink;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(String rssLink) {
        this.rssLink = rssLink;
    }

    @Override
    public String toString() {
        return String.format("%s\t| [ %s ]", name, rssLink);
    }
    
    
}
