package com.wenmingkeji.peiliao.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceAppointActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.icon_head)
    CircleImageView mIconHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_order_item_title)
    TextView mTvOrderItemTitle;
    @BindView(R.id.tv_service_price)
    TextView mTvServicePrice;
    @BindView(R.id.tv_service_stock)
    TextView mTvServiceStock;
    @BindView(R.id.ibtn_order_minus)
    ImageButton mIbtnOrderMinus;
    @BindView(R.id.tv_service_num)
    TextView mTvServiceNum;
    @BindView(R.id.ibtn_order_plus)
    ImageButton mIbtnOrderPlus;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.img_arrow_right)
    ImageView mImgArrowRight;
    @BindView(R.id.ibtn_order_commit)
    ImageButton mIbtnOrderCommit;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.et_input_message)
    EditText mEtInputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_appointment);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.ic_back, R.id.ibtn_order_minus, R.id.tv_service_num, R.id.ibtn_order_plus, R.id.ibtn_order_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.ibtn_order_minus:
                break;
            case R.id.tv_service_num:
                break;
            case R.id.ibtn_order_plus:
                break;
            case R.id.ibtn_order_commit:
                startActivity(new Intent(this, ServicePayActivity.class));
                break;
        }
    }
}
