package com.wenmingkeji.peiliao.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.fragment.GoodsAllFragment;
import com.wenmingkeji.peiliao.fragment.GoodsAssessFragment;
import com.wenmingkeji.peiliao.fragment.GoodsConfirmFragment;
import com.wenmingkeji.peiliao.fragment.GoodsOrderTakingFragment;
import com.wenmingkeji.peiliao.fragment.GoodsRefundFragment;
import com.wenmingkeji.peiliao.fragment.GoodsWaitingforPayFragment;

import java.util.ArrayList;
import java.util.List;

public class HadPaidActivity extends AppCompatActivity {
    private ImageButton ib_back;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    List<Fragment> viewContainter = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhadpaid);
        initBack();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        Intent intent=getIntent();
        String tmp = intent.getStringExtra("flag");
        int result = Integer.valueOf(tmp);
        switch (result){
            case 0 :
                mViewPager.setCurrentItem(0);
                break;
            case 1 :
                mViewPager.setCurrentItem(1);
                break;
            case 2 :
                mViewPager.setCurrentItem(2);
                break;
            case 3 :
                mViewPager.setCurrentItem(3);
                break;
            case 4 :
                mViewPager.setCurrentItem(4);
                break;
            case 5 :
                mViewPager.setCurrentItem(5);
                break;
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        GoodsAllFragment GoodsAllFragment = new GoodsAllFragment();
        GoodsAssessFragment AssessActivity = new GoodsAssessFragment();
        GoodsConfirmFragment ConfirmActivity = new GoodsConfirmFragment();
        GoodsOrderTakingFragment OrderTakingActivity = new GoodsOrderTakingFragment();
        GoodsWaitingforPayFragment ForPayActivity = new GoodsWaitingforPayFragment();
        GoodsRefundFragment RefundActivity = new GoodsRefundFragment();
        viewContainter.add(GoodsAllFragment);
        viewContainter.add(AssessActivity);
        viewContainter.add(ConfirmActivity);
        viewContainter.add(OrderTakingActivity);
        viewContainter.add(ForPayActivity);
        viewContainter.add(RefundActivity);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return viewContainter.get(position);
        }
        @Override
        public int getCount() {
            return 6;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部";
                case 1:
                    return "待支付";
                case 2:
                    return "待接单";
                case 3:
                    return "待确认";
                case 4:
                    return "待评价";
                case 5:
                    return "待退款";
            }
            return null;
        }
    }
    private void initBack() {
        ib_back = (ImageButton) findViewById(R.id.ic_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }
}
