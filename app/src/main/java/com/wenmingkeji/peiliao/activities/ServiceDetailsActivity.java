package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.DockGridAdapter;
import com.wenmingkeji.peiliao.adaper.ServiceDetailsAdapter;
import com.wenmingkeji.peiliao.adaper.ViewPagerAdapter;
import com.wenmingkeji.peiliao.fragment.AppointDialogFragment;
import com.wenmingkeji.peiliao.model.HomeItem;
import com.wenmingkeji.peiliao.model.NameWithIcon;
import com.wenmingkeji.peiliao.model.ServiceItem;
import com.wenmingkeji.peiliao.view.HeaderBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class ServiceDetailsActivity extends AppCompatActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.rv_service_details)
    RecyclerView mRvServiceDetails;
    @BindView(R.id.ibtn_whisper)
    ImageButton mIbtnWhisper;
    @BindView(R.id.ibtn_message)
    ImageButton mIbtnMessage;
    @BindView(R.id.ibtn_collection)
    ImageButton mIbtnCollection;
    @BindView(R.id.ibtn_buy_now)
    ImageButton mIbtnBuyNow;
    ServiceDetailsAdapter mQuickAdapter;
    List<HomeItem> mHomeItems;
    String[] names = {
            "美食", "陪聊陪玩", "手绘", "生活咨询", "声优", "LOL",
            "签名设计", "占卜", "情感咨询", "斗图"
    };
    int[] icons = {
            R.drawable.icon_common_food, R.drawable.icon_common_play,
            R.drawable.icon_common_draw, R.drawable.icon_common_life_consult,
            R.drawable.icon_common_cv, R.drawable.icon_common_lol,
            R.drawable.icon_common_sign, R.drawable.icon_common_pedict,
            R.drawable.icon_common_emotion_consult, R.drawable.icon_common_doutu

    };
    private ViewPager viewPager;
    private CircleIndicator mCircleIndicator;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);
        
        //初始化底部
        NameWithIcon nameWithIcon = new NameWithIcon(names, icons);
        mHomeItems = new ArrayList<HomeItem>();

        DockGridAdapter adapter = new DockGridAdapter(this, nameWithIcon);

        initView();
        mQuickAdapter = new ServiceDetailsAdapter(this, mHomeItems);
        //初始化header
        initHeaderBanner();
        mRvServiceDetails.setLayoutManager(new LinearLayoutManager(this));
        mRvServiceDetails.setAdapter(mQuickAdapter);
        
        
    }


   

    void initView() {
        
        ServiceItem serviceItem = new ServiceItem();
        serviceItem.setImgId(R.drawable.icon_common_food);
        serviceItem.setPrice("100");
        serviceItem.setServiceIntroduce("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        serviceItem.setSkills("手绘");
        serviceItem.setUserName("呵呵呵");
        
        
        HomeItem homeItem = new HomeItem();
        homeItem.setServiceItem(serviceItem);
        homeItem.setItemType(HomeItem.USER);
        mHomeItems.add(homeItem);
        
        HomeItem homeItem1 = new HomeItem();
        homeItem1.setServiceItem(serviceItem);
        homeItem1.setItemType(HomeItem.PRICE);
        mHomeItems.add(homeItem1);
        
        HomeItem homeItem2 = new HomeItem();
        homeItem2.setItemType(HomeItem.DISCUSSION_BANNER);
        mHomeItems.add(homeItem2);


        HomeItem homeItem3 = new HomeItem();
        homeItem3.setItemType(HomeItem.COMMENT_BANNER);
        mHomeItems.add(homeItem3);

        HomeItem homeItem4 = new HomeItem();
        homeItem4.setItemType(HomeItem.COMMENT);
        mHomeItems.add(homeItem4);

        HomeItem homeItem6 = new HomeItem();
        homeItem6.setItemType(HomeItem.COMMENT);
        mHomeItems.add(homeItem6);

        HomeItem homeItem5 = new HomeItem();
        homeItem5.setItemType(HomeItem.DISCUSSION);
        mHomeItems.add(homeItem5);
        
    }

    
    
    
    
    void initHeaderBanner() {
        NameWithIcon nameWithIcon = new NameWithIcon(names,icons);
        HeaderBanner banner = new HeaderBanner(this, nameWithIcon);
        mQuickAdapter.addHeaderView(banner.view);
    }


    
    
    
    @OnClick({R.id.ibtn_whisper, R.id.ibtn_message, R.id.ibtn_collection, R.id.ibtn_buy_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_whisper:
                break;
            case R.id.ibtn_message:
                break;
            case R.id.ibtn_collection:
                break;
            case R.id.ibtn_buy_now:

                AppointDialogFragment dialogFragment = new AppointDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "ServiceDetailsActivity");
                break;
        }
    }
}
