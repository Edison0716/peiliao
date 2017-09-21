package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.NameAndIcon;

import java.util.List;

/**
 * Created by bevis on 2016/7/5.
 */
public class RecommendShowAdapter extends BaseQuickAdapter<NameAndIcon> {


    public RecommendShowAdapter(int layoutResId, List<NameAndIcon> data) {
        super(layoutResId, data);
        
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NameAndIcon nameAndIcon) {

        baseViewHolder.setText(R.id.tv_title, nameAndIcon.getName())
                .setImageResource(R.id.iv_icon, nameAndIcon.getIcon());
                
    }
}
