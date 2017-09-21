package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.SectionsPagerAdapter;
import com.wenmingkeji.peiliao.fragment.CategoryFragment;
import com.wenmingkeji.peiliao.model.HomeHeaderItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/27.
 */
public class HeaderViewPager extends HeaderInterface {
    @BindView(R.id.vp_header)
    ViewPager mVpHeader;


    private Context mContext;

    /**
     * Fragment管理器，用于初始化mPagerAdapter
     */
    private FragmentManager mManager;


    /**
     * 头部的数据结构
     */
    private HomeHeaderItem mHomeHeaderItem;

    private List<Fragment> mFragments = new ArrayList<Fragment>();


    //fragment适配器
    private SectionsPagerAdapter mPagerAdapter;

    public HeaderViewPager(Context context, HomeHeaderItem homeHeaderItem, FragmentManager manager) {
        super(context);
        this.mHomeHeaderItem = homeHeaderItem;
        this.mContext = context;
        this.mManager = manager;
    }

    @Override
    protected void getView(ListView listView) {
        View view = mInflate.inflate(R.layout.view_header_view_pager, null);
        ButterKnife.bind(this, view);

        setCategory();


        listView.addHeaderView(view);
    }


    void initView() {
        CategoryFragment fragment1 = CategoryFragment.newInstance(mHomeHeaderItem.getNameWithIcons().get(0));
        CategoryFragment fragment2 = CategoryFragment.newInstance(mHomeHeaderItem.getNameWithIcons().get(1));

        mFragments.add(fragment1);
        mFragments.add(fragment2);
    }

    private void setCategory() {
        initView();
        mPagerAdapter = new SectionsPagerAdapter(mManager, mFragments);
        mVpHeader.setAdapter(mPagerAdapter);
    }
}
