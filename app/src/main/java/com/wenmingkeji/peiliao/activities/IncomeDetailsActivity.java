package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.fragment.AllDetailsFragment;
import com.wenmingkeji.peiliao.fragment.ExpenditureFragment;
import com.wenmingkeji.peiliao.fragment.IncomeFragment;

import java.util.ArrayList;
import java.util.List;

public class IncomeDetailsActivity extends AppCompatActivity {
    private ImageButton ib_back;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    List<Fragment> viewContainter = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        AllDetailsFragment allDetailsFragment = new AllDetailsFragment();
        ExpenditureFragment expenditureFragment = new ExpenditureFragment();
        IncomeFragment incomeFragment = new IncomeFragment();
        viewContainter.add(allDetailsFragment);
        viewContainter.add(expenditureFragment);
        viewContainter.add(incomeFragment);
       initBack();

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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部";
                case 1:
                    return "收入";
                case 2:
                    return "支出";
            }
            return null;
        }
    }
}
