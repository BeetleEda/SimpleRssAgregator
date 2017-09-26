/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rss.entities.RssItem;

/**
 *
 * @author Евдокимова
 */
public class RssItemDisplayer {
    
    public static AbstractTableModel getTableModelForRSSItems(List<RssItem> items){
       return new RssItemsModel(items);
    }
}
