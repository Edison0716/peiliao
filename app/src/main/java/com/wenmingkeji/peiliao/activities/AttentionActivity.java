package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 10564 on 2016/7/12.
 */
public class AttentionActivity extends Activity {
    private ListView lv_attentPerson;
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_attention);
        initListView();
        initBack();
    }

    private void initListView() {
        List<Icon> headImage = new ArrayList<Icon>();
        List<String> name = new ArrayList<String>();
        name.add("刘德华");
        name.add("周杰伦");
        name.add("曾志伟");
        List<String> jobName = new ArrayList<String>();
        jobName.add("演员");
        jobName.add("歌手");
        jobName.add("演员");
        List<Integer> fansNum = new ArrayList<Integer>();
        fansNum.add(99999) ;
        fansNum.add(2646848);
        fansNum.add(789415);
        lv_attentPerson =  (ListView) findViewById(R.id.lv_attentPeople);
        lv_attentPerson.setAdapter(new MyAdapter(AttentionActivity.this,headImage,name,jobName,fansNum));
    }

    private class MyAdapter extends BaseAdapter {
        private List<Icon> mHeadImg;
        private List<String> mName;
        private List<String> mJobName;
        private List<Integer> mFansNum;
        private LayoutInflater mInflater;
        private MyAdapter(Context context,List<Icon> headImage,List<String> name, List<String> jobName, List<Integer> fansNum){
            this.mHeadImg = headImage;
            this.mName = name;
            this.mJobName = jobName;
            this.mFansNum = fansNum;
            mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mName.size();
        }

        @Override
        public Object getItem(int i) {
            return mName.size();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolderAdapter holder = null;
            if(view == null){
                holder = new ViewHolderAdapter();
                view = mInflater.inflate(R.layout.list_item_attentperson,null);
                holder.attendName = (TextView) view.findViewById(R.id.userName);
                holder.fansNum = (TextView) view.findViewById(R.id.fansNum);
                holder.jobName = (TextView) view.findViewById(R.id.jobName);
                holder.cancelAttention = (Button) view.findViewById(R.id.cancelAttent);
                view.setTag(holder);
            }else{
                holder = (ViewHolderAdapter) view.getTag();
            }
            holder.attendName.setText(mName.get(i));
            holder.jobName.setText(mJobName.get(i));
            holder.fansNum.setText(mFansNum.get(i).toString()+" 粉丝");
            holder.cancelAttention.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(AttentionActivity.this).setTitle("取消关注").setMessage("是否取消关注此人？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    mName.remove(i);
                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("否",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            });
            return view;
        }
    }
    private void initBack() {
        ib_back = (ImageButton) findViewById(R.id.ic_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }
    public final class ViewHolderAdapter{
        public TextView attendName;
        public TextView jobName;
        public TextView fansNum;
        public ImageView headImage;
        public Button cancelAttention;
    }
}
