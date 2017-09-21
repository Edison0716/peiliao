package com.wenmingkeji.peiliao.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10564 on 2016/7/25.
 */
public class SkillsFragment extends Fragment {
    String Psychological = "提供心里咨询师一二三级证书、心里健康辅导员证等证书照片，就可以得到【心理咨询师】的技能认证";
    String Lawyer = "上传律师证、法律咨询证书、法律顾问证等证书照片";
    String Financial = "上传证券从业资格证";
    private List<String> skillsName;
    private List<Integer> skillsIcon;
    private List<String> skillsContent;
    private ListView skillsItems;
    private Button addOthers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getContext(), R.layout.fragment_skills_authentication,null);
        skillsItems = (ListView) v.findViewById(R.id.ll_skills);
        addOthers = (Button) v.findViewById(R.id.addOthers);
        initData();
        skillsItems.setAdapter(new MyAdapter(getContext(),skillsName,skillsContent,skillsIcon));
        addOthers();
        return v;
    }

    private void addOthers() {
        addOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setTitle("提示").setMessage("请先[实名认证]或[芝麻信用认证]后，才能完成其他认证")
                        .setPositiveButton("好的",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }

    private void initData() {
        skillsName = new ArrayList<String>();
        skillsName.add("心里咨询师");
        skillsName.add("专业律师");
        skillsName.add("金融投资师");
        skillsIcon = new ArrayList<>();
        skillsIcon.add(R.drawable.icon_grrz_zmxyrz);
        skillsIcon.add(R.drawable.icon_grrz_zmxyrz);
        skillsIcon.add(R.drawable.icon_grrz_zmxyrz);
        skillsContent = new ArrayList<String>();
        skillsContent.add(Psychological);
        skillsContent.add(Lawyer);
        skillsContent.add(Financial);
    }

    private class MyAdapter extends BaseAdapter {

        private List<String> mSkillsName;
        private List<String> mSkillsContent;
        private List<Integer> mSkillsIcon;
        private LayoutInflater mInflater;
        private MyAdapter(Context context, List<String>SkillsName, List<String> SkillsContent, List<Integer> SkillsIcon){
            this.mSkillsName = SkillsName;
            this.mSkillsContent = SkillsContent;
            this.mSkillsIcon = SkillsIcon;
            mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mSkillsName.size();
        }

        @Override
        public Object getItem(int position) {
            return mSkillsName.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderAdapter holder ;
            if(convertView ==null){
                holder = new ViewHolderAdapter();
                convertView = mInflater.inflate(R.layout.list_skills_authentication_item,null);
                holder.SkillsName = (TextView) convertView.findViewById(R.id.tv_auth);
                holder.skillsContent = (TextView) convertView.findViewById(R.id.SkillContent);
                holder.skillsIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolderAdapter) convertView.getTag();
            }
            holder.SkillsName.setText(mSkillsName.get(position));
            holder.skillsContent.setText(mSkillsContent.get(position));
            holder.skillsIcon.setImageResource(mSkillsIcon.get(position));
            return convertView;
        }
    }
    public final class ViewHolderAdapter{
        public TextView SkillsName;
        public TextView skillsContent;
        public ImageView skillsIcon;
    }
}
