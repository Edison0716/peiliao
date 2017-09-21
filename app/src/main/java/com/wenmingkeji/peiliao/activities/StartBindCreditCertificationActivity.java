package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.PrefUtils;
import com.wenmingkeji.peiliao.utils.ToastUtil;

/**
 * Created by 10564 on 2016/7/20.
 */
public class StartBindCreditCertificationActivity extends Activity{
    private ImageButton ib_back;
    Intent intent = new Intent();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startbind_creditcertification);
        initBack();
        EnsureBindFinish();
    }

    private void EnsureBindFinish() {
        Button bt_ensure = (Button) findViewById(R.id.bt_confirm);
        bt_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.putBoolean("whether_bind",true,getApplicationContext());
                //ToastUtil.show("提交成功！");
                Toast.makeText(StartBindCreditCertificationActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                /*intent.setClass(StartBindCreditCertificationActivity.this, MineFragment.class);
                startActivity(intent);*/
                finish();
            }
        });

    }

    private void initBack() {
        ib_back = (ImageButton) findViewById(R.id.ibtn_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
