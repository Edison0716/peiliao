package com.wenmingkeji.peiliao.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.android.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ServiceItemAdapter;
import com.wenmingkeji.peiliao.adaper.TravelingAdapter;
import com.wenmingkeji.peiliao.model.FilterData;
import com.wenmingkeji.peiliao.model.FilterEntity;
import com.wenmingkeji.peiliao.model.FilterTwoEntity;
import com.wenmingkeji.peiliao.model.GridHeader;
import com.wenmingkeji.peiliao.model.NameAndIcon;
import com.wenmingkeji.peiliao.model.ServiceItem;
import com.wenmingkeji.peiliao.model.TravelingEntity;
import com.wenmingkeji.peiliao.utils.DensityUtil;
import com.wenmingkeji.peiliao.utils.ModelUtil;
import com.wenmingkeji.peiliao.view.FilterView;
import com.wenmingkeji.peiliao.view.HeaderCategoryRecommend;
import com.wenmingkeji.peiliao.view.HeaderFilterViewView;
import com.wenmingkeji.peiliao.view.HeaderGridDetails;
import com.wenmingkeji.peiliao.view.SmoothListView.SmoothListView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GridDetailsActivity extends AppCompatActivity implements SmoothListView.ISmoothListViewListener {


    @BindView(R.id.iv_bg_search)
    ImageView mIvBgSearch;
    @BindView(R.id.tv_hint)
    TextView mTvHint;
    @BindView(R.id.ibtn_back)
    ImageButton mIbtnBack;
    @BindView(R.id.fv_filter)
    FilterView mFvFilter;
    @BindView(R.id.listView)
    SmoothListView mListView;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    int[] icons = {
            R.drawable.icon_common_food, R.drawable.icon_common_play,
            R.drawable.icon_common_draw, R.drawable.icon_common_life_consult,
            R.drawable.icon_common_cv, R.drawable.icon_common_lol,
            R.drawable.icon_common_sign, R.drawable.icon_common_pedict,
            R.drawable.icon_common_emotion_consult, R.drawable.icon_common_doutu

    };
    String[] names = {
            "美食", "陪聊陪玩", "手绘", "生活咨询", "声优", "LOL",
            "签名设计", "占卜", "情感咨询", "斗图"
    };
    private Context mContext;
    private BaseQuickAdapter mQuickAdapter;
    private HeaderCategoryRecommend mRecommendView;
    private HeaderFilterViewView headerFilterViewView; // 分类筛选视图
    private FilterData filterData; // 筛选数据
    private TravelingAdapter mTravelingAdapter; // 主页数据
    private int mScreenHeight; // 屏幕高度
    private HeaderGridDetails mGridDetails;
    private ServiceItemAdapter mAdapter;// 主页数据
    private List<ServiceItem> mServiceItems = new ArrayList<ServiceItem>(); // ListView数据
    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据
    private View itemHeaderAdView; // 从ListView获取的广告子View
    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private boolean isScrollIdle = true; // ListView是否在滑动
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int titleViewHeight = 40; // 标题栏的高度
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
    private int adViewHeight = 180; // 广告视图的高度
    private int adViewTopSpace; // 广告视图距离顶部的距离
    private int filterViewPosition = 4; // 筛选视图的位置
    private int filterViewTopSpace; // 筛选视图距离顶部的距离
    private GridHeader mGridHeader;
    //推荐列表数据
    private List<NameAndIcon> mNameAndIconList = new ArrayList<NameAndIcon>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_details);
        ButterKnife.bind(this);

        mContext = this;

        initData();
        initView();
        initListener();
        
        
       /* mQuickAdapter = new QuickAdapter() {
            @Override
            protected void convert(BaseAdapterHelper helper, Object item) {

            }
        };*/


    }


    void initData() {

        //初始化header
        mGridHeader = new GridHeader();
        mGridHeader.setBg(R.drawable.ad3);
        mGridHeader.setIcon(R.drawable.icon_common_food);
        mGridHeader.setName("美食达人");


        //初始化推荐列表

        for (int i = 0; i < icons.length; i++) {
            NameAndIcon nameAndIcon = new NameAndIcon();
            nameAndIcon.setIcon(icons[i]);
            nameAndIcon.setName(names[i]);
            mNameAndIconList.add(nameAndIcon);
        }

        // ListView数据
        for (int i = 0; i < 10; i++) {
            ServiceItem serviceItem = new ServiceItem();
            mServiceItems.add(serviceItem);
        }


        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getSortData());
        filterData.setFilters(ModelUtil.getFilterData());

        // ListView数据
        travelingList = ModelUtil.getTravelingData();

