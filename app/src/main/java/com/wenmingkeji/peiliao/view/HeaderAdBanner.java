package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ImageAdapter;
import com.wenmingkeji.peiliao.model.HomeHeaderItem;
import com.wenmingkeji.peiliao.utils.DisplayTool;
import com.wenmingkeji.peiliao.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by cfanr on 2015/12/5.
 */
public class HeaderAdBanner extends HeaderInterface {

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
    @BindView(R.id.viewpager_ad)
    ViewPager mViewpagerAd;
    @BindView(R.id.ll_ad_banner_dots)
    LinearLayout mLlAdBannerDots;
    private int preDotPosition = 0; //上一个被选中的小圆点的索引，默认值为0
    private int currentItem = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (mHandler.hasMessages(MSG_UPDATE_IMAGE)) {
                mHandler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mViewpagerAd.setCurrentItem(currentItem);
                    //准备下次播放
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    };
    private Context context;
    private ImageAdapter imgAdapter;
    private DisplayTool mDisplayTool;
    private HomeHeaderItem mHomeHeaderItem;

    public HeaderAdBanner(Context context, HomeHeaderItem homeHeaderItem) {
        super(context);
        this.context = context;
        this.mHomeHeaderItem = homeHeaderItem;
        mDisplayTool = new DisplayTool(context);

    }


    @Override
    protected void getView(ListView listView) {
        View view = mInflate.inflate(R.layout.view_home_ad_banner, null);
        ButterKnife.bind(this, view);

        setViewPager();

        listView.addHeaderView(view);
    }


    private void setViewPager() {
        final int length = mHomeHeaderItem.getAdHome().getImgIds().length;

        if (imgAdapter == null) {//避免重复初始化ViewPager和addView

            View dot = null;
            LinearLayout.LayoutParams params = null;
            for (int i = 0; i < length; i++) {
                dot = new View(context);
                dot.setBackgroundResource(R.drawable.dot_bg_selector);
                params = new LinearLayout.LayoutParams(mDisplayTool.dip2px(5), mDisplayTool.dip2px(5));
                params.leftMargin = mDisplayTool.dip2px(10);
                dot.setEnabled(false);
                dot.setLayoutParams(params);
                mLlAdBannerDots.addView(dot); // 向线性布局中添加"点"
            }

            imgAdapter = new ImageAdapter(context, mHomeHeaderItem.getAdHome().getImgIds());
            imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ToastUtil.show("i am banner ad " + position);
                }
            });
            mViewpagerAd.setAdapter(imgAdapter);
            mViewpagerAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    mHandler.sendMessage(Message.obtain(mHandler, MSG_PAGE_CHANGED, position, 0));
                    // 取余后的索引，得到新的page的索引
                    int newPosition = position % mHomeHeaderItem.getAdHome().getImgIds().length;

                    // 把上一个点设置为被选中
                    mLlAdBannerDots.getChildAt(preDotPosition).setEnabled(false);
                    mLlAdBannerDots.getChildAt(newPosition).setEnabled(true);
                    preDotPosition = newPosition;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            mHandler.sendEmptyMessage(MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });

            mViewpagerAd.setCurrentItem(0);//默认在中间，使用户看不到边界
            mLlAdBannerDots.getChildAt(0).setEnabled(true);
            //开始轮播效果
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);

        }


    }

}
