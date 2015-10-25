package com.kuo.moneycat.mode.drawer;

/**
 * Created by Kuo on 2015/10/22.
 */
public class DrawerItem {

    private int iconResId;
    private String titleText;

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getTitleText() {
        return titleText;
    }
}
