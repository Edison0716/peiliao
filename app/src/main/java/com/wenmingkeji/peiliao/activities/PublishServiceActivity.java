package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.fragment.ServicePublishFragment1;

public class PublishServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_service);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .add(R.id.container_publish_service, new ServicePublishFragment1())
                .commit();
    }
}
