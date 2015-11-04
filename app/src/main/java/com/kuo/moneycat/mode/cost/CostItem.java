package com.kuo.moneycat.mode.cost;

import java.io.Serializable;

/**
 * Created by Kuo on 2015/10/23.
 */
public class CostItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private int rowId, iconImage, cost, year, month, day;
    private String titleText;

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitleText() {
        return titleText;
    }

    public int getIconImage() {
        return iconImage;
    }

    public int getCost() {
        return cost;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getRowId() {
        return rowId;
    }
}
