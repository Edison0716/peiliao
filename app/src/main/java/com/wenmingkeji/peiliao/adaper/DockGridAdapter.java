package com.wenmingkeji.peiliao.adaper;

import android.content.Context;
import android.graphics.Color;
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
 * Created by bevis on 2016/6/23.
 */
public class DockGridAdapter extends BaseAdapter {
    NameWithIcon mNameWithIcon;
    LayoutInflater mInflater;

    public DockGridAdapter(Context context, NameWithIcon nameWithIcon) {
        this.mNameWithIcon = nameWithIcon;
        mInflater = LayoutInflater.from(context);
    }

    private int selectedPosition = -1;

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public int getCount() {
        return mNameWithIcon.getIcons().length;
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
        View view = null;
        NameWithIcon nameWithIcon = (NameWithIcon) getItem(position);
        ViewHolder viewHolder;

        String [] names = nameWithIcon.getNames();
        int [] icons = nameWithIcon.getIcons();
        
        
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_grid_dock, null);

            viewHolder = new ViewHolder(view);
            viewHolder.mDockName.setText(names[position]);
            viewHolder.mDockIcon.setImageResource(icons[position]);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        
        if (selectedPosition == position) {
            view.setBackgroundColor(Color.parseColor("#f8bc00"));
        } else {
            view.setBackgroundColor(Color.parseColor("#f8cd00"));
        }
        
        
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.dockIcon)
        ImageView mDockIcon;
        @BindView(R.id.dockName)
        TextView mDockName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
