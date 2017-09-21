package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.TimeManagerSectionAdapter;
import com.wenmingkeji.peiliao.model.TimeItem;
import com.wenmingkeji.peiliao.model.TimeManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeManagerActivity extends AppCompatActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.iv_mon_normal)
    ImageButton mIvMonNormal;
    @BindView(R.id.iv_tue_normal)
    ImageView mIvTueNormal;
    @BindView(R.id.iv_wed_normal)
    ImageView mIvWedNormal;
    @BindView(R.id.iv_thu_normal)
    ImageView mIvThuNormal;
    @BindView(R.id.iv_fri_normal)
    ImageView mIvFriNormal;
    @BindView(R.id.iv_sta_normal)
    ImageView mIvStaNormal;
    @BindView(R.id.iv_sun_normal)
    ImageView mIvSunNormal;
    @BindView(R.id.rv_time_select)
    RecyclerView mRvTimeSelect;


    TimeManagerSectionAdapter mAdapter;
    List<TimeManager> mTimeManagers = new ArrayList<TimeManager>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_manager);
        ButterKnife.bind(this);

        initDate();

        mAdapter = new TimeManagerSectionAdapter(R.layout.item_list_time_select, R.layout.item_list_time_banner, mTimeManagers);
        mRvTimeSelect.setLayoutManager(new LinearLayoutManager(this));
        mRvTimeSelect.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Logger.d(i);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_time_morning);
                if (imageView.isSelected()) {
                    imageView.setSelected(false);
                } else {
                    imageView.setSelected(true);
                }


            }
        });

    }


    void initDate() {

        TimeManager timeManager1 = new TimeManager(true, "星期一");
        mTimeManagers.add(timeManager1);
        TimeItem timeItem1 = new TimeItem(false, false, false);
        TimeManager timeManager11 = new TimeManager(timeItem1);
        mTimeManagers.add(timeManager11);

        TimeManager timeManager2 = new TimeManager(true, "星期二");
        mTimeManagers.add(timeManager2);
        TimeItem timeItem2 = new TimeItem(false, false, false);
        TimeManager timeManager22 = new TimeManager(timeItem2);
        mTimeManagers.add(timeManager22);

        TimeManager timeManager3 = new TimeManager(true, "星期三");
        mTimeManagers.add(timeManager3);
        TimeItem timeItem3 = new TimeItem(false, false, false);
        TimeManager timeManager33 = new TimeManager(timeItem3);
        mTimeManagers.add(timeManager33);

        TimeManager timeManager4 = new TimeManager(true, "星期四");
        mTimeManagers.add(timeManager4);
        TimeItem timeItem4 = new TimeItem(false, false, false);
        TimeManager timeManager44 = new TimeManager(timeItem4);
        mTimeManagers.add(timeManager44);

        TimeManager timeManager5 = new TimeManager(true, "星期五");
        mTimeManagers.add(timeManager5);
        TimeItem timeItem5 = new TimeItem(false, false, false);
        TimeManager timeManager55 = new TimeManager(timeItem5);
        mTimeManagers.add(timeManager55);

        TimeManager timeManager6 = new TimeManager(true, "星期六");
        mTimeManagers.add(timeManager6);
        TimeItem timeItem6 = new TimeItem(false, false, false);
        TimeManager timeManager66 = new TimeManager(timeItem6);
        mTimeManagers.add(timeManager66);

        TimeManager timeManager7 = new TimeManager(true, "星期日");
        mTimeManagers.add(timeManager7);
        TimeItem timeItem7 = new TimeItem(false, false, false);
        TimeManager timeManager77 = new TimeManager(timeItem7);
        mTimeManagers.add(timeManager77);
    }

    @OnClick({R.id.ic_back, R.id.tv_ok, R.id.iv_mon_normal, R.id.iv_tue_normal, R.id.iv_wed_normal, R.id.iv_thu_normal, R.id.iv_fri_normal, R.id.iv_sta_normal, R.id.iv_sun_normal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_ok:
                break;
            case R.id.iv_mon_normal:
                break;
            case R.id.iv_tue_normal:
                break;
            case R.id.iv_wed_normal:
                break;
            case R.id.iv_thu_normal:
                break;
            case R.id.iv_fri_normal:
                break;
            case R.id.iv_sta_normal:
                break;
            case R.id.iv_sun_normal:
                break;
        }
    }
}
