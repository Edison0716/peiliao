package com.wenmingkeji.peiliao.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.IndividualCertificationActivity;
import com.wenmingkeji.peiliao.activities.TureNameApproveActivity;

/**
 * Created by 10564 on 2016/7/18.
 */
public class OfficialFragment extends Fragment {
    @Nullable
    Intent intent = new Intent();
    String [] title = new String[]{"  芝麻信用认证","  实名认证","  头衔认证","  食品三证"};
    int [] icon = new int[]{R.drawable.icon_grrz_zmxyrz,
            R.drawable.icon_grrz_smrz, R.drawable.icon_grrz_txrz, R.drawable.icon_grrz_sprz};
    private ListView lv_official;
    private boolean whether_bind;
    private TextView tv_whetherCertification;
    double x = 0.7;
    double y = 0.7;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences read = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);

        whether_bind = read.getBoolean("whether_bind", true);
        View view = inflater.inflate(R.layout.fragment_personal_authentication,null);
        lv_official =  (ListView) view.findViewById(R.id.lv_official);
        lv_official.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    intent.setClass(getContext(), IndividualCertificationActivity.class);
                    startActivity(intent);
                }
                if(position==1){
                    intent.setClass(getContext(), TureNameApproveActivity.class);
                    startActivity(intent);
                }
                if(position==2){

                }
                if(position==3){

                }
            }
        });
        lv_official.setAdapter(new MyAdapter());
        return view;
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(getContext(), R.layout.list_official_authentication_item,null);
            ImageView iv_title = (ImageView) v.findViewById(R.id.iv_icon);
            iv_title.setImageResource(icon[position]);
            TextView tv_auth = (TextView) v.findViewById(R.id.tv_auth);
            tv_auth.setText(title[position]);
            ImageView yesOrNo = (ImageView) v.findViewById(R.id.iv_yesOrno);
            tv_whetherCertification =  (TextView) v.findViewById(R.id.tv_whetherCertification);
            if(position==0){
               if(whether_bind){

                   yesOrNo.setScaleX((float) x);
                   yesOrNo.setScaleY((float) y);
                   yesOrNo.setImageResource(R.drawable.icon_add_check_mini_selected);
                   tv_whetherCertification.setText("已认证");
               }else{
                   yesOrNo.setImageResource(R.drawable.icon_grrz_wrz);
                   tv_whetherCertification.setText("未认证");
               }
           }
            return v;
        }
    }

}
