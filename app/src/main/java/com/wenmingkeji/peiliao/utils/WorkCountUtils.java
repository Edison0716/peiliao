package com.wenmingkeji.peiliao.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 10564 on 2016/7/21.
 */
public class WorkCountUtils {
    public CharSequence temp;
    public int selectionStart;
    public int selectionEnd;
    public void initWordCount(final EditText content, final TextView hasnumTV, final int num) {
        content.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number =  num - s.length();
                hasnumTV.setText(number+"/400");
                selectionStart = content.getSelectionStart();
                selectionEnd = content.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    content.setText(s);
                    content.setSelection(tempSelection);//设置光标在最后
                }
            }


        });
    }
}
