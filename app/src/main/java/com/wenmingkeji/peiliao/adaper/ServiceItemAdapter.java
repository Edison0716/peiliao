package com.wenmingkeji.peiliao.adaper;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.TalentPersonalActivity;
import com.wenmingkeji.peiliao.model.ServiceItem;

import java.util.List;

/**
 * Created by bevis on 2016/7/27.
 */
public class ServiceItemAdapter extends QuickAdapter<ServiceItem> {

    private Context mContext;


    public ServiceItemAdapter(Context context, int layoutResId, List<ServiceItem> data) {
        super(context, layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ServiceItem item) {

        helper.setOnClickListener(R.id.icon_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, TalentPersonalActivity.class));
            }
        });

    }


}
