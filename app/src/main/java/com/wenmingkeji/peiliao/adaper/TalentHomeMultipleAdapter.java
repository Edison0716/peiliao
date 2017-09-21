package com.wenmingkeji.peiliao.adaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.TalentHomeItem;

import java.util.List;

/**
 * Created by bevis on 2016/7/15.
 */
public class TalentHomeMultipleAdapter extends BaseMultiItemQuickAdapter<TalentHomeItem> {

    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mAdapter;

    public TalentHomeMultipleAdapter(List<TalentHomeItem> data, FragmentManager manager) {
        super(data);
        this.mFragmentManager = manager;
        addItemType(TalentHomeItem.INDICATOR, R.layout.view_talent_home_indicator);
//        addItemType(TalentHomeItem.CONTENT, R.layout.view_talent_content_view_pager);
        addItemType(TalentHomeItem.CONTENT, R.layout.item_list_talent_content);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TalentHomeItem talentHomeItem) {

        switch (baseViewHolder.getItemViewType()) {
            case TalentHomeItem.CONTENT:

                /*System.out.println("test................");
                if (mViewPager == null) {
                    mViewPager = (ViewPager) baseViewHolder.getConvertView().findViewById(R.id.vp_talent_content);
                }

                ArrayList<User> users = new ArrayList<User>();

                //初始化mFragments
                mFragments = new ArrayList<Fragment>();

                //添加fragment 到mFragments
                TalentContentFragment f1 = TalentContentFragment.newInstance(users);
                mFragments.add(f1);

                TalentContentFragment f2 = TalentContentFragment.newInstance(users);
                mFragments.add(f2);

                TalentContentFragment f3 = TalentContentFragment.newInstance(users);
                mFragments.add(f3);

                mAdapter = new SectionsPagerAdapter(mFragmentManager, mFragments);
                mViewPager.setAdapter(mAdapter);
                */

                //设置头像的点击事件
                baseViewHolder.setOnClickListener(R.id.icon_head, new OnItemChildClickListener());
                
                break;

            case TalentHomeItem.INDICATOR:
                baseViewHolder.setOnClickListener(R.id.tv_hotter, new OnItemChildClickListener())
                        .setOnClickListener(R.id.tv_latest, new OnItemChildClickListener())
                        .setOnClickListener(R.id.tv_follow, new OnItemChildClickListener());
                break;
        }

    }


}

