package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/7/13.
 */
public class MyMessage {
    private ItemType mItemType;
    private MsgPreview mMsgPreview;
    private MyNotification mMyNotification;
    private MsgChat mMsgChat;

    public ItemType getItemType() {
        return mItemType;
    }

    public void setItemType(ItemType itemType) {
        mItemType = itemType;
    }

    public MsgChat getMsgChat() {
        return mMsgChat;
    }

    public void setMsgChat(MsgChat msgChat) {
        mMsgChat = msgChat;
    }

    public MsgPreview getMsgPreview() {
        return mMsgPreview;
    }

    public void setMsgPreview(MsgPreview msgPreview) {
        mMsgPreview = msgPreview;
    }

    public MyNotification getMyNotification() {
        return mMyNotification;
    }

    public void setMyNotification(MyNotification myNotification) {
        mMyNotification = myNotification;
    }
}
