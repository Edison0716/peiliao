package com.wenmingkeji.peiliao.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.MultipleItemQuickAdapter;
import com.wenmingkeji.peiliao.adaper.ViewPagerAdapter;
import com.wenmingkeji.peiliao.model.HomeItem;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    
    public static Main2Activity instance;
    MultipleItemQuickAdapter adapter;
    String[] channels = new String[]{"科技", "汽车", "互联网", "hackware.lucode.net", "奇闻异事", "搞笑", "段子", "趣图", "健康", "时尚", "教育", "星座", "育儿", "游戏", "家居", "美食", "旅游", "历史", "情感"};
    private List<HomeItem> mHomeItems = new ArrayList<HomeItem>();
    private ViewPager mPager;
    private List<String> mDataList = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


        for (int i = 0; i < channels.length; i++) {
            mDataList.add(channels[i]);
        }

        mPager = (ViewPager) findViewById(R.id.view_pager);


        int[] icons = {
                R.drawable.icon_common_food, R.drawable.icon_common_play,
                R.drawable.icon_common_draw, R.drawable.icon_common_life_consult,
                R.drawable.icon_common_cv, R.drawable.icon_common_lol,
                R.drawable.icon_common_sign, R.drawable.icon_common_pedict,
                R.drawable.icon_common_emotion_consult, R.drawable.icon_common_doutu

        };


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, icons);
        mPager.setAdapter(viewPagerAdapter);


        // 今日头条式
        final MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        final CommonNavigator commonNavigator = new CommonNavigator(this);

        commonNavigator.setFollowTouch(false);
        commonNavigator.setSkimOver(true);  // 跨多个item切换时中间的item呈现 "掠过" 效果
        commonNavigator.setRightPadding(UIUtil.dip2px(this, 50));
//        commonNavigator.setLeftPadding(UIUtil.dip2px(MainActivity.this, 80));
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;    // 没有指示器，因为title的指示作用已经很明显了
            }
        });

        magicIndicator.setNavigator(commonNavigator);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });

//        mPager.setCurrentItem(1);

    }
        
        
        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_service_item);
        initCategory();
        initServiceItem();


        adapter = new MultipleItemQuickAdapter(this, mHomeItems, getSupportFragmentManager());

        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
        adapter.addHeaderView(zoomView);
        
        if (recyclerView != null) {
            
            
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
        
        instance = this;

    }


    public void initServiceItem() {

        System.out.println("init...........");
        for (int i = 0; i < 5; i++) {
            System.out.println("generate........................");
            HomeItem homeItem = new HomeItem();
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setImgId(R.drawable.icon_common_food);
            serviceItem.setPrice("100");
            serviceItem.setServiceIntroduce("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
            serviceItem.setSkills("手绘");
            serviceItem.setUserName("呵呵呵");
            
//            homeItem.setServiceItemList(serviceItem);
            homeItem.setItemType(HomeItem.SERVICE_ITEM);
            mHomeItems.add(homeItem);
            System.out.println(i + "aaaaaaaaaaaaaaa");
        }


    }

    void initCategory() {
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

        HomeItem categoryItem = new HomeItem();
//        categoryItem.setItemType(ItemType.CATEGORY);
        NameWithIcon category = new NameWithIcon(names, icons);
        *//*for (int i = 0; i < icons.length; i++) {
            NameWithIcon nameWithIcon = new NameWithIcon(names[i], icons[i]);
            lists.add(nameWithIcon);
        }

        categoryItem.setNameWithIconList(lists);*//*

//        System.out.println(categoryItem.getNameWithIconList().size());
        categoryItem.setNameWithIcon(category);

        categoryItem.setItemType(HomeItem.CATEGORY);
        
        mHomeItems.add(categoryItem);

    }
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    private void initHeader() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(this).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        adapter.addHeaderView(scrollView);*/

       
}
