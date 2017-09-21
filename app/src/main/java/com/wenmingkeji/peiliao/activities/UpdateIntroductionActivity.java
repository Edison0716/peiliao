package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/27.
 */
public class UpdateIntroductionActivity extends Activity{
    private ImageButton ib_back;
    private WebView wv_show;
    private String introduceInfo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_update);
        introduceInfo = getIntent().getExtras().get("introduceInfo").toString();
        initBack();
        showIntroduction();
    }

    private void showIntroduction() {
        wv_show = (WebView) findViewById(R.id.wv_updateIntroduction);
        wv_show.loadUrl(introduceInfo);
        wv_show.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
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
