package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.githang.android.snippet.adapter.ChoiceListAdapter;
import com.wenmingkeji.peiliao.R;

import java.util.ArrayList;
import java.util.List;


public class ChoiceConstellationActivity extends Activity {
    private RadioGroup mChoiceMode;
    private ListView mListView;
    private ImageButton ib_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_choice_list);
        initBack();
        mListView = (ListView) findViewById(android.R.id.list);
        List<String> data = new ArrayList<>();
        data.add("水瓶座");
        data.add("双鱼座");
        data.add("白羊座");
        data.add("金牛座");
        data.add("双子座");
        data.add("巨蟹座");
        data.add("狮子座");
        data.add("处女座");
        data.add("天秤座");
        data.add("天蝎座");
        data.add("射手座");
        data.add("魔羯座");
        final ChoiceListAdapter adapter = new ChoiceListAdapter<String>(this, R.layout.item_single_choice,
                data, R.id.checkedView) {
            @Override
            protected void holdView(ChoiceLayout view) {
                view.hold(R.id.text);
            }

            @Override
            protected void bindData(ChoiceLayout view, int position, String data) {
                TextView text = view.get(R.id.text);
                text.setText(data);
            }
        };
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

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
