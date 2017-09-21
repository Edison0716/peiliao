package com.wenmingkeji.peiliao.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;

public class EditAddressActivity extends AppCompatActivity {

    private String userName;
    private String userPhoneName;
    private String userAddressDetails;
    private EditText et_contactName;
    private EditText et_contactPhone;
    private EditText et_addressDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        showOldAddress();
        initView();
    }

    private void initView() {
        et_contactName = (EditText) findViewById(R.id.et_contactName);
        et_contactPhone = (EditText) findViewById(R.id.et_contactPhone);
        et_addressDetails = (EditText) findViewById(R.id.et_contactAddressDetails);
    }

    private void showOldAddress() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(0==requestCode){
            if(0==resultCode){
                Bundle bundle = data.getBundleExtra("bundle");
                userName = bundle.getString("userName");
                userPhoneName = bundle.getString("userPhoneNum");
                userAddressDetails = bundle.getString("userAddressDetails");
                Toast.makeText(EditAddressActivity.this,userName+userPhoneName+userAddressDetails,Toast.LENGTH_SHORT).show();
                et_contactName.setText(userName);
                et_contactPhone.setText(userPhoneName);
                et_addressDetails.setText(userAddressDetails);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
