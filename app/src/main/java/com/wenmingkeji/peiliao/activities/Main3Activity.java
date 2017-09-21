package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.TravelingAdapter;
import com.wenmingkeji.peiliao.model.Ad;
import com.wenmingkeji.peiliao.model.FilterData;
import com.wenmingkeji.peiliao.model.NameAndIcon;
import com.wenmingkeji.peiliao.model.TravelingEntity;
import com.wenmingkeji.peiliao.view.AdHolder;
import com.wenmingkeji.peiliao.view.FilterView;
import com.wenmingkeji.peiliao.view.HeaderCategoryRecommend;
import com.wenmingkeji.peiliao.view.HeaderFilterViewView;
import com.wenmingkeji.peiliao.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {


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
    private HeaderCategoryRecommend mRecommendView;
    private Ad mAd;
    private AdHolder mAdHolder;
    private TravelingAdapter mAdapter; // 主页数据
    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据
    private HeaderFilterViewView headerFilterViewView; // 分类筛选视图
    private FilterData filterData; // 筛选数据
    //推荐列表数据
    private List<NameAndIcon> mNameAndIconList = new ArrayList<NameAndIcon>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ButterKnife.bind(this);


        /*//初始化推荐列表

        for (int i = 0; i < icons.length; i++) {
            NameAndIcon nameAndIcon = new NameAndIcon();
            nameAndIcon.setIcon(icons[i]);
            nameAndIcon.setName(names[i]);
            mNameAndIconList.add(nameAndIcon);
        }
        
        int[] imgIds = {R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};
        mAd = new Ad();
        mAd.setImgIds(imgIds);
        
        // ListView数据
        travelingList = ModelUtil.getTravelingData();
        mAdHolder = new AdHolder(this, mAd);
        mAdHolder.fillView(mListView);


        // 设置ListView数据
        mAdapter = new TravelingAdapter(this, travelingList);
        mListView.setAdapter(mAdapter);


        //设置推荐列表
        mRecommendView = new HeaderCategoryRecommend(this, mNameAndIconList);
        mRecommendView.fillView(mListView);
        
        
        // 设置筛选数据
        headerFilterViewView = new HeaderFilterViewView(this);
        headerFilterViewView.fillView(new Object(), mListView);
        
        
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > 2) {
                    mFvTopFilter.setVisibility(View.VISIBLE);
                } else {
                    mFvTopFilter.setVisibility(View.GONE);
                }
                
            }
        });*/

    }

}
