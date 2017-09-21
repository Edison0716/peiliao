package com.wenmingkeji.peiliao.adaper;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bevis on 2016/6/28.
 */
public class ViewPagerAdapter extends PagerAdapter{
    
    
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private onItemClickListener onItemClickListener;

    public ViewPagerAdapter(Context context, int [] imgIds) {
        for (int i = 0; i < imgIds.length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imgIds[i]);
            mImageViews.add(imageView);
        }
        
    }
    
    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        
        ImageView view = mImageViews.get(position);
        container.addView(view);
        return mImageViews.get(position);
    }

    @Override
    public int getCount() {
        //设置成最大，使用户看不到边界
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }
}
