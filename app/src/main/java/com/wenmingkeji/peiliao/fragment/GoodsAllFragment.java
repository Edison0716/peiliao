package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;


/**
 * Created by 10564 on 2016/7/11.
 */
public class GoodsAllFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_goods_all,null);
        ListView lv_goodsDetails = (ListView) view.findViewById(R.id.lv_GoodsDetails);
        lv_goodsDetails.setAdapter(new MyAdapter());
        return view;
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 2;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(getContext(), R.layout.list_item_goodsdetails,null);
            return v;
        }
    }
}
