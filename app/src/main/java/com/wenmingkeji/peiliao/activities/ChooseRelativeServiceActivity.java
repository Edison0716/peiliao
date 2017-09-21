package com.wenmingkeji.peiliao.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.SectionsPagerAdapter;
import com.wenmingkeji.peiliao.fragment.ServiceListFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseRelativeServiceActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.vp_choose_relative_service)
    ViewPager mVpChooseRelativeService;


    String[] channels = new String[]{"我的服务", "我购买的服务"};
    private List<String> mIndicatorList = new ArrayList<String>();
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_relative_service);
        ButterKnife.bind(this);

        ServiceListFragment fragment1 = new ServiceListFragment();
        ServiceListFragment fragment2 = new ServiceListFragment();

        mFragments.add(fragment1);
        mFragments.add(fragment2);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mFragments);
        mVpChooseRelativeService.setAdapter(mSectionsPagerAdapter);

        setMagicIndicator();

    }

    @OnClick({R.id.ic_back, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_save:
                break;
        }
    }


    void setMagicIndicator() {


        //初始化指示器内容
        for (int i = 0; i < channels.length; i++) {
            mIndicatorList.add(channels[i]);
        }


        /*// 贝塞尔曲线
        final CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mIndicatorList == null ? 0 : mIndicatorList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView colorTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setText(mIndicatorList.get(index));
                colorTransitionPagerTitleView.setTextSize(18);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMainVpContainer.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                List<String> colorList = new ArrayList<String>();
                colorList.add("#ff4a42");
                colorList.add("#fcde64");
                colorList.add("#73e8f4");
                colorList.add("#76b0ff");
                colorList.add("#c683fe");
                indicator.setColorList(colorList);
                return indicator;
            }
        });*/


        final CommonNavigator commonNavigator3 = new CommonNavigator(this);
        commonNavigator3.setAdjustMode(true);  // 自适应模式
        commonNavigator3.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mIndicatorList == null ? 0 : mIndicatorList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setText(mIndicatorList.get(index));
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpChooseRelativeService.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                List<String> colorList = new ArrayList<String>();
                colorList.add("#fcde64");
                indicator.setColorList(colorList);
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                if (index == 2) {
                    return 1.5f;
                } else {
                    return 1;
                }
            }
        });
//        magic_indicator3.setNavigator(commonNavigator3);

        mMagicIndicator.setNavigator(commonNavigator3);


        mVpChooseRelativeService.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mMagicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mMagicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mMagicIndicator.onPageScrollStateChanged(state);
            }
        });

    }
}
