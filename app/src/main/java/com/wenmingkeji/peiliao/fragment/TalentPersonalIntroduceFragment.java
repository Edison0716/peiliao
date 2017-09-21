package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenmingkeji.peiliao.R;

/**
 * Created by bevis on 2016/7/19.
 */
public class TalentPersonalIntroduceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talent_personal_introduce, container, false);
        return view;
    }
}
