package com.wenmingkeji.peiliao.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.AlreadyPurchasedActivity;
import com.wenmingkeji.peiliao.activities.AttentionActivity;
import com.wenmingkeji.peiliao.activities.BalanceActivity;
import com.wenmingkeji.peiliao.activities.CollectionActivity;
import com.wenmingkeji.peiliao.activities.DataCompleteActivity;
import com.wenmingkeji.peiliao.activities.FeedbackActivity;
import com.wenmingkeji.peiliao.activities.HadPaidActivity;
import com.wenmingkeji.peiliao.activities.IndividualCertificationActivity;
import com.wenmingkeji.peiliao.activities.ManageCareerFirstActivity;
import com.wenmingkeji.peiliao.activities.MyAssessActivity;
import com.wenmingkeji.peiliao.activities.PersonalAuthenticationActivity;
import com.wenmingkeji.peiliao.activities.PersonalDataActivity;
import com.wenmingkeji.peiliao.activities.PublishServiceActivity;
import com.wenmingkeji.peiliao.activities.SettingActivity;
import com.wenmingkeji.peiliao.activities.TalentPersonalActivity;
import com.wenmingkeji.peiliao.model.MineInfoBeen;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by bevis on 2016/7/7.
 */
public class MineFragment extends Fragment {
    private String json_path = "http://192.168.1.132:8080/caimao/MineActivity.json";
    Intent intent = new Intent();
    private ImageView iv_headImage;
    private LinearLayout ll_headButton;
    private LinearLayout ll_identify;
    private LinearLayout ll_setting;
    private LinearLayout ll_data;
    private LinearLayout ll_feedback;
    private LinearLayout linearLayout;
    private LinearLayout ll_collection;
    private LinearLayout ll_attent;
    private RelativeLayout rl_waitAssess;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private RelativeLayout rl_waitPay;
    private RelativeLayout rl_waitTakingOrder;
    private RelativeLayout rl_waitEnsure;
    private TextView tv_perfectDegree;
    private boolean whether_bind = false;
    private LinearLayout publicService;
    private String avatar;
    private String complete_percent;
    private String user_gender;
    private String user_nick;
    private Boolean identify;
    private ImageView image;
    private MineInfoBeen mineInfoBeen;
    private ImageView icon_sex;
    private TextView nickName;
    private TextView percent;
    private TextView bind;
    private TextView balance;
    private String balance1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getContext(), R.layout.fragment_my,null);
        getInfo();
        initView(v);

        iv_headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(),PersonalDataActivity.class);
                startActivity(intent);
            }
        });
        ll_headButton =  (LinearLayout) v.findViewById(R.id.ll_headButton);
        ll_headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent.setClass(getContext(),TalentPersonalActivity.class);
                    startActivity(intent);
            }
        });
        ll_identify=   (LinearLayout) v.findViewById(R.id.ll_identify);
        ll_identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), PersonalAuthenticationActivity.class);
                startActivity(intent);
            }
        });
        ll_setting =  (LinearLayout) v.findViewById(R.id.ll_setting);
        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent.setClass(getContext(),SettingActivity.class);
                    startActivity(intent);
                    getActivity().finish();
            }
        });
        ll_data =  (LinearLayout) v.findViewById(R.id.ll_dataComplete);
        ll_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), DataCompleteActivity.class);
                startActivity(intent);
            }
        });
       ll_feedback =  (LinearLayout) v.findViewById(R.id.ll_feedback);
        ll_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
        linearLayout = (LinearLayout) v.findViewById(R.id.ll_hotLine);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("客服热线");
                builder.setIcon(R.drawable.icon_me_tel);
                builder.setMessage("您即将拨打客服热线！");
                builder.setPositiveButton("确认拨打", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "拨打" , Toast.LENGTH_SHORT).show();
                        //判断Android版本，如果版本大于23，则要添加过程中的权限
                        int currentapiVersion=android.os.Build.VERSION.SDK_INT;
                        if (currentapiVersion>=23){

                            if (ContextCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED)
                            {

                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            } else
                            {
                                initCall();
                            }

                        }else {
                            initCall();
                        }


                    }
                });
                builder.setNegativeButton("取消拨打",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消拨打" , Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(true);	//设置按钮是否可以按返回键取消,false则不可以取消
                AlertDialog dialog = builder.create();	//创建对话框
                dialog.setCanceledOnTouchOutside(true);	//设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                dialog.show();
            }
        });
                ll_collection = (LinearLayout) v.findViewById(R.id.ll_collect);
        ll_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), CollectionActivity.class);
                startActivity(intent);
            }
        });
        ll_attent =  (LinearLayout) v.findViewById(R.id.ll_attent);
        ll_attent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(),AttentionActivity.class);
                startActivity(intent);
            }
        });

        TextView tv_showAllGoods = (TextView) v.findViewById(R.id.tv_showAllGoods);
        tv_showAllGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), HadPaidActivity.class);
                intent.putExtra("flag", String.valueOf(0));
                startActivity(intent);
            }
        });
        rl_waitPay = (RelativeLayout) v.findViewById(R.id.rl_waitPay);
        rl_waitPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), HadPaidActivity.class);
                intent.putExtra("flag", String.valueOf(1));
                startActivity(intent);
            }
        });
        rl_waitTakingOrder =  (RelativeLayout) v.findViewById(R.id.rl_waitTakingOrder);
        rl_waitTakingOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), HadPaidActivity.class);
                intent.putExtra("flag", String.valueOf(2));
                startActivity(intent);
            }
        });
        rl_waitAssess =  (RelativeLayout) v.findViewById(R.id.rl_waitAssess);
        rl_waitAssess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), MyAssessActivity.class);
                startActivity(intent);
            }
        });
        rl_waitEnsure =  (RelativeLayout) v.findViewById(R.id.waitEnsure);
        rl_waitEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), HadPaidActivity.class);
                intent.putExtra("flag", String.valueOf(3));
                startActivity(intent);
            }
        });
        RelativeLayout rl_refund = (RelativeLayout) v.findViewById(R.id.rl_waitRefund);
        rl_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), HadPaidActivity.class);
                intent.putExtra("flag", String.valueOf(5));
                startActivity(intent);
            }
        });

        LinearLayout ll_manageCareer = (LinearLayout)v.findViewById(R.id.ll_manageCareer);
        ll_manageCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), ManageCareerFirstActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout ll_manage = (LinearLayout) v.findViewById(R.id.ll_manageService);
        ll_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), AlreadyPurchasedActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout rl_balance = (RelativeLayout) v.findViewById(R.id.rl_balance);
        rl_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences read = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                boolean whether_bind = read.getBoolean("whether_bind",true);
                System.out.println(whether_bind);
                if(whether_bind){
                intent.setClass(getContext(), BalanceActivity.class);
                startActivity(intent);
                }else {
                    intent.setClass(getContext(), IndividualCertificationActivity.class);
                    startActivity(intent);
                }
            }
        });
        tv_perfectDegree = (TextView) v.findViewById(R.id.tv_perfectDegree);
        tv_perfectDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), DataCompleteActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout hadSell = (LinearLayout) v.findViewById(R.id.ll_hadSell);
        hadSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(),HadPaidActivity.class);
                intent.putExtra("flag", String.valueOf(0));
                startActivity(intent);
            }
        });
        publicService = (LinearLayout) v.findViewById(R.id.ll_publicService);
        publicService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getContext(), PublishServiceActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void initView(View v) {
        balance = (TextView) v.findViewById(R.id.tv_balance);
        nickName = (TextView) v.findViewById(R.id.tv_nickName);
        image = (ImageView) v.findViewById(R.id.iv_headImage);
        icon_sex = (ImageView) v.findViewById(R.id.icon_sex);
        iv_headImage =  (ImageView) v.findViewById(R.id.iv_headImage);
        percent = (TextView) v.findViewById(R.id.tv_perfectDegree);
        bind = (TextView) v.findViewById(R.id.whetherBind);
    }

    private void getInfo() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, json_path, new RequestCallBack<String>(){

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                mineInfoBeen = gson.fromJson(result, MineInfoBeen.class);
                Boolean success = mineInfoBeen.isSuccess();
                System.out.println(success);
                if(success){
                    setInfo();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void setInfo() {
        avatar = mineInfoBeen.getData().getAvatar_url();
        complete_percent = mineInfoBeen.getData().getComplete_percent();
        user_gender = mineInfoBeen.getData().getUser_gender();
        user_nick = mineInfoBeen.getData().getUser_nick();
        identify = mineInfoBeen.getData().getIdentify();
        balance1 = mineInfoBeen.getData().getUser_balance();
        Picasso.with(getContext()).load(avatar).into(image);
        System.out.println(user_gender);
        if(user_gender.equals("F")){

            Picasso.with(getContext()).load(R.drawable.icon_order_sexwoman).into(icon_sex);
        }else if (user_gender.equals("M")){
            //icon_sex.setImageResource(R.drawable.icon_order_sexman);
            Picasso.with(getContext()).load(R.drawable.icon_order_sexman).into(icon_sex);
        }
       nickName.setText(user_nick);
        percent.setText(complete_percent);
        if(identify){
            bind.setText("已绑定");
        }else{
            bind.setText("未绑定");
        }
       balance.setText(balance1);
    }

    private void initCall() {
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:156654648"));
        startActivity(intent);
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                initCall();
            } else
            {
                // Permission Denied
                Toast.makeText(getContext(), "拨打电话失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
