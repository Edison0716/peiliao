package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ViewPagerAdapter;
import com.wenmingkeji.peiliao.model.NameWithIcon;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by bevis on 2016/7/6.
 */
public class HeaderBanner {

    public View view;
    Context context;
    ViewPager mViewPager;
    CircleIndicator mIndicator;
    
    public HeaderBanner(Context context, NameWithIcon nameWithIcon) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.view_service_details_view_pager, null);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
        setViewPager(nameWithIcon);
    }

    private void setViewPager(NameWithIcon nameWithIcon) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(context, nameWithIcon.getIcons());
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
        //默认在中间，使用户看不到边界
        mViewPager.setCurrentItem(0);


    }


}
