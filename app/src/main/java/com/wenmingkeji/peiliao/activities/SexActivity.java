package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.view.SmoothCheckBox;

/**
 * Created by 10564 on 2016/7/13.
 */
public class SexActivity extends Activity
{
    private RelativeLayout rl_man;
    private RelativeLayout rl_woman;
    private SmoothCheckBox scb_man;
    private SmoothCheckBox scb_woman;
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sex);
        initSingleChoice();
        initBack();
    }
    private void initSingleChoice() {
        scb_man = (SmoothCheckBox) findViewById(R.id.r1_man);
        scb_woman = (SmoothCheckBox) findViewById(R.id.r1_woman);
        rl_man = (RelativeLayout) findViewById(R.id.rl_sex_man);
        rl_woman = (RelativeLayout) findViewById(R.id.rl_sex_woman);
        scb_man.setChecked(true);
        scb_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb_man.isChecked()==false){
                    scb_man.setChecked(true,true);
                    scb_woman.setChecked(false,true);
                }
            }
        });
        rl_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb_man.isChecked()==false){
                    scb_man.setChecked(true,true);
                    scb_woman.setChecked(false,true);
                }
            }
        });
        rl_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb_woman.isChecked()==false){
                    scb_man.setChecked(false,true);
                    scb_woman.setChecked(true,true);
                }
            }
        });
        scb_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb_woman.isChecked()==false){
                    scb_man.setChecked(false,true);
                    scb_woman.setChecked(true,true);
                }
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
