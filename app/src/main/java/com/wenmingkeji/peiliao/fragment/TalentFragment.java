package com.wenmingkeji.peiliao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.TalentPersonalActivity;
import com.wenmingkeji.peiliao.activities.TalentPublishActivity;
import com.wenmingkeji.peiliao.activities.TalentStatusDetailsActivity;
import com.wenmingkeji.peiliao.adaper.ChadQuickAdapter;
import com.wenmingkeji.peiliao.adaper.TalentHomeMultipleAdapter;
import com.wenmingkeji.peiliao.model.IndicatorText;
import com.wenmingkeji.peiliao.model.TalentHomeContent;
import com.wenmingkeji.peiliao.model.TalentHomeItem;
import com.wenmingkeji.peiliao.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bevis on 2016/7/7.
 */
public class TalentFragment extends Fragment {
    private static final int GET_NOTIFICATION = 3;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_msg)
    ImageButton mIcMsg;
    @BindView(R.id.ic_release)
    ImageButton mIcRelease;
    @BindView(R.id.rv_talent_home)
    RecyclerView mRvTalentHome;
    RecommendItemFragment fragment;
    //初始化牛人首页的数据结构
    List<TalentHomeItem> mTalentHomeItems = new ArrayList<TalentHomeItem>();
    private ChadQuickAdapter mChadQuickAdapter;
    private TalentHomeMultipleAdapter mMultipleAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talent_home, container, false);
        ButterKnife.bind(this, view);

        //获取fragment管理器
        FragmentManager fragmentManager = getChildFragmentManager();

        //将 推荐好友 这一布局作为fragment添加到RV的header里 
        View header = inflater.inflate(R.layout.fragment_talent_recommend, null);
        //防止fragment重复加载
        if (fragment == null) {
            fragment = (RecommendItemFragment) getChildFragmentManager().findFragmentById(R.id.frag_talent_recommend);
        }


        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 5; i++) {
            User user = new User();
            users.add(user);
        }


        //初始化指示器文本
        TalentHomeItem talentHomeItem = new TalentHomeItem();
        IndicatorText indicatorText = new IndicatorText();
        talentHomeItem.setItemType(TalentHomeItem.INDICATOR);
        talentHomeItem.setIndicatorText(indicatorText);
        mTalentHomeItems.add(talentHomeItem);
        mRvTalentHome.setHasFixedSize(true);

        //初始化内容列表
        for (int i = 0; i < 5; i++) {
            TalentHomeItem talentHomeItem1 = new TalentHomeItem();
            TalentHomeContent talentHomeContent = new TalentHomeContent();
            talentHomeItem1.setItemType(TalentHomeItem.CONTENT);
            talentHomeItem1.setTalentHomeContent(talentHomeContent);
            mTalentHomeItems.add(talentHomeItem1);
        }

       /* TalentHomeItem talentHomeItem1 = new TalentHomeItem();
        TalentHomeContent content = new TalentHomeContent();
        talentHomeItem1.setItemType(TalentHomeItem.CONTENT);
        talentHomeItem1.setTalentHomeContent(content);
        mTalentHomeItems.add(talentHomeItem1);*/

//        mQuickAdapter = new QuickAdapter(R.layout.item_list_talent_content, users);
        mMultipleAdapter = new TalentHomeMultipleAdapter(mTalentHomeItems, fragmentManager);
        mRvTalentHome.setLayoutManager(new LinearLayoutManager(getActivity()));

//        init();
        mRvTalentHome.setAdapter(mMultipleAdapter);
//        mQuickAdapter.addHeaderView(header);

        //为RV 设置header
        mMultipleAdapter.addHeaderView(header);


        mMultipleAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.tv_follow:

                        break;
                    case R.id.tv_latest:
                        break;

                    case R.id.tv_hotter:
                        break;

                    case R.id.icon_head:
                        startActivity(new Intent(getActivity(), TalentPersonalActivity.class));
                        break;
                }
            }
        });


        mMultipleAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                startActivity(new Intent(getActivity(), TalentStatusDetailsActivity.class));
            }
        });
        
        
        return view;
    }


    @OnClick({R.id.ic_msg, R.id.ic_release})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_msg:
                Handler handler = new Handler();
                handler.sendEmptyMessageAtTime(GET_NOTIFICATION, 0);
                getFragmentManager().beginTransaction()
                        .replace(R.id.home_container, new MessageHomeFragment())
                        .commit();

                
               
                
                break;
            case R.id.ic_release:
                startActivity(new Intent(getActivity(), TalentPublishActivity.class));
                break;
        }
    }
    
}
