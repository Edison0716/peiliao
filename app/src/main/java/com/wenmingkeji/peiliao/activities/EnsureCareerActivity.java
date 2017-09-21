package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 10564 on 2016/7/12.
 */
public class EnsureCareerActivity extends Activity {
    List<String> skills  = new ArrayList<String>();
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ensure_career);
        Intent intent = getIntent();
        String skills1 = intent.getExtras().getString("skills1");
        String skills2 = intent.getExtras().getString("skills2");
        String skills3 = intent.getExtras().getString("skills3");

        skills.add(skills1);
        skills.add(skills2);
        skills.add(skills3);

        ListView lv_career = (ListView) findViewById(R.id.lv_career);
        lv_career.setAdapter(new MyAdapter());
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

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return skills.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(getApplicationContext(), R.layout.list_item_career,null);
            TextView tv_skillName = (TextView) v.findViewById(R.id.skillName);
            tv_skillName.setText(skills.get(i));
            return v;
        }
    }
}
