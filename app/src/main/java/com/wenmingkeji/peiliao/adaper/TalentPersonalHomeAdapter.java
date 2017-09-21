package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.TalentHomeItem;
import com.wenmingkeji.peiliao.model.User;

import java.util.List;

/**
 * Created by bevis on 2016/7/18.
 */
public class TalentPersonalHomeAdapter extends BaseMultiItemQuickAdapter<User> {

    public TalentPersonalHomeAdapter(List<User> data) {
        super(data);
        addItemType(TalentHomeItem.INDICATOR, R.layout.view_talent_personal_home_indicator);
        addItemType(TalentHomeItem.CONTENT, R.layout.item_list_talent_zone);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, User user) {
        switch (baseViewHolder.getItemViewType()) {
            case TalentHomeItem.CONTENT:


                break;

            case TalentHomeItem.INDICATOR:

                baseViewHolder.setOnClickListener(R.id.ll_status, new OnItemChildClickListener())
                        .setOnClickListener(R.id.ll_message, new OnItemChildClickListener())
                        .setOnClickListener(R.id.tv_intro, new OnItemChildClickListener());
                break;
        }
    }
}
