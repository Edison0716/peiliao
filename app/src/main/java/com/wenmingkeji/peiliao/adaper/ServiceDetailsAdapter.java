package com.wenmingkeji.peiliao.adaper;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.HomeItem;

import java.util.List;

/**
 * Created by bevis on 2016/7/6.
 */
public class ServiceDetailsAdapter extends BaseMultiItemQuickAdapter<HomeItem> {

    private Context mContext;
    
    public ServiceDetailsAdapter(Context context, List<HomeItem> data) {
        super(data);
        this.mContext = context;
        addItemType(HomeItem.USER, R.layout.view_user_info);
        addItemType(HomeItem.PRICE, R.layout.view_details_price);
        addItemType(HomeItem.HEADER_BANNER, R.layout.view_comment_banner);
        addItemType(HomeItem.COMMENT_BANNER, R.layout.view_comment_banner);
        addItemType(HomeItem.DISCUSSION_BANNER, R.layout.view_dicussion_banner);
        addItemType(HomeItem.COMMENT, R.layout.view_service_comment);
        addItemType(HomeItem.DISCUSSION, R.layout.view_service_discussion);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeItem homeItem) {
        switch (baseViewHolder.getItemViewType()) {
            case HomeItem.USER:
                baseViewHolder.setImageResource(R.id.icon_head,homeItem.getServiceItem().getImgId());
                baseViewHolder.setText(R.id.tv_user_name, homeItem.getServiceItem().getUserName());
                break;

            case HomeItem.PRICE:
                baseViewHolder.setText(R.id.tv_service_introduce, homeItem.getServiceItem().getServiceIntroduce());
                baseViewHolder.setText(R.id.tv_skills, homeItem.getServiceItem().getSkills());
                break;

            case HomeItem.COMMENT_BANNER:
                baseViewHolder.setText(R.id.tv_num_comments, "212");
                break;

            case HomeItem.DISCUSSION_BANNER:
                baseViewHolder.setText(R.id.tv_num_discussions, "22");
                break;

            case HomeItem.DISCUSSION:


                break;
            case HomeItem.COMMENT:

                break;
        }
    }
}