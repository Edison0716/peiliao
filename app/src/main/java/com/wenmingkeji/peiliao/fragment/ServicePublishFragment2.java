package com.wenmingkeji.peiliao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

/**
 * Created by bevis on 2016/7/11.
 */
public class ServicePublishFragment2 extends Fragment {

    private final static int REQUEST_IMAGE = 3;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_next_step)
    TextView mTvNextStep;
    @BindView(R.id.ll_choose_skills)
    LinearLayout mLlChooseSkills;
    @BindView(R.id.tv_text_num)
    TextView mTvTextNum;
    @BindView(R.id.et_input_description)
    EditText mEtInputDescription;
    @BindView(R.id.tv_text_num_service)
    TextView mTvTextNumService;
    @BindView(R.id.et_input_unit)
    EditText mEtInputUnit;
    @BindView(R.id.et_input_price)
    EditText mEtInputPrice;
    @BindView(R.id.et_input_stock)
    EditText mEtInputStock;
    @BindView(R.id.grid_view_add_img)
    GridView mGridViewAddImg;
    //图片数据，ImageItem是图片选择的库自带的Bean
    private ArrayList<ImageItem> images;
    //adapter是核心，添加按钮的处理删除的处理都在里面，后面会说，别急
    private MultiImageAdapter mImageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_publish_2, container, false);
        ButterKnife.bind(this, view);

        //初次进入的时候先只传this
        mImageAdapter = new MultiImageAdapter(getActivity());
        mGridViewAddImg.setAdapter(mImageAdapter);
        return view;
    }

    @OnClick({R.id.ic_back, R.id.tv_next_step, R.id.ll_choose_skills})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_next_step:

                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.home_container, new ServicePublishFragment3())
                        .commit();
                break;
            case R.id.ll_choose_skills:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.d(requestCode);
        if (data != null & requestCode == REQUEST_IMAGE) {

            Logger.d("demo");
            if (images == null) {
                images = (ArrayList<ImageItem>)
                        data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            } else {
                images.addAll((ArrayList<ImageItem>)
                        data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
            }

            //拿到图片数据后把images传过去
            mImageAdapter = new MultiImageAdapter(getActivity(), images);
            mGridViewAddImg.setAdapter(mImageAdapter);

        } else {
            Toast.makeText(getActivity(), "没有选择图片！", Toast.LENGTH_SHORT).show();
        }
    }
    
    
}
