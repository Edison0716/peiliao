package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/19.
 */
public class BalanceActivity extends Activity{
    Intent in = new Intent();
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_balance);
        turnToDetails();
        turnToRecharge();
        turnToDrawMoneyRecord();
        initBack();
        turnToChooseCardTypeActivity();
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
    private void turnToChooseCardTypeActivity() {
        RelativeLayout rl_drawMoney = (RelativeLayout) findViewById(R.id.rl_drawMoney);
        rl_drawMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setClass(BalanceActivity.this,ChooseCardTypeActivity.class);
                startActivity(in);
            }
        });
    }



    private void turnToDrawMoneyRecord() {
        RelativeLayout rl_drawMoneyRecord = (RelativeLayout) findViewById(R.id.rl_getMoneyRecord);
        rl_drawMoneyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setClass(BalanceActivity.this,DrawMoneyRecord.class);
                startActivity(in);
            }
        });

    }
    private void turnToRecharge() {
        RelativeLayout rl_recharge = (RelativeLayout) findViewById(R.id.rl_recharge);
        rl_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.setClass(BalanceActivity.this,RechargeActivity.class);
                startActivity(in);
            }
        });
    }

    private void turnToDetails() {
        RelativeLayout rl_balanceDetails = (RelativeLayout) findViewById(R.id.rl_balanceDetails);
        rl_balanceDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    in.setClass(BalanceActivity.this,IncomeDetailsActivity.class);
                startActivity(in);
            }
        });
    }
}
