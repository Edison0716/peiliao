package com.wenmingkeji.peiliao.adaper;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.orhanobut.logger.Logger;
import com.wenmingkeji.peiliao.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/20.
 */
public class MultiImageAdapter extends BaseAdapter {
    ImagePicker imagePicker = ImagePicker.getInstance();
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<ImageItem> mImages;
    //用来判断是否是刚刚进入，刚进入只显示添加按钮，也就是上面java代码中只传this的时候
    private boolean is = false;

    public MultiImageAdapter(Activity activity, ArrayList<ImageItem> images) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        this.mImages = images;
        initImagePicker();//设置图片选择的一些属性
    }

    public MultiImageAdapter(Activity activity) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        is = true;//设置为true表示第一次初始化
        initImagePicker();//设置图片选择的一些属性
    }


    @Override
    public int getCount() {
        if (!is) {
            //这里判断数据如果有9张就size等于9,否则就+1，+1是为按钮留的位置
            return mImages.size() == 9 ? mImages.size() : mImages.size() + 1;
        }
        //没有数据就是1，1是为按钮留的位置
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return mImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_list_grid_image, null);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        if (!is) {
            //选了图片后会进入这里，先判断下position 是否等于size
            if (position == mImages.size()) {
                //执行到这里就说明是最后一个位置，判断是否有9张图
                if (mImages.size() != 9) {
                    //没有9张图就显示添加按钮
                    holder.mIbtnAddImg.setVisibility(View.VISIBLE);
                } else {
                    //有就隐藏
                    holder.mIbtnAddImg.setVisibility(View.GONE);
                }
            } else {
                //还不是最后一个位置的时候执行这里
                //隐藏添加按钮，要设置图片嘛~
                holder.mIbtnAddImg.setVisibility(View.GONE);
                //根据条目位置设置图片
                ImageItem item = mImages.get(position);
                Glide.with(activity)
                        .load(item.path)
                        .into(holder.mIvImg);
            }
            //删除按钮的点击事件
            holder.mIbtnDeleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logger.d("delete img");

                    Logger.d(position);

                    Logger.d(mImages);
                    //移除图片
                    mImages.remove(position);
                    Logger.d(mImages.size());

                    Logger.d(getCount());
                    //更新
                    notifyDataSetChanged();

                }
            });
        } else {
            //初次初始化的时候显示添加按钮
            holder.mIbtnAddImg.setVisibility(View.VISIBLE);
        }
        //添加按钮点击事件
        holder.mIbtnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否是初始化进入
                if (!is) {
                    //到这里表示已经选过了，然后用9-size算出还剩几个图的位置
                    imagePicker.setSelectLimit(9 - mImages.size());//选中数量限制
                }
                //跳转到图片选择
                Intent intent = new Intent(activity, ImageGridActivity.class);
                activity.startActivityForResult(intent, 3);
            }
        });
        return view;
    }


    private void initImagePicker() {
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);//显示拍照按钮
        imagePicker.setCrop(true);//允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);//是否按矩形区域保存
        imagePicker.setSelectLimit(9);//选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);//裁剪框的形状
        imagePicker.setFocusWidth(100);//裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(100);//裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    class ViewHolder {
        @BindView(R.id.iv_img)
        ImageView mIvImg;
        @BindView(R.id.ibtn_delete_img)
        ImageButton mIbtnDeleteImg;
        @BindView(R.id.ibtn_add_img)
        ImageButton mIbtnAddImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
