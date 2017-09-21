package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/14.
 */
public class WorkNumActivity extends Activity{
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_worknum);
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
}
