package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/7/13.
 */
public class MsgPreview {

    //消息预览

    int type;
    private String content;
    private String userName;
    private int iconHead;
    private String time;
    private int num;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIconHead() {
        return iconHead;
    }

    public void setIconHead(int iconHead) {
        this.iconHead = iconHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
