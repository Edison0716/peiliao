package com.wenmingkeji.peiliao.activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.PrefUtils;
import com.wenmingkeji.peiliao.utils.StreamUtils;
import com.wenmingkeji.peiliao.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 10564 on 2016/7/13.
 */
public class SettingActivity extends Activity {
    private ImageButton ib_back;
    private RelativeLayout rl_personalData;
    private RelativeLayout rl_setAddress;
    private RelativeLayout rl_checkUpdate;
    private static final int CODE_UPDATE_DIALOG = 1;
    private static final int CODE_ENTER_HOME = 2;
    private static final int CODE_URL_ERROR = 3;
    private static final int CODE_NETWORK_ERROR = 4;
    private static final int CODE_JSON_ERROR = 5;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private String mVersionName;
    private int mVersionCode;
    private String mDes;
    private String mUrl;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_UPDATE_DIALOG:
                    showUpdateDialog();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(SettingActivity.this, "网络连接错误！", Toast.LENGTH_LONG).show();
                    enterHome();
                    break;
                case CODE_NETWORK_ERROR:
                    Toast.makeText(SettingActivity.this, "网络异常！", Toast.LENGTH_LONG).show();
                    enterHome();
                    break;
                case CODE_JSON_ERROR:
                    Toast.makeText(SettingActivity.this, "数据解析异常！", Toast.LENGTH_LONG).show();
                    enterHome();
                    break;
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        initBack();
        initPersonalData();
        settingAdderss();
        checkUpdate();
        turnToNotiNews();
    }
    public  void quitPrevious(View v){
        PrefUtils.putBoolean("whether_bind",false,getApplicationContext());
        PrefUtils.putBoolean("whether_register",false,getApplicationContext());
        //ToastUtil.show("退出成功！");
        Toast.makeText(SettingActivity.this,"退出成功！",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(SettingActivity.this,RegisterActivity.class);
        startActivity(intent);
        SettingActivity.this.finish();
    }
   private void turnToNotiNews() {
        RelativeLayout rl_notiNews = (RelativeLayout) findViewById(R.id.rl_noti_news);
       rl_notiNews.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.setClass(SettingActivity.this,NotiNewsActivity.class);
               startActivity(intent);
           }
       });
    }

    private void checkUpdate() {
        rl_checkUpdate = (RelativeLayout) findViewById(R.id.rl_checkUpdate);
        rl_checkUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVersion();
            }
        });
    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发现新版本：" + mVersionCode + ".0");
        builder.setMessage(mDes);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            int currentapiVersion=android.os.Build.VERSION.SDK_INT;
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentapiVersion>=23){
                    verifyStoragePermissions(SettingActivity.this);
                }else{
                    downloadApk();
                }

            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome();
            }
        });
        builder.show();
    }

    private void enterHome() {
       // ToastUtil.show("更新失败！");
        Toast.makeText(this,"更新失败！",Toast.LENGTH_SHORT).show();
    }

    private void downloadApk() {
        //判断是否存在SD Card
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            /*设置下载路径*/
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Mobile-Safe.apk";
            HttpUtils utils = new HttpUtils();
            utils.download(mUrl, path, new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                }

                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    String absolutePath = responseInfo.result.getAbsolutePath();
                    Toast.makeText(SettingActivity.this, "下载成功：" + absolutePath, Toast.LENGTH_LONG).show();
                    /*设置跳转到安装界面*/
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(intent.CATEGORY_DEFAULT);
                    intent.setDataAndType(Uri.fromFile(responseInfo.result), "application/vnd.android.package-archive");
                    startActivityForResult(intent, 0);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(SettingActivity.this, "没有SDCard，下载失败！", Toast.LENGTH_LONG).show();
        }
    }

    private void checkVersion() {
        new Thread(){
            @Override
            public void run() {
                Message msg = Message.obtain();
                long currentTimeMillis = System.currentTimeMillis();
                HttpURLConnection conn = null;
                    try {
                        conn = (HttpURLConnection) new URL("http://192.168.1.132:8080/PeiLiaoUpdate.json").openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(2000);
                        conn.setReadTimeout(2000);
                        conn.connect();
                        int code = conn.getResponseCode();
                        if(code == 200){
                            InputStream in = conn.getInputStream();
                            String result = StreamUtils.stream2String(in);
                            JSONObject object=null;
                            object = new JSONObject(result);
                            mVersionName =  object.getString("versionName");
                            mVersionCode =  object.getInt("versionCode");
                            mDes =  object.getString("des");
                            mUrl =  object.getString("url");
                            if(getVersionCode()<mVersionCode){
                                msg.what = CODE_UPDATE_DIALOG;
                            }else{
                                msg.what = CODE_ENTER_HOME;
                            }
                        }
                    }catch (MalformedURLException e) {
                        e.printStackTrace();
                        msg.what = CODE_URL_ERROR;
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        msg.what = CODE_NETWORK_ERROR;
                    }catch (JSONException e) {
                        e.printStackTrace();
                        msg.what = CODE_JSON_ERROR;
                    }finally {
                        if(conn!=null){
                            conn.disconnect();
                        }
                        long endTime = System.currentTimeMillis();
                        long timeUsed = endTime - currentTimeMillis;//计算访问网络用的时间
                        try {
                            if (timeUsed < 2000) {
                                Thread.sleep(1000 * 2 - timeUsed);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendMessage(msg);
                    }
            }
        }.start();
    }

    private void settingAdderss() {
        rl_setAddress =  (RelativeLayout) findViewById(R.id.rl_setAddress);
        rl_setAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPersonalData() {
         rl_personalData = (RelativeLayout) findViewById(R.id.rl_personalData);
         rl_personalData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(SettingActivity.this,PersonalDataActivity.class);
                 startActivity(intent);
             }
         });
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

    private int getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Integer.parseInt("");
        }
    }

    //6.0 以后仍然要加过程中加读和写权限
    public void verifyStoragePermissions(Activity activity) {
             // Check if we have write permission
               int permission = ActivityCompat.checkSelfPermission(activity,
                               Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permission != PackageManager.PERMISSION_GRANTED) {
                         // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                                       REQUEST_EXTERNAL_STORAGE);
                        downloadApk();
                    }else{
                    downloadApk();
                }
             }

}
