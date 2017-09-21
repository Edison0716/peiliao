package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.wenmingkeji.peiliao.R;


/**
 * Created by 10564 on 2016/7/11.
 */
public class SelectCityActivity extends Activity {
    private GridView gv_cityItem;
    private ImageButton ib_back;
    private String[] CityName = new String[]{
            "北京","黑龙江省","天津","北京","上海","天津","北京","上海","天津",
            "北京","上海","天津","北京","上海","天津","北京","上海","天津",
            "北京","上海","天津","北京","上海","天津","北京","上海","天津",
            "北京","上海","天津","北京","上海","天津","北京","上海","天津",
            "北京","上海","天津","北京","上海","天津","北京","上海","天津"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_city);
        initBack();
        initCityItems();
    }
    private void initCityItems() {
        gv_cityItem = (GridView) findViewById(R.id.gv_city_item);

        gv_cityItem.setAdapter(new ItemAdapter());
    }

    class ItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return CityName.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = View.inflate(getApplicationContext(), R.layout.list_item_city,null);
            Button bt_citySelect = (Button) view.findViewById(R.id.bt_cityName);
            bt_citySelect.setText(CityName[i]);
          /*  if(i==1){
                String[] provinceName = new String[]{
                        "哈尔滨市","齐齐哈尔市","鹤岗市","双鸭山市","鸡西市"
                        ,"大庆市","伊春市"," 牡丹江市","佳木斯市","黑河市","绥化市"
                };

            }*/
            return view;
        }

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
