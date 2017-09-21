package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.GridHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/26.
 */
public class HeaderGridDetails extends HeaderInterface {


    @BindView(R.id.rl_header)
    View mRlHeader;
    @BindView(R.id.icon_head)
    CircleImageView mIconHead;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    private Context mContext;

    private GridHeader mHeader;

    public HeaderGridDetails(Context context, GridHeader header) {
        super(context);
        this.mContext = context;
        this.mHeader = header;
    }

    @Override
    protected void getView(ListView listView) {
        View view = mInflate.inflate(R.layout.view_grid_details_header, null);
        ButterKnife.bind(this, view);

        setHeaderGridDetail();
        listView.addHeaderView(view);
    }

    void setHeaderGridDetail() {

        //设置二级首页的头像
        mIconHead.setImageResource(mHeader.getIcon());

        //设置二级首页的名称
        mTvCategory.setText(mHeader.getName());

        //设置二级首页的背景
        mIvHeader.setImageResource(mHeader.getBg());
    }
}
