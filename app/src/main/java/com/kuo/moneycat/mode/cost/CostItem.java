package com.kuo.moneycat.mode.cost;

/**
 * Created by Kuo on 2015/10/23.
 */
public class CostItem {

    private int iconImage, cost;
    private String titleText;

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
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
}
