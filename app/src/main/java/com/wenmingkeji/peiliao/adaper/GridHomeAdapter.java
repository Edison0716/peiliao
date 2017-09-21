package com.wenmingkeji.peiliao.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.NameWithIcon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/6/27.
 */
public class GridHomeAdapter extends BaseAdapter {

    private NameWithIcon mNameWithIcon;
    LayoutInflater mInflater;

    public GridHomeAdapter(Context context, NameWithIcon nameWithIcon) {
        this.mNameWithIcon = nameWithIcon;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mNameWithIcon.getNames().length;
    }

    @Override
    public Object getItem(int position) {
        return mNameWithIcon;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        NameWithIcon nameWithIcon = (NameWithIcon) getItem(position);
        String [] names = nameWithIcon.getNames();
        int [] icons = nameWithIcon.getIcons();
        
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_grid_category, null);
            viewHolder = new ViewHolder(view);
            viewHolder.mIvImg.setImageResource(icons[position]);
            viewHolder.mTvName.setText(names[position]);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_img)
        ImageView mIvImg;
        @BindView(R.id.tv_user_name)
        TextView mTvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
