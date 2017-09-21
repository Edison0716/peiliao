package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.TalentRecommendAdapter;
import com.wenmingkeji.peiliao.model.SimpleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bevis on 2016/7/8.
 */
public class RecommendItemFragment extends Fragment {

    private static final String TAG = "RecommendItemFragment";

    @BindView(R.id.rv_talent_recommend)
    RecyclerView mRvTalentRecommend;
    @BindView(R.id.ibtn_talent_refresh)
    ImageButton mIbtnTalentRefresh;
    @BindView(R.id.ibtn_close)
    ImageButton mIbtnClose;
    @BindView(R.id.ll_talent_recommend)
    LinearLayout mLlTalentRecommend;
    List<SimpleItem> mSimpleItems = new ArrayList<SimpleItem>();
    private TalentRecommendAdapter mAdapter;


    //接收新传来的item
    /*public static RecommendItemFragment getInstance(ArrayList<User> users) {
        TalentContentFragment talentContentFragment = new TalentContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("demo", users);
        talentContentFragment.setArguments(bundle);
        return talentContentFragment;
    }*/
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_recommend, container, false);
        ButterKnife.bind(this, view);

        getSimpleItems();
        mAdapter = new TalentRecommendAdapter(R.layout.item_list_talent_recommand, mSimpleItems);
        mAdapter.openLoadAnimation();
        mRvTalentRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTalentRecommend.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.ibtn_follow:

                        Log.d(TAG, "follow ibtn clicked");
                        SimpleItem simpleItem = new SimpleItem();
                        simpleItem.setUserHead(R.drawable.icon_common_doutu);
                        simpleItem.setUserName("莱昂纳多aaaa");
                        simpleItem.setFansNum("99999");

                        mAdapter.remove(i);
//                        mSimpleItems.remove(i);
                        mAdapter.add(i, simpleItem);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.openLoadAnimation();
                        break;
                }
            }
        });

        return view;
    }


    List<SimpleItem> getSimpleItems() {

        SimpleItem simpleItem = new SimpleItem();
        simpleItem.setUserHead(R.drawable.icon_common_food);
        simpleItem.setUserName("莱昂纳多");
        simpleItem.setFansNum("55");
        mSimpleItems.add(simpleItem);

        SimpleItem simpleItem1 = new SimpleItem();
        simpleItem1.setUserHead(R.drawable.icon_common_cv);
        simpleItem1.setUserName("王尼玛");
        simpleItem1.setFansNum("666");
        mSimpleItems.add(simpleItem1);

        SimpleItem simpleItem2 = new SimpleItem();
        simpleItem2.setUserHead(R.drawable.icon_common_draw);
        simpleItem2.setUserName("暴走马那话");
        simpleItem2.setFansNum("444");
        mSimpleItems.add(simpleItem2);

        return mSimpleItems;
    }


    @OnClick({R.id.ibtn_talent_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_talent_refresh:
                List<SimpleItem> simpleItems = new ArrayList<SimpleItem>();
                for (int i = 0; i < 3; i++) {
                    SimpleItem simpleItem = new SimpleItem();
                    simpleItem.setUserHead(R.drawable.icon_common_doutu);
                    simpleItem.setUserName("莱昂纳多aaaa");
                    simpleItem.setFansNum("99999");
                    simpleItems.add(simpleItem);
                }
                mAdapter.setNewData(simpleItems);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick(R.id.ibtn_close)
    public void onClick() {
        mLlTalentRecommend.setVisibility(View.GONE);
    }
}
