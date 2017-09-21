package com.wenmingkeji.peiliao.activities;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.converter.BooleanColumnConverter;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.AllCareerItems;
import com.wenmingkeji.peiliao.model.CareerBean;
import com.wenmingkeji.peiliao.okhttputils.OKManager;
import com.wenmingkeji.peiliao.utils.PrefUtils;
import com.wenmingkeji.peiliao.utils.ToastUtil;
import com.wenmingkeji.peiliao.utils.Urls;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 10564 on 2016/7/14.
 */
public class ManageCareerActivity extends Activity {
    private ImageView iv_close;
    private String json_path = "http://192.168.1.132:8080/caimao/AllCareer.json";
    Intent intent = new Intent();
    List<String> jobName;
    List<String> skills;
    List<String> allSkills;
    List<Boolean> nameBoolean;
    List<Bitmap> mapClick = new ArrayList<Bitmap>();
    List<Bitmap> mapNormal = new ArrayList<Bitmap>();
    List<String> iconUrlNormal = new ArrayList<String>();
    List<String> iconUrlClick = new ArrayList<String>();
    private ImageButton ib_back;
    private ListView lv_choiceCareer;
    private RelativeLayout rl_instruct;
    private RelativeLayout careerItem;
    boolean flag = true;
    private MyAdapter myAdapter;
    private TextView tv_textview;
    private ImageView checked;
    private Snackbar mSnackbar;
    private List<AllCareerItems.DataBean.JobsBean.SkillsBean> skills1;
    private AllCareerItems careerItems;
    private CareerBean bean;
    private List<CareerBean> careerBeen;
    private String detils;
    private String s2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_managecareer1);
        jobName = new ArrayList<String>();
        nameBoolean = new ArrayList<Boolean>();
        allSkills = new ArrayList<String>();
        rl_instruct = (RelativeLayout) findViewById(R.id.rl_instructClose);
        skills = new ArrayList<String>();
        tv_textview = (TextView) findViewById(R.id.tv_textview);
        getInforFromService();
        initBack();
        initData();
        closeDialog();
        SharedPreferences setting = getSharedPreferences("config", 0);
        Boolean user_first = setting.getBoolean("whether_closed", true);
        if (user_first) {//第一次
        } else {
            rl_instruct.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv_choiceCareer.getLayoutParams();
            lp.weight = 470;
            lv_choiceCareer.setLayoutParams(lp);
        }

    }


    private void getInforFromService() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, json_path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                careerItems = gson.fromJson(result, AllCareerItems.class);
                boolean success = careerItems.isSuccess();
                careerBeen = new ArrayList<CareerBean>();
                List<String>mList=null;
                if (success) {
                    for (int i = 0; i < careerItems.getData().getJobs().size(); i++) {
                        jobName.add(careerItems.getData().getJobs().get(i).getName());
                        nameBoolean.add(false);
                        iconUrlNormal.add(careerItems.getData().getJobs().get(i).getPic().getNormal());
                        iconUrlClick.add(careerItems.getData().getJobs().get(i).getPic().getClicked());
                        mList=new ArrayList<String>();
                            for(int j=0;j<careerItems.getData().getJobs().get(i).getSkills().size();j++){
                            mList.add(careerItems.getData().getJobs().get(i).getSkills().get(j).getName());
                    }
                        careerBeen.add(new CareerBean(i,mList));
                       /* for (int j = 0;j<careerItems.getData().getJobs().get(i).getSkills().size();j++){
                            skills.add(careerItems.getData().getJobs().get(i).getSkills().get(j).getName());
                        }*/
                       /* OkHttpUtils.get(careerItems.getData().getJobs().get(i).getPic().getNormal())//
                                .tag(this)//
                                .execute(new BitmapCallback() {
                                    @Override
                                    public void onResponse(boolean isFromCache, Bitmap bitmap, Request request, @Nullable Response response) {
                                        // bitmap 即为返回的图片数据
                                        mapNormal.add(bitmap);
                                    }
                                });
                        OkHttpUtils.get(careerItems.getData().getJobs().get(i).getPic().getClicked())//
                                .tag(this)//
                                .execute(new BitmapCallback() {
                                    @Override
                                    public void onResponse(boolean isFromCache, Bitmap bitmap, Request request, @Nullable Response response) {
                                        // bitmap 即为返回的图片数据
                                        mapClick.add(bitmap);
                                    }
                                });*/


                    }


                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(ManageCareerActivity.this, s, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void initData() {

        lv_choiceCareer = (ListView) findViewById(R.id.lv_careerItem);
        myAdapter = new MyAdapter();
        lv_choiceCareer.setAdapter(myAdapter);
        lv_choiceCareer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int count = count();
                if (!nameBoolean.get(position)) {

                    if (count >= 3) {
                        new AlertDialog.Builder(ManageCareerActivity.this).setTitle("提示").setMessage("最多只能选三个职业哦！")
                                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();

                        return;
                    }
                    if (count == 0) {

                        //tv_textview.setVisibility(View.VISIBLE);
                        tv_textview.setText(jobName.get(position));
                        String show = tv_textview.getText().toString();
                        snackbarUtils(view, show);
                    } else {

                        tv_textview.setText(tv_textview.getText().toString() + "、" + jobName.get(position));
                        String show = tv_textview.getText().toString();
                        snackbarUtils(view, show);
                    }
                    //nameBoolean[position] = !nameBoolean[position];
                    nameBoolean.set(position,!nameBoolean.get(position));

                    myAdapter.notifyDataSetChanged();
                } else {
                    if (count == 1) {
                        tv_textview.setText("");
                        nameBoolean.set(position, false);
                        String show = tv_textview.getText().toString();
                        snackbarUtils(view, show);
                        // tv_textview.setVisibility(View.GONE);
                        myAdapter.notifyDataSetChanged();
                        return;

                    } else {
                        String result = "";
                        int count2 = 0;
                        nameBoolean.set(position, false);
                        for (int i = 0; i < jobName.size(); i++) {
                            if (nameBoolean.get(i)) {
                                if (count2 == 0) {
                                    tv_textview.setText(jobName.get(i));
                                    String show = tv_textview.getText().toString();
                                    snackbarUtils(view, show);
                                } else {

                                    tv_textview.setText(tv_textview.getText().toString() + "、" + jobName.get(i));
                                    String show = tv_textview.getText().toString();
                                    snackbarUtils(view, show);

                                }
                                count2++;
                            }
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                }


            }
        });
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

    private void closeDialog() {
        iv_close = (ImageView) findViewById(R.id.icon_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_instruct.setVisibility(View.GONE);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv_choiceCareer.getLayoutParams();
                lp.weight = 470;
                lv_choiceCareer.setLayoutParams(lp);
                PrefUtils.putBoolean("whether_closed", false, getApplicationContext());

            }
        });


    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return jobName.size();
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

            convertView = View.inflate(getApplicationContext(), R.layout.list_career_item, null);
            ImageView iv_careerIcon = (ImageView) convertView.findViewById(R.id.iv_careerIcon);
            TextView tv_careerName = (TextView) convertView.findViewById(R.id.tv_careerName);
            careerItem = (RelativeLayout) convertView.findViewById(R.id.careerItem);
            checked = (ImageView) convertView.findViewById(R.id.iv_checked);
            //iv_careerIcon.setImageResource(jobIcon[position]);
            tv_careerName.setText(jobName.get(position));
            if (!nameBoolean.get(position)) {
                careerItem.setBackgroundColor(Color.parseColor("#fffffd"));
                Picasso.with(ManageCareerActivity.this).load(iconUrlNormal.get(position)).into(iv_careerIcon);
                //iv_careerIcon.setImageBitmap(mapNormal.get(position));
                checked.setVisibility(View.INVISIBLE);

            } else {
                careerItem.setBackgroundColor(Color.parseColor("#f8cd00"));
                Picasso.with(ManageCareerActivity.this).load(iconUrlClick.get(position)).into(iv_careerIcon);
                //iv_careerIcon.setImageBitmap(mapClick.get(position));
                checked.setVisibility(View.VISIBLE);
            }
            TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            List<String> strings = careerBeen.get(position).getmList();
            for(int s=0;s<strings.size();s++){
                if(s==0){
                    detils="";
                }else{
                    detils = tv_content.getText()+"、"+strings.get(s);
                }
            }
            tv_content.setText(detils);

            return convertView;
        }
    }

    private int count() {
        int count = 0;
        for (boolean a : nameBoolean) {
            if (a) {
                count++;
            }
        }
        return count;
    }

    private void snackbarUtils(View view, final String text) {
        mSnackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
        if (text != "") {
            mSnackbar.setAction("下一步", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String[] skills = text.split("、");

                    String skills1 = skills[0];
                    String skills2 = skills[1];
                    String skills3 = skills[2];
                    intent.putExtra("skills1", skills1);
                    intent.putExtra("skills2", skills2);
                    intent.putExtra("skills3", skills3);

                    intent.setClass(ManageCareerActivity.this, EnsureCareerActivity.class);
                    startActivity(intent);

                }
            });

        } else {
            mSnackbar.setAction("下一步", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ManageCareerActivity.this, "至少选中一个职业!", Toast.LENGTH_SHORT).show();
                    mSnackbar.dismiss();
                }
            });

        }
        mSnackbar.show();
    }
}
