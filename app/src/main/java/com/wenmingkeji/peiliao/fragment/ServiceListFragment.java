package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.ChadQuickAdapter;
import com.wenmingkeji.peiliao.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/24.
 */
public class ServiceListFragment extends Fragment {

    @BindView(R.id.rv_service_list)
    RecyclerView mRvServiceList;

    private ChadQuickAdapter mChadQuickAdapter;

    private List<User> mUsers = new ArrayList<User>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_service, container, false);
        ButterKnife.bind(this, view);


        for (int i = 0; i < 5; i++) {
            User user = new User();
            mUsers.add(user);
        }

        mChadQuickAdapter = new ChadQuickAdapter(R.layout.item_service_list, mUsers);
        mRvServiceList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRvServiceList.setAdapter(mChadQuickAdapter);

        return view;
    }
}
