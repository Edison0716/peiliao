package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;

/**
 * Created by 10564 on 2016/7/28.
 */
public class WelcomeActivity extends Activity{
    int num = 140;
    private CharSequence temp;
    private int selectionStart;
    private int selectionEnd;
    private TextView hasnumTV;
    private EditText welcomeContent;
    private ImageButton ib_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initBack();
        wordCount();
    }

    private void wordCount() {
        hasnumTV = (TextView) findViewById(R.id.tv_wordCount);
        hasnumTV.setText(num+"/140");
        welcomeContent = (EditText) findViewById(R.id.et_welcomeContent);
        welcomeContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                hasnumTV.setText(number+"/140");
                selectionStart = welcomeContent.getSelectionStart();
                selectionEnd = welcomeContent.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    welcomeContent.setText(s);
                    welcomeContent.setSelection(tempSelection);//设置光标在最后
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
