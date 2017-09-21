package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/29.
 */
public class SigninAtivity extends Activity{
    private ImageButton ib_back;
    private EditText phoneNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        initBack();
    }
    public void finishSignin(View v){
        String phoneNum1 = phoneNum.getText().toString().trim();
        Intent intent = new Intent();
        if(!TextUtils.isEmpty(phoneNum1)&&phoneNum1.length()==11){
            intent.setClass(SigninAtivity.this,RegisterActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PhoneNum",phoneNum1);
            intent.putExtra("bundle",bundle);
            setResult(RESULT_OK,intent);
            finish();
        }else {
            Toast.makeText(SigninAtivity.this,"手机号为空或手机号码不合法！",Toast.LENGTH_SHORT).show();
        }

    }
    public void immediatelyLogin(View v){
        Intent intent = new Intent();
        intent.setClass(SigninAtivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
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
