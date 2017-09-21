package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/20.
 */
public class IndividualCertificationActivity extends Activity{
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualcertification);
        initBack();
        turnToBindAlipayCreditRank();
    }

    private void turnToBindAlipayCreditRank() {
        Button bt_bindCreditRank = (Button) findViewById(R.id.bt_immediateBind);
        bt_bindCreditRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IndividualCertificationActivity.this,StartBindCreditCertificationActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void initBack() {
        ib_back = (ImageButton) findViewById(R.id.ibtn_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
