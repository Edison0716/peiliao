package com.wenmingkeji.peiliao.controller;

import android.app.Application;

/**
 * Created by xifan on 2016/4/22.
 */
public class AppController extends Application {
    private static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppController getInstance() {
        if (null == instance) {
            instance = new AppController();
        }
        return instance;
    }
}
