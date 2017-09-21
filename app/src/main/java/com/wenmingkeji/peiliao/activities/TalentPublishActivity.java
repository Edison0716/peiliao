package com.wenmingkeji.peiliao.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalentPublishActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_next_step)
    ImageButton mIbtnMore;
    @BindView(R.id.ibtn_publish_txt)
    ImageButton mIbtnPublishTxt;
    @BindView(R.id.ibtn_publish_voice)
    ImageButton mIbtnPublishVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_publish);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.ic_back, R.id.tv_next_step, R.id.ibtn_publish_txt, R.id.ibtn_publish_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.ic_back:
                break;
            case R.id.tv_next_step:
                break;
            case R.id.ibtn_publish_txt:
                startActivity(new Intent(this, PublishTextActivity.class));
                break;
            case R.id.ibtn_publish_voice:
                startActivity(new Intent(this, PublishVoiceActivity.class));
                break;
        }
    }
}
