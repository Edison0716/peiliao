package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.SimpleItem;

import java.util.List;

/**
 * Created by bevis on 2016/7/14.
 */
public class TalentRecommendAdapter extends BaseQuickAdapter<SimpleItem> {


    public TalentRecommendAdapter(int layoutResId, List<SimpleItem> data) {
        super(R.layout.item_list_talent_recommand, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SimpleItem simpleItem) {
        baseViewHolder.setImageResource(R.id.icon_head, simpleItem.getUserHead())
                .setText(R.id.tv_user_name, simpleItem.getUserName())
                .setImageResource(R.id.iv_level_img, simpleItem.getLevelIcon())
                .setText(R.id.tv_user_level, simpleItem.getLevelName())
                .setText(R.id.tv_fans_num, simpleItem.getFansNum())
                .setOnClickListener(R.id.ibtn_follow, new OnItemChildClickListener());
    }
}
