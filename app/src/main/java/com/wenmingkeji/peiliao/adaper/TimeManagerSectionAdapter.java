package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.TimeItem;
import com.wenmingkeji.peiliao.model.TimeManager;

import java.util.List;

/**
 * Created by bevis on 2016/7/22.
 */
public class TimeManagerSectionAdapter extends BaseSectionQuickAdapter<TimeManager> {

    public TimeManagerSectionAdapter(int layoutResId, int sectionHeadResId, List<TimeManager> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, TimeManager timeManager) {
        baseViewHolder.setText(R.id.tv_time_banner, timeManager.header);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TimeManager timeManager) {
        TimeItem timeItem = timeManager.t;
        baseViewHolder.setOnClickListener(R.id.iv_time_morning, new OnItemChildClickListener());
        baseViewHolder.getView(R.id.iv_time_morning).setSelected(true);
       /* baseViewHolder.setOnClickListener(R.id.toggleView_morning, new OnItemChildClickListener())
                .setOnClickListener(R.id.toggleView_afternoon, new OnItemChildClickListener())
                .setOnClickListener(R.id.toggleView_evening, new OnItemChildClickListener());
        */
    }
}
