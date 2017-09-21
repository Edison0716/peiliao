package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bevis on 2016/7/7.
 */
public class MessageHomeFragment extends Fragment {

    private static final int MESSAGE = 1;
    private static final int NOTIFICATION = 2;
    private static final int GET_NOTIFICATION = 3;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.tv_notification)
    TextView mTvNotification;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.vp_message_home)
    ViewPager mVpMessageHome;
    @BindView(R.id.ll_message_msg_selected)
    LinearLayout mLlMessageMsgSelected;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE:

                    mTvMsg.setTextColor(getResources().getColor(R.color.colorMain));
                    mTvNotification.setTextColor(getResources().getColor(R.color.colorTextWhite));
                    mLlMessageMsgSelected.setBackgroundResource(R.drawable.bg_message_msg_selected);
                    break;
                case NOTIFICATION:
                    mTvMsg.setTextColor(getResources().getColor(R.color.colorTextWhite));
                    mTvNotification.setTextColor(getResources().getColor(R.color.colorMain));
                    mLlMessageMsgSelected.setBackgroundResource(R.drawable.bg_message_notification_selected);
                    break;
                case GET_NOTIFICATION:
                    System.out.println("msg received");
                    mVpMessageHome.setCurrentItem(0);
                    break;
            }
        }
    };
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private SectionsPagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_home, container, false);
        ButterKnife.bind(this, view);


        //初始化fragment
        final MsgFragment msgFragment = new MsgFragment();
        NotificationFragment notificationFragment = new NotificationFragment();

        mFragments.add(msgFragment);
        mFragments.add(notificationFragment);

        //初始化适配器
        mPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        //设置Viewpager
        mVpMessageHome.setAdapter(mPagerAdapter);
        mVpMessageHome.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    Message message = new Message();
                    message.what = MESSAGE;
                    mHandler.sendMessage(message);
                } else {
                    Message message = new Message();
                    message.what = NOTIFICATION;
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    @OnClick({R.id.tv_msg, R.id.tv_notification, R.id.ic_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_msg:

                mVpMessageHome.setCurrentItem(0);
                break;
            case R.id.tv_notification:
                mVpMessageHome.setCurrentItem(1);
                break;
            case R.id.ic_back:
                break;
        }
    }


    //ViewPager 适配器
    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
