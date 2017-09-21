package com.wenmingkeji.peiliao.model;

import java.util.HashMap;
import java.util.List;

//徐桑博改的
public class CareerBean {

public int position;
    public List<String>mList;
    public CareerBean(int position,List<String>mList){
        this.position=position;
        this.mList=mList;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
    }

    public List<String> getmList() {

        return mList;
    }

    public int getPosition() {

        return position;
    }
}
