package com.wenmingkeji.peiliao.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.TravelingAdapter;
import com.wenmingkeji.peiliao.model.ChannelEntity;
import com.wenmingkeji.peiliao.model.FilterData;
import com.wenmingkeji.peiliao.model.OperationEntity;
import com.wenmingkeji.peiliao.model.TravelingEntity;
import com.wenmingkeji.peiliao.view.FilterView;
import com.wenmingkeji.peiliao.view.HeaderAdViewView;
import com.wenmingkeji.peiliao.view.HeaderChannelViewView;
import com.wenmingkeji.peiliao.view.HeaderDividerViewView;
import com.wenmingkeji.peiliao.view.HeaderFilterViewView;
import com.wenmingkeji.peiliao.view.HeaderOperationViewView;
import com.wenmingkeji.peiliao.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/25.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.listView)
    SmoothListView mListView;
    @BindView(R.id.view_title_bg)
    View mViewTitleBg;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.view_action_more_bg)
    View mViewActionMoreBg;
    @BindView(R.id.fl_action_more)
    FrameLayout mFlActionMore;
    @BindView(R.id.rl_bar)
    RelativeLayout mRlBar;
    @BindView(R.id.fv_top_filter)
    FilterView mFvTopFilter;

    private Context mContext;
    private Activity mActivity;
    private int mScreenHeight; // 屏幕高度

    private List<String> adList = new ArrayList<>(); // 广告数据
    private List<ChannelEntity> channelList = new ArrayList<>(); // 频道数据
    private List<OperationEntity> operationList = new ArrayList<>(); // 运营数据
    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据

    private HeaderAdViewView listViewAdHeaderView; // 广告视图
    private HeaderChannelViewView headerChannelView; // 频道视图
    private HeaderOperationViewView headerOperationViewView; // 运营视图
    private HeaderDividerViewView headerDividerViewView; // 分割线占位图
    private HeaderFilterViewView headerFilterViewView; // 分类筛选视图
    private FilterData filterData; // 筛选数据
    private TravelingAdapter mAdapter; // 主页数据


    private View itemHeaderAdView; // 从ListView获取的广告子View
    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private boolean isScrollIdle = true; // ListView是否在滑动
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int titleViewHeight = 50; // 标题栏的高度
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)

    private int adViewHeight = 180; // 广告视图的高度
    private int adViewTopSpace; // 广告视图距离顶部的距离

    private int filterViewPosition = 4; // 筛选视图的位置
    private int filterViewTopSpace; // 筛选视图距离顶部的距离


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        return view;
    }
}
