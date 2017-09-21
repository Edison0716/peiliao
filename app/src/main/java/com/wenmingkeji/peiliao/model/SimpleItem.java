package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/7/14.
 */
public class SimpleItem {

    /**
     * 从复杂的布局中分离出来的Item实体，主要用于该类item的赋值
     * 如，服务列表item上的头像一栏以及其它带像的item
     * 也可用于消息模块的预览消息列表，聊天列表item以及通知列表的item
     * 将这样类型的item所有属性封装到一起，然后根据自身item需要初始化不同的属性
     */
    private int userHead;
    private String userName;
    private int levelIcon;
    private String levelName;
    private String fansNum;
    private String followStatus;
    private String time;
    private String content;
    private String msgNum;
    private int status;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(String msgNum) {
        this.msgNum = msgNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public int getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(int levelIcon) {
        this.levelIcon = levelIcon;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getUserHead() {
        return userHead;
    }

    public void setUserHead(int userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
