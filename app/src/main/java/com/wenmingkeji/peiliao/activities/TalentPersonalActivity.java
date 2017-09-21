package com.wenmingkeji.peiliao.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.CommonTabPagerAdapter;
import com.wenmingkeji.peiliao.adaper.ChadQuickAdapter;
import com.wenmingkeji.peiliao.adaper.SectionsPagerAdapter;
import com.wenmingkeji.peiliao.adaper.TalentPersonalHomeAdapter;
import com.wenmingkeji.peiliao.fragment.TalentPersonalIntroduceFragment;
import com.wenmingkeji.peiliao.fragment.TalentPersonalMsgFragment;
import com.wenmingkeji.peiliao.fragment.TalentPersonalStatusFragment;
import com.wenmingkeji.peiliao.model.User;
import com.wenmingkeji.peiliao.view.CircleImageView;

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
import jp.wasabeef.glide.transformations.BlurTransformation;

public class TalentPersonalActivity extends AppCompatActivity implements CommonTabPagerAdapter.TabPagerListener {


    @BindView(R.id.icon_head)
    CircleImageView mIconHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_level)
    TextView mTvUserLevel;
    @BindView(R.id.iv_level_img)
    ImageView mIvLevelImg;
    @BindView(R.id.tv_register_time)
    TextView mTvRegisterTime;
    @BindView(R.id.tv_visitor_num)
    TextView mTvVisitorNum;
    @BindView(R.id.tv_purchase_num)
    TextView mTvPurchaseNum;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.main_vp_container)
    ViewPager mMainVpContainer;
    @BindView(R.id.root_layout)
    CoordinatorLayout mRootLayout;
    @BindView(R.id.head_layout)
    LinearLayout mHeadLayout;
    @BindView(R.id.rv_service_career)
    RecyclerView mRvServiceCareer;
    @BindView(R.id.img_servcie_preview)
    CircleImageView mImgServciePreview;
    @BindView(R.id.tv_service_name)
    TextView mTvServiceName;
    @BindView(R.id.iv_level)
    ImageView mIvLevel;
    @BindView(R.id.tv_level_num)
    TextView mTvLevelNum;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_person_appointment)
    TextView mTvPersonAppointment;
    @BindView(R.id.tv_service_sum)
    TextView mTvServiceSum;
    @BindView(R.id.rl_header)
    RelativeLayout mRlHeader;
    List<User> mUsers = new ArrayList<User>();

    CommonTabPagerAdapter mTabPagerAdapter;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    //定义viewpager  指示器
    String[] channels = new String[]{"动态", "留言", "我的简介"};
    /*@BindView(R.id.rv_talent_personal)
                RecyclerView mRvTalentPersonal;*/
    private LayoutInflater mInflater;
    private ChadQuickAdapter mAdapter;
    private SectionsPagerAdapter mPagerAdapter;
    private TalentPersonalHomeAdapter mPersonalHomeAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private List<String> mIndicatorList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_personal_main);
        ButterKnife.bind(this);


        //设置透明状态栏
        setTransparentStatus();

        //初始化职业内容
        initCareer();
        
        
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 设置标题
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mHeadLayout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle(mTvUserName.getText().toString());
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });


        //初始化viewpager
        TalentPersonalStatusFragment statusFragment = new TalentPersonalStatusFragment();
        TalentPersonalMsgFragment msgFragment = new TalentPersonalMsgFragment();
        TalentPersonalIntroduceFragment introduceFragment = new TalentPersonalIntroduceFragment();

        mFragments.add(statusFragment);
        mFragments.add(msgFragment);
        mFragments.add(introduceFragment);


        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mFragments);

        mMainVpContainer.setAdapter(mPagerAdapter);


        //设置指示器
        setMagicIndicator();

        //设置毛玻璃效果和沉浸状态栏
        loadBlurAndSetStatusBar();


    }

    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
//        StatusBarUtil.setTranslucent(MainActivity.this, 60);
//        StatusBarUtil.setTransparent(MainActivity.this);
        Glide.with(this).load(R.drawable.ad1).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mRlHeader.setBackground(resource);
//                        mRootLayout.setBackground(resource);
                    }
                });

        Glide.with(this).load(R.drawable.ad1).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mCollapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }


    void setTransparentStatus() {
        //透明状态栏  
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar  
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //透明状态栏  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }


    @Override
    public Fragment getFragment(int position) {
        return null;
    }


    void initCareer() {
        for (int i = 0; i < 3; i++) {
            User user = new User();
            mUsers.add(user);
        }

        mRvServiceCareer.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChadQuickAdapter(R.layout.item_list_career, mUsers);

        mRvServiceCareer.setAdapter(mAdapter);
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
                return mIndicatorList == null ? 0 : 3;
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
                        mMainVpContainer.setCurrentItem(index);
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


        mMainVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
