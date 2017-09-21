package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.DockGridAdapter;
import com.wenmingkeji.peiliao.fragment.HomePageFragment;
import com.wenmingkeji.peiliao.fragment.MessageHomeFragment;
import com.wenmingkeji.peiliao.fragment.MineFragment;
import com.wenmingkeji.peiliao.fragment.ServicePublishFragment1;
import com.wenmingkeji.peiliao.fragment.TalentFragment;
import com.wenmingkeji.peiliao.model.NameWithIcon;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity {
    public static HomeActivity instacne;

    /**
     * R.drawable.icon_common_food, R.drawable.icon_common_emotion_consult,
     * R.drawable.icon_common_food, R.drawable.icon_common_emotion_consult,
     */


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
    @BindView(R.id.tv_recommend)
    TextView mTvRecommend;
    @BindView(R.id.tv_latest)
    TextView mTvLatest;
    @BindView(R.id.tv_today)
    TextView mTvToday;
    @BindView(R.id.tv_hot)
    TextView mTvHot;
    @BindView(R.id.item_dock_grid_view)
    GridView mItemDockGridView;

    private DockGridAdapter mGridAdapter;
    private NameWithIcon mNameWithIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instacne = this;
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initDock();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("TAG")
                    .add(R.id.home_container, new HomePageFragment())
                    .commit();

        }


        mItemDockGridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initDock() {

        //初始化Dock栏

        //获取数据
        mNameWithIcon = getDockList();

        //新建适配器
        mGridAdapter = new DockGridAdapter(this, mNameWithIcon);

        //设置适配器
        mItemDockGridView.setAdapter(mGridAdapter);

        mGridAdapter.setSelectedPosition(0);
        mItemDockGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {

                    case 0:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_container, new HomePageFragment())
                                .commit();
                        break;

                    case 1:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_container, new TalentFragment())
                                .commit();
                        break;

                    case 2:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_container, new MessageHomeFragment())
                                .commit();
                        break;

                    case 3:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_container, new MineFragment())
                                .commit();
                        break;
                }
                mGridAdapter.setSelectedPosition(position);
                mGridAdapter.notifyDataSetChanged();
            }
        });

    }

    //初始化Dock 栏
    public NameWithIcon getDockList() {

        //初始化图标文字数据
        int[] dockIcons = {
                R.drawable.icon_home_main, R.drawable.icon_home_talent,
                R.drawable.icon_home_message, R.drawable.icon_home_me
        };
        String[] dockNames = {"首页", "牛人", "消息", "我的"};

        mNameWithIcon = new NameWithIcon(dockNames, dockIcons);
        return mNameWithIcon;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        instacne = null;
    }
}
