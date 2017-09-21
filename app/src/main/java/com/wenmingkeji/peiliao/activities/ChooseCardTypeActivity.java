package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/20.
 */
public class ChooseCardTypeActivity extends Activity{
    private ImageButton ib_back;
    Intent in = new Intent();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecardtype);
        initBack();
        turnToDrawMoney();
        turnToEnterpriseCertification();
    }

    private void turnToEnterpriseCertification() {
        RelativeLayout rl_EnterpriseCertification = (RelativeLayout) findViewById(R.id.enterpriseCertification);
        rl_EnterpriseCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setClass(ChooseCardTypeActivity.this,EnterpriseCertificationActivity.class);
                startActivity(in);
            }
        });
    }

    private void turnToDrawMoney() {
        RelativeLayout rl_drawMoney = (RelativeLayout) findViewById(R.id.individualCertification);
        rl_drawMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setClass(ChooseCardTypeActivity.this,DrawMoneyDetails.class);
                startActivity(in);
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
