package com.wenmingkeji.peiliao.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by bevis on 2016/7/13.
 */
public class MsgChat extends MultiItemEntity {

    public static final int MSG_RECEIVED = 0;
    public static final int MSG_SENT = 1;


    private String chatContent;
    private String chatTime;

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }

    public String getChatContent() {

        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }
}
