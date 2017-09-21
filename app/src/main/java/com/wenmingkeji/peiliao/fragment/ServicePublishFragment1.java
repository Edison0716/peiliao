package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.SkillSectionsAdapter;
import com.wenmingkeji.peiliao.model.MySection;
import com.wenmingkeji.peiliao.model.Skill;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bevis on 2016/7/11.
 */
public class ServicePublishFragment1 extends Fragment {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;

    @BindView(R.id.rv_service_skill)
    RecyclerView mRvServiceSkill;
    @BindView(R.id.tv_next_step)
    TextView mTvNextStep;
    ArrayList<MySection> mMySections = new ArrayList<MySection>();
    String[] skillNames = {"私人导游", "全程管家", "线路推荐", "攻略定制", "旅游咨询", "全程管家", "线路推荐", "攻略定制", "旅游咨询", "全程管家", "线路推荐", "攻略定制", "旅游咨询"};
    private SkillSectionsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_publish_1, container, false);
        ButterKnife.bind(this, view);
        //init skill


        initData();
        mAdapter = new SkillSectionsAdapter(R.layout.view_skill_list, R.layout.view_skills_type, mMySections);
        mRvServiceSkill.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        mRvServiceSkill.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvServiceSkill.setAdapter(mAdapter);
        
        
/*        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                mAdapter.setSelectedPosition(i);
                mAdapter.notifyDataSetChanged();   
            }
        });*/

        mAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                Logger.d(i);
                TextView textView = (TextView) view.findViewById(R.id.tv_skill_list);
                if (textView.isSelected()) {
                    textView.setSelected(false);
                } else {
                    textView.setSelected(true);
                }

            }
        });

        return view;
    }

    @OnClick({R.id.ic_back, R.id.tv_next_step})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_next_step:
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.home_container, new ServicePublishFragment2())
                        .commit();
                break;
        }
    }


    void initData() {

        MySection mySection = new MySection(true, "旅游顾问");
        mMySections.add(mySection);
        for (int i = 0; i < skillNames.length; i++) {

            Skill skill = new Skill();
            skill.setSkillName(skillNames[i]);
            MySection mySection1 = new MySection(skill);

            mMySections.add(mySection1);

        }

        MySection mySection2 = new MySection(true, "美食达人");
        mMySections.add(mySection2);
        for (int i = 0; i < skillNames.length; i++) {

            Skill skill2 = new Skill();
            skill2.setSkillName(skillNames[i]);
            MySection mySection22 = new MySection(skill2);

            mMySections.add(mySection22);

        }
    }
}
