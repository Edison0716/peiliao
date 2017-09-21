package com.wenmingkeji.peiliao.model;

/**
 * Created by cfanr on 2015/12/4.
 */
public enum ItemType{
    NOTIFICAION(1),
    MSG_PREVIEW(2),
    CHAT_MESSAGE(3);
    
    private int value;

    ItemType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
