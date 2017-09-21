package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10564 on 2016/7/14.
 */
public class AddressActivity extends Activity{
    private ImageButton ib_back;
    Intent intent = new Intent();
    private ListView address;
    private Button addAddress;
    private String contactName;
    private String contactPhone;
    private String contactAddressDetails;
    private List<String> name;
    private List<String> phoneNum;
    private List<String> addressContent;
    private Boolean defaultChecked;
    private boolean addressDefault;
    private List<Boolean> checked;
    private Boolean defaultChecked1;
    private int clickPosition = -1;
    private ViewHolderAdapter myAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_address);
        initData();
        initBack();
        addAddress();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(0 == requestCode){
            if (0 == resultCode){
            Bundle bundle = data.getBundleExtra("bundle");
                contactName = bundle.getString("contactName");
                contactPhone = bundle.getString("contactPhone");
                contactAddressDetails = bundle.getString("contactAddressDetails");
                defaultChecked1 = bundle.getBoolean("default_checked");
                name.add(contactName);
                phoneNum.add(contactPhone);
                addressContent.add(contactAddressDetails);
                checked.add(defaultChecked1);
            Toast.makeText(AddressActivity.this, contactName + contactPhone + contactAddressDetails,Toast.LENGTH_SHORT).show();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addAddress() {
        addAddress = (Button) findViewById(R.id.addAddress);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AddressActivity.this,AddNewAddressActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    private void initData() {
        checked = new ArrayList<Boolean>();
        name = new ArrayList<String>();
        phoneNum = new ArrayList<String>();
        addressContent = new ArrayList<String>();
        address = (ListView) findViewById(R.id.lv_address);
        myAdapter = new ViewHolderAdapter(AddressActivity.this, name, phoneNum, addressContent,checked);
        address.setAdapter(myAdapter);
       /* address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                final CheckBox cb_default = (CheckBox) view.findViewById(R.id.cb_whetherDefault);
                cb_default.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(cb_default.isChecked()==true){
                            Toast.makeText(AddressActivity.this,"必须选中一个默认地址",Toast.LENGTH_SHORT).show();
                        }else{
                            cb_default.setChecked(true);
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        /*address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=clickPosition){
                    clickPosition = position;
                }else
                    clickPosition=-1;
            }

        });
        myAdapter.notifyDataSetChanged();*/
    }

    private void initBack() {
        ib_back = (ImageButton) findViewById(R.id.ic_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(AddressActivity.this,SettingActivity.class);
                finish();
            }

        });
    }


    private class ViewHolderAdapter extends BaseAdapter {
       private List<String> mName ;
       private List<String> mPhoneNum ;
       private List<String> mAddressContent ;
        private List<Boolean>mDefaultChecked;
        private LayoutInflater mInflater;
        public ViewHolderAdapter (Context context,List<String>name,List<String>phoneNum,List<String>addressContent,List<Boolean>defaultChecked){
            this.mName = name;
            this.mPhoneNum = phoneNum;
            this.mAddressContent = addressContent;
            this.mDefaultChecked = defaultChecked;
            mInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mName.size();
        }

        @Override
        public Object getItem(int position) {
            return mName.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(holder == null){
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_address_item,null);
                holder.userName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.phoneNum = (TextView) convertView.findViewById(R.id.tv_phoneNum);
                holder.addressContent = (TextView) convertView.findViewById(R.id.tv_addressName);
                holder.delete = (TextView) convertView.findViewById(R.id.tv_deleteAddress);
                holder.cb_default = (CheckBox) convertView.findViewById(R.id.cb_whetherDefault);
                holder.edit =  (TextView)convertView.findViewById(R.id.tv_editAddress);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.userName.setText(mName.get(position));
            holder.phoneNum.setText(mPhoneNum.get(position));
            holder.addressContent.setText(mAddressContent.get(position));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(AddressActivity.this).setTitle("确认删除？").setMessage("确定删除选中的地址吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mName.remove(position);
                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            });
            holder.cb_default.setChecked(mDefaultChecked.get(position));
           final ViewHolder finalHolder = holder;
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userName = finalHolder.userName.getText().toString().trim();
                    String userPhoneNum = finalHolder.phoneNum.getText().toString().trim();
                    String userAddressDetails = finalHolder.addressContent.getText().toString().trim();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent();
                    bundle.putString("userName",userName);
                    bundle.putString("userPhoneNum",userPhoneNum);
                    bundle.putString("userAddressDetails",userAddressDetails);

                    intent.putExtra("bundle",bundle);

                    intent.setClass(AddressActivity.this,EditAddressActivity.class);
                    startActivityForResult(intent,0);
                    AddressActivity.this.finish();
                }
            });

            return convertView;
        }
    }
    public final class ViewHolder{
        public TextView userName;
        public TextView addressContent;
        public TextView phoneNum;
        public TextView delete;
        public CheckBox cb_default;
        public TextView edit;
    }
}
