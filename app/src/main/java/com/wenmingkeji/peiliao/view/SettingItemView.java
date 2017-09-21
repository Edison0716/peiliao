package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;


/**
 * Created by 10564 on 2016/6/1.
 * 自定义组合控件
 *
 */
public class SettingItemView extends RelativeLayout {
    public static final String NAMESPACE = "http://schemas.android.com/apk/res/com.myproject.mobilesafe";
    TextView tvTitle;
    TextView tvDetail;
    CheckBox cbCheck;
    String mDetailOn;
    String mDetailOff;
    public SettingItemView(Context context) {
        super(context);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        int count = attrs.getAttributeCount();
       /* for(int i=0;i<count;i++){
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            //System.out.println(attributeName+attributeValue);
        }*/
        String title = attrs.getAttributeValue(NAMESPACE, "title");

        mDetailOn = attrs.getAttributeValue(NAMESPACE, "detail_on");

        mDetailOff = attrs.getAttributeValue(NAMESPACE, "detail_off");
        setTitile(title);
    }
    /*
    *
    *
    * */
    private void initView(){
        View child = View.inflate(getContext(), R.layout.setting_item_view,null);
        //初始化组合控件布局
        this.addView(child);//将布局添加给当前的RelativeLayout对象
        tvTitle = (TextView) child.findViewById(R.id.tv_title);
        cbCheck = (CheckBox) child.findViewById(R.id.cb_check);
    }
    /*设置标题*/
    public void setTitile(String title){
        tvTitle.setText(title);
    }
    /*设置内容*/
    public void setDetail(String detail){
        tvTitle.setText(detail);
    }
    /*设置CheckBox被选中*/
    public boolean isChecked(){
        return cbCheck.isChecked();
    }
    public void setChecked(boolean checked){
            cbCheck.setChecked(checked);
        if(checked){
            setTitile("设为默认地址");
        }else {
            setTitile("设为默认地址");
        }
    }
}
