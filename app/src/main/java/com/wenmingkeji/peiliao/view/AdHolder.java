package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ImageAdapter;
import com.wenmingkeji.peiliao.model.HomeHeaderItem;
import com.wenmingkeji.peiliao.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by cfanr on 2015/12/5.
 */
public class AdHolder extends HeaderInterface {
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

    Context mContext;
    int currentItem = 0;
    ImageAdapter imgAdapter;

    HomeHeaderItem mHomeHeaderItem;


    @BindView(R.id.view_pager_common)
    ViewPager mViewPagerCommon;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (handler.hasMessages(MSG_UPDATE_IMAGE)) {
                handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mViewPagerCommon.setCurrentItem(currentItem);
                    //准备下次播放
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
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


    public AdHolder(Context context, HomeHeaderItem homeHeaderItem) {
        super(context);
        this.mHomeHeaderItem = homeHeaderItem;
        this.mContext = context;

    }

    @Override
    protected void getView(ListView listView) {
        View view = mInflate.inflate(R.layout.view_common_pager, null);
        ButterKnife.bind(this, view);

        setViewPager();
        listView.addHeaderView(view);
    }

    public void setViewPager() {
        if (imgAdapter == null) {
            imgAdapter = new ImageAdapter(mContext, mHomeHeaderItem.getAdHome().getImgIds());
            mViewPagerCommon.setAdapter(imgAdapter);
            mViewPagerCommon.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    handler.sendMessage(Message.obtain(handler, MSG_PAGE_CHANGED, position, 0));
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            handler.sendEmptyMessage(MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });
            mViewPagerCommon.setCurrentItem(0);//默认在中间，使用户看不到边界
            //开始轮播效果
            handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);

            imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ToastUtil.show("i am ad " + position);
                }
            });
        }
    }

}
