package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.BitmapCallback;
import com.squareup.picasso.Picasso;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.MyCareer;
import com.wenmingkeji.peiliao.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 10564 on 2016/7/18.
 */
public class ManageCareerFirstActivity extends Activity{
    Intent intent = new Intent();
    private ImageButton ib_back;
    private String json_path = "http://192.168.1.132:8080/caimao/MyCareer.json";
    private String name1;
    private String name2;
    private String name3;
    private List<String> name;
    private List<String> level_title;
    private List<Integer> gap;
    private List<Integer> exp;
    private List<String> updateIntroduction;
    private List<Boolean> is_default_job;
    private List<String> level_icon;
    private List<String> next_level_icon;
    private String level_title1;
    private String level_title2;
    private String level_title3;
    private int gap1;
    private int gap2;
    private int gap3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_managecareer);
        name = new ArrayList<String>();
        level_title = new ArrayList<String>();
        gap  = new ArrayList<Integer>();
        exp = new ArrayList<Integer>();
        next_level_icon = new ArrayList<String>();
        level_icon = new ArrayList<String>();
        is_default_job = new ArrayList<Boolean>();
        updateIntroduction = new ArrayList<String>();
        getDataFormService();
        initData();
        initBack();

    }

    private void getDataFormService() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, json_path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                MyCareer myCareer = gson.fromJson(result, MyCareer.class);
                    Boolean success = myCareer.success;
                    System.out.println(success);
                if (success){
                    getInfo(myCareer);
                }else {
                    Toast.makeText(ManageCareerFirstActivity.this,"failed",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(ManageCareerFirstActivity.this,s,Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void getInfo(MyCareer myCareer) {
        name1 = myCareer.getData().getJobs().get(0).getName();
        name2 = myCareer.getData().getJobs().get(1).getName();
        name3 = myCareer.getData().getJobs().get(2).getName();
        name.add(name1);
        name.add(name2);
        name.add(name3);
        level_title1 = myCareer.getData().getJobs().get(0).getLevel_title();
        level_title2 = myCareer.getData().getJobs().get(1).getLevel_title();
        level_title3 = myCareer.getData().getJobs().get(2).getLevel_title();
        level_title.add(level_title1);
        level_title.add(level_title2);
        level_title.add(level_title3);
        gap1 = myCareer.getData().getJobs().get(0).getGap();
        gap2 = myCareer.getData().getJobs().get(1).getGap();
        gap3 = myCareer.getData().getJobs().get(2).getGap();
        gap.add(gap1);
        gap.add(gap2);
        gap.add(gap3);
        int exp1 = myCareer.getData().getJobs().get(0).getExp();
        int exp2 = myCareer.getData().getJobs().get(1).getExp();
        int exp3 = myCareer.getData().getJobs().get(2).getExp();
        exp.add(exp1);
        exp.add(exp2);
        exp.add(exp3);
        String action_url1 = myCareer.getData().getJobs().get(0).getAction_url();
        String action_url2 = myCareer.getData().getJobs().get(0).getAction_url();
        String action_url3 = myCareer.getData().getJobs().get(0).getAction_url();
        updateIntroduction.add(action_url1);
        updateIntroduction.add(action_url2);
        updateIntroduction.add(action_url3);
        boolean is_default_job1 =  myCareer.getData().getJobs().get(0).isIs_default_job();
        boolean is_default_job2 = myCareer.getData().getJobs().get(1).isIs_default_job();
        boolean is_default_job3 = myCareer.getData().getJobs().get(2).isIs_default_job();
        is_default_job.add(is_default_job1);
        is_default_job.add(is_default_job2);
        is_default_job.add(is_default_job3);
        String url1 = myCareer.getData().getJobs().get(0).getLevel_icon();
        String url2 = myCareer.getData().getJobs().get(1).getLevel_icon();
        String url3 = myCareer.getData().getJobs().get(2).getLevel_icon();
        level_icon.add(url1);
        level_icon.add(url2);
        level_icon.add(url3);
        String next_level_icon1 = myCareer.getData().getJobs().get(0).getNext_level_icon();
        String next_level_icon2 = myCareer.getData().getJobs().get(1).getNext_level_icon();
        String next_level_icon3 = myCareer.getData().getJobs().get(2).getNext_level_icon();
        next_level_icon.add(next_level_icon1);
        next_level_icon.add(next_level_icon2);
        next_level_icon.add(next_level_icon3);
    }



    private void initData() {

        ListView iv_career = (ListView) findViewById(R.id.lv_career);
        iv_career.setAdapter(new MyAdapter());

    }


    public void editingCareer (View v){
        Intent in  =  new  Intent(this,ManageCareerActivity.class);
        startActivity(in);
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

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return name.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = View.inflate(getApplication(),R.layout.list_choicecareer_item,null);
            TextView tv_name = (TextView) v.findViewById(R.id.name);
            tv_name.setText(name.get(position));
            TextView level_title1= (TextView) v.findViewById(R.id.level_title);
            level_title1.setText(level_title.get(position));
            TextView tv_gap = (TextView) v.findViewById(R.id.gap);
            tv_gap.setText(gap.get(position).toString());
            TextView tv_exp = (TextView) v.findViewById(R.id.exp);
            tv_exp.setText(exp.get(position).toString());
            TextView tv_update = (TextView) v.findViewById(R.id.tv_updateIntroduction);
            tv_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = updateIntroduction.get(position);
                    intent.setClass(ManageCareerFirstActivity.this,UpdateIntroductionActivity.class);
                    intent.putExtra("introduceInfo",url);
                    startActivity(intent);
                }
            });
            TextView tv_setDefault = (TextView) v.findViewById(R.id.setDefault);
            Boolean setdefault = is_default_job.get(position);
            if (setdefault){
                tv_setDefault.setText("已设为主营职业");
            }else{
                tv_setDefault.setText("设置为主营职业");
            }
            ImageView iv_level_icon = (ImageView) v.findViewById(R.id.level_icon);
            Picasso.with(ManageCareerFirstActivity.this).load(level_icon.get(position)).into(iv_level_icon);
            ImageView next_level_icon1 = (ImageView) v.findViewById(R.id.next_level_icon);
            Picasso.with(ManageCareerFirstActivity.this).load(next_level_icon.get(position)).into(next_level_icon1);
            return v;
        }
    }
}
