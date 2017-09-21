package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.view.ToggleSwitch;


/**
 * Created by 10564 on 2016/7/11.
 */
public class NotiNewsActivity extends Activity {
    private ImageButton ib_back;
    private ToggleSwitch toggleView ;
    private RelativeLayout welcome;
    Intent intent = new Intent();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_noti_news);
        initToggleSwitch();
        initBack();
        turnToWelcome();
    }

    private void turnToWelcome() {
        welcome = (RelativeLayout) findViewById(R.id.rl_welcome);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(NotiNewsActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initToggleSwitch() {
        toggleView = (ToggleSwitch) findViewById(R.id.toggleView);
        toggleView.setSwitchBackgroundResource(R.drawable.bg_tz_switchmini);
        toggleView.setSlideButtonResourceOff(R.drawable.icon_tz_switch_selectedmini);
        toggleView.setSlideButtonResource(R.drawable.icon_tz_switch_narmalmini);
        toggleView.setSwitchState(true);
        toggleView.setOnSwitchStateUpdateListener(new ToggleSwitch.OnSwitchStateUpdateListener(){
            public void onStateUpdate(boolean state) {
                if(state == true)
                    Toast.makeText(getApplicationContext(),"开关打开", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"开关关闭", Toast.LENGTH_SHORT).show();
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
