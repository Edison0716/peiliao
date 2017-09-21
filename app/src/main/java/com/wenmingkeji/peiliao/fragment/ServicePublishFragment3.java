package com.wenmingkeji.peiliao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.TimeManagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bevis on 2016/7/11.
 */
public class ServicePublishFragment3 extends Fragment {

    //设置控件的默认状态
    private static boolean SERVICE_TYPE_GO_SELECTED = false;
    private static boolean SERVICE_TYPE_COME_SELECTED = false;
    private static boolean SERVICE_TYPE_ONLINE_SELECTED = false;
    private static boolean SERVICE_TYPE_POST_SELECTED = false;
    private static boolean SERVICE_RANGER_LIMITI = false;
    private static boolean SERVICE_RANGER_ANYWHERE = false;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_finish)
    TextView mTvFinish;
    @BindView(R.id.iv_go)
    ImageView mIvGo;
    @BindView(R.id.iv_come)
    ImageView mIvCome;
    @BindView(R.id.iv_online)
    ImageView mIvOnline;
    @BindView(R.id.iv_post)
    ImageView mIvPost;
    @BindView(R.id.tv_range_limit)
    TextView mTvRangeLimit;
    @BindView(R.id.tv_range_anywhere)
    TextView mTvRangeAnywhere;
    @BindView(R.id.ll_choose_time)
    LinearLayout mLlChooseTime;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_publish_3, null, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.ic_back, R.id.tv_finish, R.id.iv_go, R.id.iv_come, R.id.iv_online, R.id.iv_post, R.id.ll_choose_time, R.id.tv_range_limit, R.id.tv_range_anywhere})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_finish:
                break;
            case R.id.iv_go:

                /**
                 * 判断selector是否被选中，标志量为true，则改为false
                 * 为false，则改为true，
                 * 总之就是改变当前的状态
                 */

                if (SERVICE_TYPE_GO_SELECTED) {
                    SERVICE_TYPE_GO_SELECTED = false;
                    mIvGo.setSelected(false);

                } else {
                    SERVICE_TYPE_GO_SELECTED = true;
                    mIvGo.setSelected(true);
                }

                break;
            case R.id.iv_come:

                if (SERVICE_TYPE_COME_SELECTED) {
                    SERVICE_TYPE_COME_SELECTED = false;
                    mIvCome.setSelected(false);

                } else {
                    SERVICE_TYPE_COME_SELECTED = true;
                    mIvCome.setSelected(true);
                }

                break;
            case R.id.iv_online:
                if (SERVICE_TYPE_ONLINE_SELECTED) {
                    SERVICE_TYPE_ONLINE_SELECTED = false;
                    mIvOnline.setSelected(false);

                } else {
                    SERVICE_TYPE_ONLINE_SELECTED = true;
                    mIvOnline.setSelected(true);
                }
                break;
            case R.id.iv_post:

                if (SERVICE_TYPE_POST_SELECTED) {
                    SERVICE_TYPE_POST_SELECTED = false;
                    mIvPost.setSelected(false);

                } else {
                    SERVICE_TYPE_POST_SELECTED = true;
                    mIvPost.setSelected(true);
                }

                break;
            case R.id.ll_choose_time:
                startActivity(new Intent(getActivity(), TimeManagerActivity.class));
                break;
            case R.id.tv_range_limit:


//                mTvRangeLimit.setSelected(true);
                if (SERVICE_RANGER_LIMITI) {
                    SERVICE_RANGER_LIMITI = false;
                    mTvRangeLimit.setSelected(false);
                } else {
                    SERVICE_RANGER_LIMITI = true;
                    mTvRangeLimit.setSelected(true);
                }
                break;
            case R.id.tv_range_anywhere:

                if (SERVICE_RANGER_ANYWHERE) {
                    SERVICE_RANGER_ANYWHERE = false;
                    mTvRangeAnywhere.setSelected(false);
                } else {
                    SERVICE_RANGER_ANYWHERE = true;
                    mTvRangeAnywhere.setSelected(true);
                }
                break;
        }
    }

    
    
}
