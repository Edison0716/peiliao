package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.PrefUtils;

/**
 * Created by 10564 on 2016/8/8.
 */
public class SplashActivity extends Activity{
    private static final int CODE_ENTER_HOME=0;
    private static final int CODE_NEED_REGISTER=1;
    private String SHARE_APP_TAG = "isFirst";
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
                case CODE_NEED_REGISTER:
                    enterRegister();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void enterHome() {
        Intent intent = new Intent();
        intent.setClass(SplashActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        isFirst();
        boolean whether_register = PrefUtils.getBoolean("whether_register", true, getApplicationContext());
        if (whether_register){
            mHandler.sendEmptyMessageDelayed(CODE_ENTER_HOME,3000);
        }else{
            mHandler.sendEmptyMessageDelayed(CODE_NEED_REGISTER,3000);
        }



    }

    private void isFirst() {
        SharedPreferences setting = getSharedPreferences(SHARE_APP_TAG, 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            SharedPreferences register = getSharedPreferences("config",0);
            register.edit().putBoolean("whether_register",false).commit();
            //Toast.makeText(SplashActivity.this, "第一次", Toast.LENGTH_LONG).show();
        }else{
            //Toast.makeText(SplashActivity.this, "不是第一次", Toast.LENGTH_LONG).show();
        }
    }

    private void enterRegister() {
        Intent intent = new Intent();
        intent.setClass(SplashActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
