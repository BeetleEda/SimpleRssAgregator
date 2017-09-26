/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rss.entities.RssItem;

/**
 *
 * @author Евдокимова
 */
public class RssItemsModel extends AbstractTableModel {

    private List<RssItem> items;

    private static final String[] columnNames = new String[]{"Дата", "Источник", "Заголовок", "Описание"};
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");

    public RssItemsModel(List<RssItem> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "";
        RssItem rssItem = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = dateFormat.format(rssItem.getPubDate());
                break;
            case 1:
                value = rssItem.getRssChannelTitle();
                break;
            case 2:
                value = rssItem.getTitle();
                break;
            case 3:
                value = rssItem.getDescription();
                break;
        }

        return value;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < columnNames.length) {
            return columnNames[column];
        }
        return "";
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public RssItem getItemAt(int row) {
        return items.get(row);
    }

}
