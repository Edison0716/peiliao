package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/19.
 */
public class DrawMoneyRecord extends Activity{
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_drawmoney);
        initBack();
        initData();
    }

    private void initData() {
        ListView lv_drawMoneyRecord = (ListView) findViewById(R.id.lv_recordItem);
        lv_drawMoneyRecord.setAdapter(new MyAdapter());
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

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(getApplicationContext(), R.layout.list_drawmoneyrecord_item,null);
            return v;
        }
    }
}
