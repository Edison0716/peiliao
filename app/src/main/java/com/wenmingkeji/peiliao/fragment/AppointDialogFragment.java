package com.wenmingkeji.peiliao.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.ServiceAppointActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bevis on 2016/7/1.
 */
public class AppointDialogFragment extends DialogFragment {


    @BindView(R.id.imageButton2)
    ImageButton mImageButton2;
    @BindView(R.id.tv_order_item_title)
    TextView mTvOrderItemTitle;
    @BindView(R.id.tv_service_price)
    TextView mTvServicePrice;
    @BindView(R.id.tv_service_stock)
    TextView mTvServiceStock;
    @BindView(R.id.ibtn_close)
    ImageButton mIbtnClose;
    @BindView(R.id.ibtn_order_minus)
    ImageButton mIbtnOrderMinus;
    @BindView(R.id.ibtn_order_plus)
    ImageButton mIbtnOrderPlus;
    @BindView(R.id.ibtn_appoint_now)
    ImageButton mIbtnAppointNow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_service_appointment, container, false);

        Dialog dialog = getDialog();
        Window dialogWindow = dialog.getWindow();
        //是状态栏透明
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置dialog 的背景颜色，否则无法铺满父级容器的宽度
        dialogWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorTextWhite)));

        //设置dialog 位置
        dialogWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER);

        //取消dialog 边距
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        //设置dialog 的宽高度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);


        ButterKnife.bind(this, view);
        return view;

    }

    @OnClick({R.id.imageButton2, R.id.ibtn_close, R.id.ibtn_order_minus, R.id.ibtn_order_plus, R.id.ibtn_appoint_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton2:
                break;
            case R.id.ibtn_close:
                break;
            case R.id.ibtn_order_minus:
                break;
            case R.id.ibtn_order_plus:
                break;
            case R.id.ibtn_appoint_now:
                startActivity(new Intent(getActivity(), ServiceAppointActivity.class));
                break;
        }
    }
}
