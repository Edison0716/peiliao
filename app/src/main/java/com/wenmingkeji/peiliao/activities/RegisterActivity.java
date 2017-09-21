package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.PrefUtils;
import com.wenmingkeji.peiliao.view.SmoothCheckBox;

/**
 * Created by 10564 on 2016/7/29.
 */
public class RegisterActivity extends Activity {
    private ImageButton ib_back;
    private EditText phoneNum;
    private EditText pwd;
    SmoothCheckBox scb;
    private String phoneNum1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phoneNum = (EditText) findViewById(R.id.et_phoneNum);

        pwd = (EditText) findViewById(R.id.et_pwd);
        scb = (SmoothCheckBox) findViewById(R.id.scb);

        boolean isRemember = PrefUtils.getBoolean("isRemember", true, getApplicationContext());
        if (isRemember) {
            String phoneNums = PrefUtils.getString("PhoneNum", "", getApplicationContext());
            if (phoneNums != null) {
                this.phoneNum.setText(phoneNums);
            }
            scb.setChecked(true, true);
        } else
            scb.setChecked(false, true);


        initBack();
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

    public void register(View v) {

        String phoNum = phoneNum.getText().toString().trim();
        String password = pwd.getText().toString().trim();
        if (phoNum != null && password != null) {
            PrefUtils.putBoolean("whether_register", true, getApplicationContext());
            Toast.makeText(RegisterActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            if (scb.isChecked() == true) {
                PrefUtils.putBoolean("isRemember", true, getApplicationContext());
                PrefUtils.putString("PhoneNum", phoNum, getApplicationContext());
                PrefUtils.putString("Password", password, getApplicationContext());
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            } else
                PrefUtils.putBoolean("isRemember", false, getApplicationContext());
        } else {
            Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        }

    }

    public void LoginforFree(View v) {
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, SigninAtivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (0==requestCode){
            if (RESULT_OK==resultCode){
                Bundle bundle = data.getBundleExtra("bundle");
                phoneNum1 = bundle.getString("PhoneNum").trim();
                System.out.println(phoneNum1);
                if (!TextUtils.isEmpty(phoneNum1)){
                    phoneNum.setText(phoneNum1);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
