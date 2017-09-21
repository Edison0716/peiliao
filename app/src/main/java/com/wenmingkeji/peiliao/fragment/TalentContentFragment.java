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
 * Created by bevis on 2016/7/14.
 */
public class TalentContentFragment extends Fragment {

    private static final String TALENT_CONTENT = "talent_content";
    @BindView(R.id.rv_talent_content)
    RecyclerView mRvTalentContent;
    private ChadQuickAdapter mChadQuickAdapter;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TalentContentFragment newInstance(ArrayList<User> users) {
        TalentContentFragment talentContentFragment = new TalentContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TALENT_CONTENT, users);
        talentContentFragment.setArguments(bundle);
        return talentContentFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talent_content, container, false);

        ButterKnife.bind(this, view);

        /*List<User> users = new ArrayList<User>();
        users = getArguments().getParcelableArrayList("demo");
        for (User user : users) {
            Log.d("Tag",user.getUserName());
            Log.d("Tag",user.getSex());
        }*/

        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 5; i++) {
            User user = new User();
            users.add(user);
        }

        mChadQuickAdapter = new ChadQuickAdapter(R.layout.item_list_talent_content, users);

        mRvTalentContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTalentContent.setAdapter(mChadQuickAdapter);

        return view;
    }


}
