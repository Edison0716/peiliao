package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/13.
 */
public class DataCompleteActivity extends Activity{
    private ImageButton ib_back;
    private RelativeLayout introduceContent;
    Intent in = new Intent();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_datacomplete);
        initBack();
        turnToIntroduceMyself();
    }

    private void turnToIntroduceMyself() {
        RelativeLayout introMysdlf = (RelativeLayout) findViewById(R.id.rl_introduceMyself);
        introMysdlf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setClass(DataCompleteActivity.this,IntroduceYourselfActivity.class);
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
