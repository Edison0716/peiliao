package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/6/23.
 */
public class NameWithIcon {
    private String[] names;
    private int[] icons;
    
    public NameWithIcon(String[] names, int[] icons) {
        this.names = names;
        this.icons = icons;
        
    }

    public void setIcons(int[] icons) {
        this.icons = icons;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[] getIcons() {
        return icons;
    }

    public String[] getNames() {
        return names;
    }
}
