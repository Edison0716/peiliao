package com.wenmingkeji.peiliao.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 10564 on 2016/7/12.
 */
public class CollectionActivity extends Activity {
    private View contentView1;
    private PopupWindow popuWindow1;
    private ImageButton ib_back;
    private Button deleteYes;
    private GridView dvCollect;
    private List<String> fountion;
    private MyAdapter myAdapter;
    private RelativeLayout allItem;
    private Button deleteNo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_collection);

        initBack();
        initData();
    }

    private void initData() {
        List<Icon>headImage = new ArrayList<Icon>();
        List<Icon>userPhoto = new ArrayList<Icon>();
        fountion = new ArrayList<String>();
        fountion.add("陪你聊天");
        fountion.add("陪你下棋");
        fountion.add("陪你LOL");
        fountion.add("陪你逛街");
        List<String>howMuch = new ArrayList<String>();
        howMuch.add("2");
        howMuch.add("4");
        howMuch.add("5");
        howMuch.add("6");
        List<String>address = new ArrayList<String>();
        address.add("哈尔滨市道外区");
        address.add("哈尔滨市南岗区");
        address.add("哈尔滨市道里区");
        address.add("哈尔滨市道里区");
        dvCollect = (GridView) findViewById(R.id.gv_collection);
        dvCollect.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                allItem = (RelativeLayout) view.findViewById(R.id.rl_allItem);
                if (position==0){
                    allItem.setVisibility(View.VISIBLE);
                    initPopnuWindow1(parent,position);
                }
                if (position==1){
                    allItem.setVisibility(View.VISIBLE);
                    initPopnuWindow1(parent,position);
                }
                if (position==2){
                    allItem.setVisibility(View.VISIBLE);
                    initPopnuWindow1(parent,position);
                }
                if (position==3){
                    allItem.setVisibility(View.VISIBLE);
                    initPopnuWindow1(parent,position);
                }

                return false;
            }
        });
        myAdapter = new MyAdapter(CollectionActivity.this, headImage, userPhoto, fountion, howMuch, address);
        dvCollect.setAdapter(myAdapter);
    }





    public final class ViewHolder{
        private ImageView headImage1;
        private ImageView userPhoto1;
        private TextView fountion1;
        private TextView address1;
        private TextView howMuch1;
        private ImageButton chatting1;
}
    private class MyAdapter extends BaseAdapter {
        private List<Icon>mHeadImage ;
        private   List<Icon>mUserPhoto ;
        private  List<String>mFountion ;
        private List<String>mHowMuch ;
        private  List<String>mAddress ;
        private LayoutInflater mInflater;
    private MyAdapter(Context context , List<Icon>headImage,List<Icon>userPhoto,
                      List<String>fountion, List<String>howMuch,List<String>address){
        this.mHeadImage = headImage;
        this.mUserPhoto = userPhoto;
        this.mFountion = fountion;
        this.mHowMuch = howMuch;
        this.mAddress  = address;
        mInflater = LayoutInflater.from(context);
    }
        @Override
        public int getCount() {
            return mFountion.size();
        }

        @Override
        public Object getItem(int i) {
            return mFountion.size();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(view==null){
                holder = new ViewHolder();
                view = mInflater.inflate(R.layout.list_collection_items,null);
                holder.userPhoto1= (ImageView) view.findViewById(R.id.userPhoto);
                holder.headImage1= (ImageView) view.findViewById(R.id.headImage);
                holder.fountion1= (TextView) view.findViewById(R.id.fountion);
                holder.address1= (TextView) view.findViewById(R.id.address);
                holder.chatting1= (ImageButton) view.findViewById(R.id.chatting);
                holder.howMuch1= (TextView) view.findViewById(R.id.tv_price);
                view.setTag(holder);
            }else {
               holder = (ViewHolder) view.getTag();
            }
            holder.fountion1.setText(mFountion.get(i));
            holder.address1.setText(mAddress.get(i));
            holder.howMuch1.setText(mHowMuch.get(i));
            holder.chatting1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtil.show("此人正忙！");
                    Toast.makeText(CollectionActivity.this,"此人正忙!",Toast.LENGTH_SHORT).show();
                }
            });
                notifyDataSetChanged();
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
    //初始化popuWindow
    private void initPopnuWindow1(View parent, final int position) {
        if (popuWindow1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView1 = mLayoutInflater.inflate(R.layout.popuwindow1, null);
            popuWindow1 = new PopupWindow(contentView1,ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            deleteYes = (Button) contentView1.findViewById(R.id.delete_Yes);
            deleteYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtil.show("删除选中的Item！");
                    Toast.makeText(CollectionActivity.this,"删除选中的Item！",Toast.LENGTH_SHORT).show();
                    fountion.remove(position);
                    myAdapter.notifyDataSetChanged();
                    popuWindow1.dismiss();
                    allItem.setVisibility(View.INVISIBLE);
                }
            });
            deleteNo = (Button) contentView1.findViewById(R.id.delete_No);
            deleteNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popuWindow1.dismiss();
                    allItem.setVisibility(View.INVISIBLE);
                }
            });
        }
        ColorDrawable cd = new ColorDrawable(0x000000);
        popuWindow1.setBackgroundDrawable(cd);
        //产生背景变暗效果
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        //lp.alpha = 0.4f;
        getWindow().setAttributes(lp);

        popuWindow1.setOutsideTouchable(true);
        popuWindow1.setFocusable(true);
        popuWindow1.showAtLocation((View)parent.getParent(), Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);

        popuWindow1.update();
        popuWindow1.setOnDismissListener(new PopupWindow.OnDismissListener(){

            //在dismiss中恢复透明度
            public void onDismiss(){
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

}
