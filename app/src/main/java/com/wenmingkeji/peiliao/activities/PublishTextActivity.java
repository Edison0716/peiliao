package com.wenmingkeji.peiliao.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.orhanobut.logger.Logger;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.MultiImageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishTextActivity extends AppCompatActivity {


    private final static int REQUEST_IMAGE = 3;
    private static final int WX_SELECTED = 1;
    private static final int QQ_SELECTED = 2;
    private static final int XLWB_SELECTED = 3;
    private static final int TXWB_SELECTED = 4;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_next_step)
    TextView mTvPublish;
    @BindView(R.id.rl_choose_service)
    RelativeLayout mRlChooseService;
    @BindView(R.id.grid_view_add_img)
    GridView mGridViewAddImg;
    @BindView(R.id.et_input_description)
    EditText mEtInputDescription;
    @BindView(R.id.tv_text_num)
    TextView mTvTextNum;
    @BindView(R.id.ibtn_sync_to_wx)
    ImageButton mIbtnSyncToWx;
    @BindView(R.id.iv_wx_selected)
    ImageView mIvWxSelected;
    @BindView(R.id.ibtn_sync_to_qq)
    ImageButton mIbtnSyncToQq;
    @BindView(R.id.iv_qq_selected)
    ImageView mIvQqSelected;
    @BindView(R.id.ibtn_sync_to_wb)
    ImageButton mIbtnSyncToWb;
    @BindView(R.id.iv_xlwb_selected)
    ImageView mIvXlwbSelected;
    @BindView(R.id.ibtn_more)
    ImageButton mIbtnSyncToTxwb;
    @BindView(R.id.iv_txwb_selected)
    ImageView mIvTxwbSelected;
    //图片数据，ImageItem是图片选择的库自带的Bean
    private ArrayList<ImageItem> images;
    //adapter是核心，添加按钮的处理删除的处理都在里面，后面会说，别急
    private MultiImageAdapter mImageAdapter;
    /**
     * 更改底部四个同步按钮的角标
     * 方式：
     * 首先判断角标的Visible属性，如果显示将其隐藏
     * 如果隐藏，设置为显示。这样每次点击的时候角标的状态都会改变
     * 以实现多选的效果
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WX_SELECTED:

                    if (mIvWxSelected.getVisibility() == View.INVISIBLE) {
                        mIvWxSelected.setVisibility(View.VISIBLE);
                    } else {
                        mIvWxSelected.setVisibility(View.INVISIBLE);
                    }


                    break;
                case QQ_SELECTED:

                    if (mIvQqSelected.getVisibility() == View.INVISIBLE) {
                        mIvQqSelected.setVisibility(View.VISIBLE);
                    } else {
                        mIvQqSelected.setVisibility(View.INVISIBLE);
                    }
                    break;

                case XLWB_SELECTED:
                    if (mIvXlwbSelected.getVisibility() == View.INVISIBLE) {
                        mIvXlwbSelected.setVisibility(View.VISIBLE);
                    } else {
                        mIvXlwbSelected.setVisibility(View.INVISIBLE);
                    }
                    break;

                case TXWB_SELECTED:

                    if (mIvTxwbSelected.getVisibility() == View.INVISIBLE) {
                        mIvTxwbSelected.setVisibility(View.VISIBLE);
                    } else {
                        mIvTxwbSelected.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_text);
        ButterKnife.bind(this);

        //初次进入的时候先只传this
        mImageAdapter = new MultiImageAdapter(this);
        mGridViewAddImg.setAdapter(mImageAdapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.d(requestCode);
        if (data != null & requestCode == REQUEST_IMAGE) {

            if (images == null) {
                images = (ArrayList<ImageItem>)
                        data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            } else {
                images.addAll((ArrayList<ImageItem>)
                        data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
            }

            //拿到图片数据后把images传过去
            mImageAdapter = new MultiImageAdapter(this, images);
            mGridViewAddImg.setAdapter(mImageAdapter);

        } else {
            Toast.makeText(this, "没有选择图片！", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick({R.id.ic_back, R.id.tv_next_step, R.id.rl_choose_service, R.id.ibtn_sync_to_wx, R.id.ibtn_sync_to_qq, R.id.ibtn_sync_to_wb, R.id.ibtn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_next_step:
                break;
            case R.id.rl_choose_service:
                startActivity(new Intent(PublishTextActivity.this, ChooseRelativeServiceActivity.class));
                break;
            case R.id.ibtn_sync_to_wx:
                mHandler.sendEmptyMessageAtTime(WX_SELECTED, 0);
                break;
            case R.id.ibtn_sync_to_qq:

                mHandler.sendEmptyMessageAtTime(QQ_SELECTED, 0);
                break;
            case R.id.ibtn_sync_to_wb:
                mHandler.sendEmptyMessageAtTime(XLWB_SELECTED, 0);
                break;
            case R.id.ibtn_more:
                mHandler.sendEmptyMessageAtTime(TXWB_SELECTED, 0);
                break;
        }
    }
}
