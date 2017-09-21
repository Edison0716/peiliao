package com.wenmingkeji.peiliao.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by cfanr on 2015/12/4.
 */
public class HomeItem extends MultiItemEntity{
   
    public static final int HEADER = 1;
    public static final int RECOMMEND = 2;
    public static final int SERVICE_ITEM = 3;
    public static final int HEADER_BANNER = 4;
    public static final int AD = 5;
    public static final int TAG = 6;
    public static final int CATEGORY = 7;
    public static final int USER =8;
    public static final int PRICE =9;
    public static final int COMMENT_BANNER =10;
    public static final int DISCUSSION_BANNER =11;
    public static final int COMMENT =12;
    public static final int DISCUSSION =13;





    private ServiceItem mServiceItem;
    private String tagName;
    private Ad ad;
    private NameWithIcon mNameWithIcon;
    private List<NameAndIcon> mNameAndIconList;
    private String[] tag;

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public List<NameAndIcon> getNameAndIconList() {
        return mNameAndIconList;
    }

    public void setNameAndIconList(List<NameAndIcon> nameAndIconList) {
        mNameAndIconList = nameAndIconList;
    }

    public ServiceItem getServiceItem() {
        return mServiceItem;
    }

    public void setServiceItem(ServiceItem serviceItem) {
        mServiceItem = serviceItem;
    }

    public NameWithIcon getNameWithIcon() {
        return mNameWithIcon;
    }

    public void setNameWithIcon(NameWithIcon nameWithIcon) {
        mNameWithIcon = nameWithIcon;
    }
    
    public Ad getAd(){
        return ad;
    }

    public void setAd(Ad ad){
        this.ad=ad;
    }
    
    
    public String getTagName(){
        return tagName;
    }

    public void setTagName(String tagName){
        this.tagName=tagName;
    }

   

    
}
