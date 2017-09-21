package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ImageAdapter;
import com.wenmingkeji.peiliao.adaper.SectionsPagerAdapter;
import com.wenmingkeji.peiliao.fragment.CategoryFragment;
import com.wenmingkeji.peiliao.model.Ad;
import com.wenmingkeji.peiliao.model.HomeHeaderItem;
import com.wenmingkeji.peiliao.utils.DisplayTool;
import com.wenmingkeji.peiliao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/24.
 */
public class HomeHeader {

    //请求更新显示的View
    protected static final int MSG_UPDATE_IMAGE = 1;
    //请求暂停轮播
    protected static final int MSG_KEEP_SILENT = 2;
    //请求恢复轮播
    protected static final int MSG_BREAK_SILENT = 3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    protected static final int MSG_PAGE_CHANGED = 4;
    //轮播间隔时间
    protected static final long MSG_DELAY = 3000;
    /**
     * 全局的view
     */
    public View mView;
    @BindView(R.id.vp_home_ad_banner)
    ViewPager mVpHomeAdBanner;
    @BindView(R.id.ll_ad_banner_dots)
    LinearLayout mLlAdBannerDots;
    @BindView(R.id.container)
    RelativeLayout mContainer;
    @BindView(R.id.vp_home_category)
    ViewPager mVpHomeCategory;
    @BindView(R.id.vp_home_ad)
    ViewPager mVpHomeAd;
    DisplayTool mDisplayTool;
    //缓存
    ViewHolder holder;
    private int preDotPosition = 0; //上一个被选中的小圆点的索引，默认值为0
    private int currentAdBannerItem = 0;
    Handler mAdBannerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (mAdBannerHandler.hasMessages(MSG_UPDATE_IMAGE)) {
                mAdBannerHandler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentAdBannerItem++;
                    holder.mVpHomeAdBanner.setCurrentItem(currentAdBannerItem);
                    //准备下次播放
                    mAdBannerHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    mAdBannerHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentAdBannerItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    };
    private int currentAdItem = 0;
    Handler mAdHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (mAdHandler.hasMessages(MSG_UPDATE_IMAGE)) {
                mAdHandler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentAdItem++;
                    holder.mVpHomeAd.setCurrentItem(currentAdItem);
                    //准备下次播放
                    mAdHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    mAdHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentAdItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    };
    private Context mContext;
    private LayoutInflater mInflater;
    /**
     * Fragment管理器，用于初始化mPagerAdapter
     */
    private FragmentManager mManager;
    /**
     * 头部的数据结构
     */
    private HomeHeaderItem mHomeHeaderItem;
    //图片轮播适配器
    private ImageAdapter imgAdapter;
    private ImageAdapter mAdAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    //fragment适配器
    private SectionsPagerAdapter mPagerAdapter;


    public HomeHeader(Context context, FragmentManager manager, HomeHeaderItem homeHeaderItem) {
        this.mContext = context;
        this.mManager = manager;
        this.mHomeHeaderItem = homeHeaderItem;
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.view_home_header, null);

        mDisplayTool = new DisplayTool(context);
        holder = new ViewHolder(mView);


        //设置首页轮播广告
        setADBanner();

        //设置首页分类
        setCategory();

        //设置轮播广告
        setAD();

    }

    private void setAD() {
        final Ad ad = mHomeHeaderItem.getAd();
        if (mAdAdapter == null) {
            mAdAdapter = new ImageAdapter(mContext, ad.getImgIds());
            holder.mVpHomeAd.setAdapter(mAdAdapter);
            holder.mVpHomeAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    mAdHandler.sendMessage(Message.obtain(mAdHandler, MSG_PAGE_CHANGED, position, 0));
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            mAdHandler.sendEmptyMessage(MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            mAdHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });
            holder.mVpHomeAd.setCurrentItem(0);//默认在中间，使用户看不到边界
            //开始轮播效果
            mAdHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);


            mAdAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ToastUtil.show("i am ad " + position);
                }
            });
        }
    }

    private void setCategory() {
        initView();
        mPagerAdapter = new SectionsPagerAdapter(mManager, mFragments);
        holder.mVpHomeCategory.setAdapter(mPagerAdapter);

    }

    private void setADBanner() {
        final int length = mHomeHeaderItem.getAdHome().getImgIds().length;

        if (imgAdapter == null) {//避免重复初始化ViewPager和addView

            View dot = null;
            LinearLayout.LayoutParams params = null;
            for (int i = 0; i < length; i++) {
                dot = new View(mContext);
                dot.setBackgroundResource(R.drawable.dot_bg_selector);
                params = new LinearLayout.LayoutParams(mDisplayTool.dip2px(5), mDisplayTool.dip2px(5));
                params.leftMargin = mDisplayTool.dip2px(10);
                dot.setEnabled(false);
                dot.setLayoutParams(params);
                holder.mLlAdBannerDots.addView(dot); // 向线性布局中添加"点"
            }

            imgAdapter = new ImageAdapter(mContext, mHomeHeaderItem.getAdHome().getImgIds());
            imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ToastUtil.show("i am banner ad " + position);
                }
            });
            holder.mVpHomeAdBanner.setAdapter(imgAdapter);
            holder.mVpHomeAdBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    mAdBannerHandler.sendMessage(Message.obtain(mAdBannerHandler, MSG_PAGE_CHANGED, position, 0));
                    // 取余后的索引，得到新的page的索引
                    int newPosition = position % mHomeHeaderItem.getAdHome().getImgIds().length;

                    // 把上一个点设置为被选中
                    holder.mLlAdBannerDots.getChildAt(preDotPosition).setEnabled(false);
                    holder.mLlAdBannerDots.getChildAt(newPosition).setEnabled(true);
                    preDotPosition = newPosition;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            mAdBannerHandler.sendEmptyMessage(MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            mAdBannerHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });

            holder.mVpHomeAdBanner.setCurrentItem(0);//默认在中间，使用户看不到边界
            holder.mLlAdBannerDots.getChildAt(0).setEnabled(true);
            //开始轮播效果
            mAdBannerHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);

        }

    }

    void initView() {
        CategoryFragment fragment1 = CategoryFragment.newInstance(mHomeHeaderItem.getNameWithIcons().get(0));
        CategoryFragment fragment2 = CategoryFragment.newInstance(mHomeHeaderItem.getNameWithIcons().get(1));

        mFragments.add(fragment1);
        mFragments.add(fragment2);

    }

    static class ViewHolder {
        @BindView(R.id.vp_home_ad_banner)
        ViewPager mVpHomeAdBanner;
        @BindView(R.id.ll_ad_banner_dots)
        LinearLayout mLlAdBannerDots;
        @BindView(R.id.container)
        RelativeLayout mContainer;
        @BindView(R.id.vp_home_category)
        ViewPager mVpHomeCategory;
        @BindView(R.id.vp_home_ad)
        ViewPager mVpHomeAd;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