//        filterViewPosition = mListView.getHeaderViewsCount() - 1;

    }


    /**
     * 注意：初始化view的顺序即为显示视图的顺序
     */
    void initView() {


        //设置首页图片
        mGridDetails = new HeaderGridDetails(this, mGridHeader);
        mGridDetails.fillView(mListView);

        //设置推荐列表
        mRecommendView = new HeaderCategoryRecommend(this, mNameAndIconList);
        mRecommendView.fillView(mListView);


        //设置筛选
        headerFilterViewView = new HeaderFilterViewView(this);
        headerFilterViewView.fillView(new Object(), mListView);

        

       /* // 设置ListView数据
        mAdapter = new ServiceItemAdapter(this, R.layout.item_list_service, mServiceItems);
        mListView.setAdapter(mAdapter);*/

        // 设置ListView数据
        mTravelingAdapter = new TravelingAdapter(this, travelingList);
        mListView.setAdapter(mTravelingAdapter);

    }


    private void initListener() {
/*        // 关于
        mFlActionMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(mActivity, AboutActivity.class));
            }
        });*/

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
        mFvFilter.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                if (isStickyTop) {
                    filterPosition = position;
                    mFvFilter.showFilterLayout(position);
//                    mListView.setSelection(1);

                    if (titleViewHeight - 3 > filterViewTopSpace || filterViewTopSpace > titleViewHeight + 3) {
                        mListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
                    }
                }
            }
        });

        // 分类Item点击
        mFvFilter.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity entity) {
                fillAdapter(ModelUtil.getCategoryTravelingData(entity));
            }
        });

        // 排序Item点击
        mFvFilter.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getSortTravelingData(entity));
            }
        });

        // 筛选Item点击
        mFvFilter.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getFilterTravelingData(entity));
            }
        });

        mListView.setRefreshEnable(true);
        mListView.setLoadMoreEnable(true);
        mListView.setSmoothListViewListener(this);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem > 2) {
                    mFvFilter.setVisibility(View.VISIBLE);
                    isStickyTop = true;
                } else {
                    mFvFilter.setVisibility(View.GONE);
                    isStickyTop = false;
                }

                /*if (firstVisibleItem > filterViewPosition) {
                    isStickyTop = true;
                    mFvFilter.setVisibility(View.VISIBLE);
                }*/


                // 获取筛选View、距离顶部的高度
                if (itemHeaderFilterView == null) {
                    itemHeaderFilterView = mListView.getChildAt(filterViewPosition - firstVisibleItem);
                    Logger.d("itemHeaderFilterView == null");

                }
                if (itemHeaderFilterView != null) {
                    filterViewTopSpace = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop());
                    Logger.d(filterViewTopSpace);
                }

                if (isSmooth && isStickyTop) {
                    isSmooth = false;
                    mFvFilter.showFilterLayout(filterPosition);
                }

                mFvFilter.setStickyTop(isStickyTop);
            }
        });


    }

    // 填充数据
    private void fillAdapter(List<TravelingEntity> list) {
        if (list == null || list.size() == 0) {
            mListView.setLoadMoreEnable(false);
            int height = mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
            mTravelingAdapter.setData(ModelUtil.getNoDataEntity(height));
        } else {
            mListView.setLoadMoreEnable(list.size() > TravelingAdapter.ONE_REQUEST_COUNT);
            mTravelingAdapter.setData(list);
        }
    }


    @OnClick({R.id.iv_bg_search, R.id.ibtn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bg_search:
                break;
            case R.id.ibtn_back:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (listViewAdHeaderView != null) {
            listViewAdHeaderView.stopADRotate();
        }*/
    }

    @Override
    public void onBackPressed() {
        if (!mFvFilter.isShowing()) {
            super.onBackPressed();
        } else {
            mFvFilter.resetAllStatus();
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
