package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ChadQuickAdapter;
import com.wenmingkeji.peiliao.model.User;
import com.wenmingkeji.peiliao.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by bevis on 2016/7/19.
 */
public class TalentPersonalCareerFragment extends Fragment {


    @BindView(R.id.rv_service_career)
    RecyclerView mRvServiceCareer;
    @BindView(R.id.img_servcie_preview)
    CircleImageView mImgServciePreview;
    @BindView(R.id.tv_service_name)
    TextView mTvServiceName;
    @BindView(R.id.iv_level)
    ImageView mIvLevel;
    @BindView(R.id.tv_level_num)
    TextView mTvLevelNum;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_person_appointment)
    TextView mTvPersonAppointment;
    @BindView(R.id.tv_service_sum)
    TextView mTvServiceSum;
    List<User> mUsers = new ArrayList<User>();
    private ChadQuickAdapter mAdapter;


    public TalentPersonalCareerFragment() {
    }

    public static TalentPersonalCareerFragment newInstance(ArrayList<User> users) {
        TalentPersonalCareerFragment fragment = new TalentPersonalCareerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("test", users);
        fragment.setArguments(bundle);
        return fragment;
    }

    /*public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talent_psersonal_career, container, false);
        ButterKnife.bind(this, view);
        for (int i = 0; i < 3; i++) {
            User user = new User();
            mUsers.add(user);
        }

        mAdapter = new QuickAdapter(R.layout.item_list_career, mUsers);

        mRvServiceCareer.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvServiceCareer.setAdapter(mAdapter);

        
        return view;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talent_psersonal_career, container, false);
        return view;
    }
}
