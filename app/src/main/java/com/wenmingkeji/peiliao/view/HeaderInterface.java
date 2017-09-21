package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ListView;

/**
 * Created by bevis on 2016/7/25.
 */
public abstract class HeaderInterface {

    protected Context mContext;
    protected LayoutInflater mInflate;

    public HeaderInterface(Context context) {
        this.mContext = context;
        mInflate = LayoutInflater.from(context);
    }

    public boolean fillView(ListView listView) {

        getView(listView);
        return true;
    }

    protected abstract void getView(ListView listView);
}
