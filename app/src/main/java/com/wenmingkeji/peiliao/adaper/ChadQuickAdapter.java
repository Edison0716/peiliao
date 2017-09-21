package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.model.User;

import java.util.List;

/**
 * Created by bevis on 2016/7/14.
 */
public class ChadQuickAdapter extends BaseQuickAdapter<User> {

    public ChadQuickAdapter(int layoutResId, List<User> data) {
        super(layoutResId, data);
        System.out.println(data.size());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, User user) {
        System.out.println("users");
    }
}
