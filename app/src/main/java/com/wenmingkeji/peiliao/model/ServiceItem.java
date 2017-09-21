package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/6/23.
 */
public class ServiceItem {
    private int imgId;
    private String userName;
    private String likeNum;
    private String userLevel;
    private String commentNum;
    private String[] imageUri;
    private String skills;
    private String serviceIntroduce;
    private String sales;
    private String fansNum;
    private String price;
    
    public ServiceItem(int imgId, String userName, String skills, String serviceIntro, String price){

        this.imgId = imgId;
        this.userName = userName;
        this.skills = skills;
        this.serviceIntroduce = serviceIntro;
        this.price = price;
    }
    

    public ServiceItem(){
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String[] getImageUri() {
        return imageUri;
    }

    public void setImageUri(String[] imageUri) {
        this.imageUri = imageUri;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setServiceIntroduce(String serviceIntroduce) {
        this.serviceIntroduce = serviceIntroduce;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImgId() {
        return imgId;
    }

    public String getPrice() {
        return price;
    }

    public String getServiceIntroduce() {
        return serviceIntroduce;
    }

    public String getUserName() {
        return userName;
    }

    public String getSkills() {
        return skills;
    }
}
