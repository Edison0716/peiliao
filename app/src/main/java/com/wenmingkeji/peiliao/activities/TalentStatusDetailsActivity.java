package com.wenmingkeji.peiliao.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.SectionsPagerAdapter;
import com.wenmingkeji.peiliao.fragment.TalentPersonalMsgFragment;
import com.wenmingkeji.peiliao.fragment.TalentStatusLikeFragment;
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
import butterknife.OnClick;

public class TalentStatusDetailsActivity extends AppCompatActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_next_step)
    ImageButton mTvNextStep;
    @BindView(R.id.icon_head)
    CircleImageView mIconHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_level)
    TextView mTvUserLevel;
    @BindView(R.id.iv_level_img)
    ImageView mIvLevelImg;
    @BindView(R.id.ibtn_follow)
    ImageButton mIbtnFollow;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    @BindView(R.id.textView33)
    TextView mTextView33;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.rl_preview)
    RelativeLayout mRlPreview;
    @BindView(R.id.iv_preview2)
    ImageView mIvPreview2;
    @BindView(R.id.iv_preview3)
    ImageView mIvPreview3;
    @BindView(R.id.ll_img_def)
    RelativeLayout mLlImgDef;
    @BindView(R.id.imageView9)
    ImageView mImageView9;
    @BindView(R.id.tv_tips)
    TextView mTvTips;
    @BindView(R.id.imageView10)
    ImageView mImageView10;
    @BindView(R.id.iv_comment_level)
    ImageView mIvCommentLevel;
    @BindView(R.id.textView37)
    TextView mTextView37;
    @BindView(R.id.tv_level_num)
    TextView mTvLevelNum;
    @BindView(R.id.textView38)
    TextView mTextView38;
    @BindView(R.id.rl_refer_more)
    RelativeLayout mRlReferMore;
    @BindView(R.id.ibtn_comment)
    ImageButton mIbtnComment;
    @BindView(R.id.ibtn_like)
    ImageButton mIbtnLike;
    @BindView(R.id.ibtn_donate)
    ImageButton mIbtnDonate;
    @BindView(R.id.ibtn_more)
    ImageButton mIbtnMore;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.head_layout)
    LinearLayout mHeadLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.main_vp_container)
    ViewPager mMainVpContainer;
    @BindView(R.id.root_layout)
    CoordinatorLayout mRootLayout;

    String[] channels = new String[]{"留言", "赞"};
    private List<String> mIndicatorList = new ArrayList<String>();
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_status_details);
        ButterKnife.bind(this);


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

        TalentStatusLikeFragment talentStatusLikeFragment = new TalentStatusLikeFragment();
        TalentPersonalMsgFragment talentPersonalMsgFragment = new TalentPersonalMsgFragment();

        mFragments.add(talentPersonalMsgFragment);
        mFragments.add(talentStatusLikeFragment);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mFragments);
        mMainVpContainer.setAdapter(mSectionsPagerAdapter);


        setMagicIndicator();
        
        
    }


    @OnClick({R.id.ibtn_follow, R.id.rl_refer_more, R.id.ibtn_comment, R.id.ibtn_like, R.id.ibtn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_follow:
                break;
            case R.id.rl_refer_more:
                break;
            case R.id.ibtn_comment:
                break;
            case R.id.ibtn_like:
                break;
            case R.id.ibtn_more:
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
