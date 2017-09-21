package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.TravelingAdapter;
import com.wenmingkeji.peiliao.model.ChannelEntity;
import com.wenmingkeji.peiliao.model.FilterData;
import com.wenmingkeji.peiliao.model.FilterEntity;
import com.wenmingkeji.peiliao.model.FilterTwoEntity;
import com.wenmingkeji.peiliao.model.OperationEntity;
import com.wenmingkeji.peiliao.model.TravelingEntity;
import com.wenmingkeji.peiliao.utils.ColorUtil;
import com.wenmingkeji.peiliao.utils.DensityUtil;
import com.wenmingkeji.peiliao.utils.ModelUtil;
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

public class Main4Activity extends AppCompatActivity implements SmoothListView.ISmoothListViewListener {


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

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ButterKnife.bind(this);

        initData();
        initView();
        initListener();
    }

    private void initData() {
        mContext = this;
        mActivity = this;
        mScreenHeight = DensityUtil.getWindowHeight(this);

        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getSortData());
        filterData.setFilters(ModelUtil.getFilterData());

        // 广告数据
        adList = ModelUtil.getAdData();

        // 频道数据
        channelList = ModelUtil.getChannelData();

        // 运营数据
        operationList = ModelUtil.getOperationData();

        // ListView数据
        travelingList = ModelUtil.getTravelingData();
    }

    private void initView() {
        mFvTopFilter.setVisibility(View.GONE);

        // 设置筛选数据
        mFvTopFilter.setFilterData(mActivity, filterData);

        // 设置广告数据
        listViewAdHeaderView = new HeaderAdViewView(this);
        listViewAdHeaderView.fillView(adList, mListView);
/*

        // 设置频道数据
        headerChannelView = new HeaderChannelViewView(this);
        headerChannelView.fillView(channelList, mListView);
*/

        // 设置运营数据
        headerOperationViewView = new HeaderOperationViewView(this);
        headerOperationViewView.fillView(operationList, mListView);

        /*// 设置分割线
        headerDividerViewView = new HeaderDividerViewView(this);
        headerDividerViewView.fillView("", mListView);*/

        // 设置筛选数据
        headerFilterViewView = new HeaderFilterViewView(this);
        headerFilterViewView.fillView(new Object(), mListView);

        // 设置ListView数据
        mAdapter = new TravelingAdapter(this, travelingList);
        mListView.setAdapter(mAdapter);

        filterViewPosition = mListView.getHeaderViewsCount() - 1;
    }

    private void initListener() {
        // 关于
        mFlActionMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(mActivity, AboutActivity.class));
            }
        });

        // (假的ListView头部展示的)筛选视图点击
        headerFilterViewView.setOnFilterClickListener(new HeaderFilterViewView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                isSmooth = true;
                mListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
            }
        });

        // (真正的)筛选视图点击
        mFvTopFilter.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                if (isStickyTop) {
                    filterPosition = position;
                    mFvTopFilter.showFilterLayout(position);
                    if (titleViewHeight - 3 > filterViewTopSpace || filterViewTopSpace > titleViewHeight + 3) {
                        mListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
                    }
                }
            }
        });

        // 分类Item点击
        mFvTopFilter.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity entity) {

                fillAdapter(ModelUtil.getCategoryTravelingData(entity));
            }
        });

        // 排序Item点击
        mFvTopFilter.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getSortTravelingData(entity));
            }
        });

        // 筛选Item点击
        mFvTopFilter.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getFilterTravelingData(entity));
            }
        });

        mListView.setRefreshEnable(true);
        mListView.setLoadMoreEnable(true);
        mListView.setSmoothListViewListener(this);
        mListView.setOnScrollListener(new SmoothListView.OnSmoothScrollListener() {
            @Override
            public void onSmoothScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScrollIdle = (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScrollIdle && adViewTopSpace < 0) return;

                // 获取广告头部View、自身的高度、距离顶部的高度
                if (itemHeaderAdView == null) {
                    itemHeaderAdView = mListView.getChildAt(1 - firstVisibleItem);
                }
                if (itemHeaderAdView != null) {
                    adViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderAdView.getTop());
                    adViewHeight = DensityUtil.px2dip(mContext, itemHeaderAdView.getHeight());
                }

                // 获取筛选View、距离顶部的高度
                if (itemHeaderFilterView == null) {
                    itemHeaderFilterView = mListView.getChildAt(filterViewPosition - firstVisibleItem);
                }
                if (itemHeaderFilterView != null) {
                    filterViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop());
                }

                // 处理筛选是否吸附在顶部
                if (filterViewTopSpace > titleViewHeight) {
                    isStickyTop = false; // 没有吸附在顶部
                    mFvTopFilter.setVisibility(View.GONE);
                } else {
                    isStickyTop = true; // 吸附在顶部
                    mFvTopFilter.setVisibility(View.VISIBLE);
                }

                if (firstVisibleItem > filterViewPosition) {
                    isStickyTop = true;
                    mFvTopFilter.setVisibility(View.VISIBLE);
                }

                if (isSmooth && isStickyTop) {
                    isSmooth = false;
                    mFvTopFilter.showFilterLayout(filterPosition);
                }

                mFvTopFilter.setStickyTop(isStickyTop);

                // 处理标题栏颜色渐变
                handleTitleBarColorEvaluate();
            }
        });
    }

    // 填充数据
    private void fillAdapter(List<TravelingEntity> list) {
        if (list == null || list.size() == 0) {
            mListView.setLoadMoreEnable(false);
            int height = mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
            mAdapter.setData(ModelUtil.getNoDataEntity(height));
        } else {
            mListView.setLoadMoreEnable(list.size() > TravelingAdapter.ONE_REQUEST_COUNT);
            mAdapter.setData(list);
        }
    }

    // 处理标题栏颜色渐变
    private void handleTitleBarColorEvaluate() {
        float fraction;
        if (adViewTopSpace > 0) {
            fraction = 1f - adViewTopSpace * 1f / 60;
            if (fraction < 0f) fraction = 0f;
            mRlBar.setAlpha(fraction);
            return;
        }

        float space = Math.abs(adViewTopSpace) * 1f;
        fraction = space / (adViewHeight - titleViewHeight);
        if (fraction < 0f) fraction = 0f;
        if (fraction > 1f) fraction = 1f;
        mRlBar.setAlpha(1f);

        if (fraction >= 1f || isStickyTop) {
            isStickyTop = true;
            mViewTitleBg.setAlpha(0f);
            mViewActionMoreBg.setAlpha(0f);
            mRlBar.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
        } else {
            mViewTitleBg.setAlpha(1f - fraction);
            mViewActionMoreBg.setAlpha(1f - fraction);
            mRlBar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(mContext, fraction, R.color.transparent, R.color.orange));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listViewAdHeaderView != null) {
            listViewAdHeaderView.stopADRotate();
        }
    }

    @Override
    public void onBackPressed() {
        if (!mFvTopFilter.isShowing()) {
            super.onBackPressed();
        } else {
            mFvTopFilter.resetAllStatus();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.stopRefresh();
                mListView.setRefreshTime("刚刚");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.stopLoadMore();
            }
        }, 2000);
    }

}
