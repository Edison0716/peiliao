package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/7/8.
 */
public class Msg {

    private String msgTime;
    private String msgContent;

    public Msg(String msgContent, String msgTime) {
        this.msgContent = msgContent;
        this.msgTime = msgTime;
    }

    public Msg() {
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
