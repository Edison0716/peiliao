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
 * Created by bevis on 2016/7/19.
 */
public class TalentPersonalStatusFragment extends Fragment {
    @BindView(R.id.rv_talent_zone)
    RecyclerView mRvTalentZone;
    List<User> mUsers = new ArrayList<User>();
    private ChadQuickAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talent_personal_status, container, false);
        ButterKnife.bind(this, view);

        for (int i = 0; i < 5; i++) {
            User user = new User();
            mUsers.add(user);
        }

        mAdapter = new ChadQuickAdapter(R.layout.item_list_talent_zone, mUsers);

        mRvTalentZone.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTalentZone.setAdapter(mAdapter);
        return view;
    }
}
