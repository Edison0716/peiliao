package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.StreamUtils;
import com.wenmingkeji.peiliao.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 10564 on 2016/7/13.
 */
public class PersonalDataActivity extends Activity{
    private static final int CODE_UPDATE_Info = 1;
    private static final int CODE_ENTER_HOME = 2;
    private static final int CODE_URL_ERROR = 3;
    private static final int CODE_NETWORK_ERROR = 4;
    private static final int CODE_JSON_ERROR = 5;
    private ImageButton ib_back;
    Intent intent = new Intent();
    private String userName;
    private String sex;
    private String city;
    private String constellation;
    private String identify;
    private String userNumInfo;
    private String phNum;
    private String workNum;
    private TextView tv_userName;
    private TextView tv_sex;
    private TextView tv_city;
    private TextView tv_constellation;
    private TextView tv_identify;
    private TextView tv_userNumInfo;
    private TextView tv_phNum;
    private TextView tv_workNum1;
    private String headImageUrl;
    private ImageView iv_headImage;
    private RelativeLayout workNum1;
    private RelativeLayout setAvatar;
    private Boolean success;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personaldata);
        turnToSex();
        initBack();
        choiceCity();
        turnToNickname();
        initView();
        getInfor();
        turnToConstellation();
        turnToApplyRank();
        turnToLoginPhoneNum();
        turnToSettingWorkNum();
        turnToSettingAvatar();

    }

    private void turnToSettingAvatar() {
        setAvatar = (RelativeLayout) findViewById(R.id.rl_Avatar);
        setAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(PersonalDataActivity.this,SetAvatarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void turnToSettingWorkNum() {
        workNum1 = (RelativeLayout) findViewById(R.id.rl_workNum);
        workNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(PersonalDataActivity.this,WorkNumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void turnToLoginPhoneNum() {
        RelativeLayout rl_phoneNum = (RelativeLayout) findViewById(R.id.rl_phoneNumber);
        rl_phoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(PersonalDataActivity.this,bindPhoneNumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void turnToApplyRank() {
        RelativeLayout rl_applyForRank = (RelativeLayout) findViewById(R.id.applyforRank);
        rl_applyForRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDataActivity.this,TitleApproveActivity.class);
                startActivity(intent);
            }
        });
    }

    private void turnToConstellation() {
        RelativeLayout rl_constellation = (RelativeLayout) findViewById(R.id.rl_constellation);
        rl_constellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDataActivity.this,ChoiceConstellationActivity.class);
                startActivity(intent);
            }
        });
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CODE_UPDATE_Info:
                    setInfo();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(PersonalDataActivity.this, "网络连接错误！", Toast.LENGTH_LONG).show();
                    enterHome();
                    break;
                case CODE_NETWORK_ERROR:
                    Toast.makeText(PersonalDataActivity.this, "网络异常！", Toast.LENGTH_LONG).show();
                    enterHome();
                    break;
                case CODE_JSON_ERROR:
                    Toast.makeText(PersonalDataActivity.this, "数据解析异常！", Toast.LENGTH_LONG).show();
                    enterHome();
                    break;
            }
        }
    };
    private void enterHome() {
       //// ToastUtil.show("数据获取失败");
        Toast.makeText(PersonalDataActivity.this,"数据获取失败",Toast.LENGTH_SHORT).show();
    }
    private void getInfor() {
        new Thread(){
            @Override
            public void run() {
                Message msg = Message.obtain();
                long currentTimeMillis = System.currentTimeMillis();
                HttpURLConnection conn = null;
                try{
                conn = (HttpURLConnection) new URL("http://192.168.1.132:8080/caimao/data.json").openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(2000);
                conn.connect();
                int code = conn.getResponseCode();
                if(code == 200) {
                    InputStream in = conn.getInputStream();
                    String result = StreamUtils.stream2String(in);
                    JSONObject object = null;
                    object = new JSONObject(result);
                    success = object.getBoolean("success");
                    if(success==true){
                        JSONObject data = object.getJSONObject("data");
                        headImageUrl = data.getString("avatar_url");
                        userName = data.getString("user_nick");
                        sex =  data.getString("user_gender");
                        city =  data.getString("city");
                        constellation =  data.getString("constellation");
                        identify =  data.getString("identify");
                        userNumInfo =  data.getString("userNumInfo");
                        phNum =  data.getString("phNum");
                        workNum =  data.getString("workNum");
                    }else
                    Toast.makeText(PersonalDataActivity.this,"failed",Toast.LENGTH_SHORT).show();

                     
                    if(2>1){
                        msg.what=CODE_UPDATE_Info;
                    }else{
                        msg.what=CODE_ENTER_HOME;
                    }

                }
            }catch (MalformedURLException e) {
                    e.printStackTrace();
                    msg.what = CODE_URL_ERROR;
                }
                catch (IOException e) {
                    e.printStackTrace();
                    msg.what = CODE_NETWORK_ERROR;
                }catch (JSONException e) {
                    e.printStackTrace();
                    msg.what = CODE_JSON_ERROR;
                }finally {
                    if(conn!=null){
                        conn.disconnect();
                    }
                    long endTime = System.currentTimeMillis();
                    long timeUsed = endTime - currentTimeMillis;//计算访问网络用的时间
                    try {
                        if (timeUsed < 2000) {
                            Thread.sleep(1000 * 2 - timeUsed);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }
    private void initView() {
         iv_headImage =  (ImageView) findViewById(R.id.iv_headImage);
         tv_userName =  (TextView) findViewById(R.id.tv_userName);
         tv_sex =  (TextView) findViewById(R.id.tv_sex);
         tv_city =  (TextView) findViewById(R.id.tv_city);
         tv_constellation =  (TextView) findViewById(R.id.tv_constellation);
         tv_identify =  (TextView) findViewById(R.id.tv_identify);
         tv_userNumInfo =   (TextView) findViewById(R.id.tv_userNumInfo);
         tv_phNum =  (TextView) findViewById(R.id.tv_phNum);
         tv_workNum1 =  (TextView) findViewById(R.id.tv_workNum);
    }
    private void setInfo() {
        //ToastUtil.show("获取数据成功！");
        Toast.makeText(PersonalDataActivity.this,"获取数据成功！",Toast.LENGTH_SHORT).show();
        if(sex == "M"){
            tv_sex.setText("男");
        }else if (sex =="F"){
            tv_sex.setText("女");
        }
        tv_userName.setText(userName);
        tv_sex.setText("男");
        tv_city.setText(city);
        tv_constellation.setText(constellation);
        tv_identify.setText(identify);
        tv_userNumInfo.setText(userNumInfo);
        tv_phNum.setText(phNum);
        tv_workNum1.setText(workNum);
        Picasso.with(PersonalDataActivity.this).load(headImageUrl).into(iv_headImage);
    }
    private void turnToNickname() {
        RelativeLayout rl_nickname = (RelativeLayout) findViewById(R.id.rl_nickname);
        rl_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(PersonalDataActivity.this, NickNameActivity.class);
                startActivity(intent);
            }
        });
    }
    private void choiceCity() {
        RelativeLayout rl_choiceCity = (RelativeLayout) findViewById(R.id.rl_city);
        rl_choiceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(PersonalDataActivity.this,SelectCityActivity.class);
                startActivity(intent);
            }
        });
    }
    private void turnToSex() {
        RelativeLayout rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(PersonalDataActivity.this,SexActivity.class);
                startActivity(intent);
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


}
