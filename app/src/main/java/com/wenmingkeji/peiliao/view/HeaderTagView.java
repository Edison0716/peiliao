package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/27.
 */
public class HeaderTagView extends HeaderInterface {

    @BindView(R.id.tv_recommend)
    TextView mTvRecommend;
    @BindView(R.id.tv_latest)
    TextView mTvLatest;
    @BindView(R.id.tv_today)
    TextView mTvToday;
    @BindView(R.id.tv_hot)
    TextView mTvHot;

    public HeaderTagView(Context context) {
        super(context);
    }

    @Override
    protected void getView(ListView listView) {
        View view = mInflate.inflate(R.layout.view_home_header_tag, null);
        ButterKnife.bind(this, view);

        listView.addHeaderView(view);
    }
}
