package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.PrefUtils;
import com.wenmingkeji.peiliao.view.SettingItemView;

public class AddNewAddressActivity extends Activity {
    private ImageButton ib_back;
    private EditText et_contactName;
    private EditText et_contactPhone;
    private EditText et_addressDetails;
    private Button save;
    private String contactName;
    private String contactPhone;
    private String contactAddressDetails;
    private RelativeLayout rl_default;
    private CheckBox cb_default;
    private SettingItemView view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        initBack();
        initView();
        saveAddressDetails();

        settingDefault();
    }

    private void settingDefault() {
        view.setTitile("设为默认地址");
        view.setChecked(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view.isChecked()) {
                    view.setChecked(false);
                    PrefUtils.putBoolean("Address_default",false,getApplicationContext());
                } else {
                    view.setChecked(true);
                    PrefUtils.putBoolean("Address_default",true,getApplicationContext());
                }
            }
        });

    }


    private void getInfo() {
        contactName = et_contactName.getText().toString().trim();
        contactPhone = et_contactPhone.getText().toString().trim();
        contactAddressDetails = et_addressDetails.getText().toString().trim();
    }

    private void saveAddressDetails() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                getInfo();
                if(view.isChecked()==true){
                    bundle.putBoolean("default_checked",true);
                    intent.putExtra("bundle",bundle);
                }else{
                    bundle.putBoolean("default_checked",false);
                    intent.putExtra("bundle",bundle);
                }

                if(contactName!=null){
                    bundle.putString("contactName",contactName);
                    intent.putExtra("bundle",bundle);
                }else{
                    Toast.makeText(AddNewAddressActivity.this,"联系人姓名不能为空！",Toast.LENGTH_SHORT).show();
                }
                if (contactPhone!=null){
                    bundle.putString("contactPhone",contactPhone);
                    intent.putExtra("bundle",bundle);
                }
                else{
                    Toast.makeText(AddNewAddressActivity.this,"联系人联系电话不能为空！",Toast.LENGTH_SHORT).show();
                }
                if (contactAddressDetails!=null){
                    bundle.putString("contactAddressDetails",contactAddressDetails);
                    intent.putExtra("bundle",bundle);
                }
                else{
                    Toast.makeText(AddNewAddressActivity.this,"联系人详细地址不能为空！",Toast.LENGTH_SHORT).show();
                }
                if(contactName!=null&&contactPhone!=null&&contactAddressDetails!=null){
                    intent.setClass(AddNewAddressActivity.this,AddressActivity.class);
                    setResult(0,intent);
                    AddNewAddressActivity.this.finish();
                }
        }
        });
    }

    private void initView() {
        view = (SettingItemView) findViewById(R.id.setDefault);
        save = (Button) findViewById(R.id.bt_save);
        et_contactName = (EditText) findViewById(R.id.et_contactName);
        et_contactPhone = (EditText) findViewById(R.id.et_contactPhone);
        et_addressDetails = (EditText) findViewById(R.id.et_contactAddressDetails);
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
