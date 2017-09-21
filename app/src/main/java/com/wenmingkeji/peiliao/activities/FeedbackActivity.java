package com.wenmingkeji.peiliao.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.ImageTools;
import com.wenmingkeji.peiliao.utils.ToastUtil;
import com.wenmingkeji.peiliao.view.SmoothCheckBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 10564 on 2016/7/13.
 */
public class FeedbackActivity extends Activity{
    int num = 400;
    private CharSequence temp;
    private int selectionStart;
    private int selectionEnd;
    private ImageButton ib_back;
    private ImageButton ib_takePhoto;
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_PICTURE = 1;
    private static final int SCALE = 5;//照片缩小比例
    ImageView iv_image=null;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE=1;
    private SmoothCheckBox scb1;
    private SmoothCheckBox scb2;
    private SmoothCheckBox scb3;
    private SmoothCheckBox scb4;
    private RelativeLayout rl_productIssue;
    private RelativeLayout rl_accountException;
    private RelativeLayout rl_aboutActivity;
    private RelativeLayout rl_others;
    private TextView hasnumTV;
    private EditText feedbackContent;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_feedback);
        iv_image =  (ImageView) findViewById(R.id.iv_show);
        initWordCount();
        initBack();
        initSingleChoice();
        ib_takePhoto =  (ImageButton) findViewById(R.id.ib_takePhoto);
        ib_takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentapiVersion=android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion>=23) {
                    //6.0以后必须添加个过程中的权限，若不添加回报错
                    if (ContextCompat.checkSelfPermission(FeedbackActivity.this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(FeedbackActivity.this, new String[]{Manifest.permission.CAMERA},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    }else{showPicturePicker(FeedbackActivity.this);}
                }else{showPicturePicker(FeedbackActivity.this);}
            }
        });
    }

    private void initWordCount() {
        hasnumTV = (TextView) findViewById(R.id.tv_wordCount);
        hasnumTV.setText(num+"/400");
        feedbackContent = (EditText) findViewById(R.id.et_feedbackContent);
        feedbackContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                hasnumTV.setText(number+"/400");
                selectionStart = feedbackContent.getSelectionStart();
                selectionEnd = feedbackContent.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    feedbackContent.setText(s);
                    feedbackContent.setSelection(tempSelection);//设置光标在最后
                }
            }


        });
    }

    private void initSingleChoice() {
        scb1 = (SmoothCheckBox) findViewById(R.id.scb1);
        scb2 = (SmoothCheckBox) findViewById(R.id.scb2);
        scb3 = (SmoothCheckBox) findViewById(R.id.scb3);
        scb4 = (SmoothCheckBox) findViewById(R.id.scb4);
        scb1.setChecked(true);
        rl_productIssue = (RelativeLayout) findViewById(R.id.rl_productIssues);
        rl_accountException = (RelativeLayout) findViewById(R.id.rl_accountException);
        rl_aboutActivity = (RelativeLayout) findViewById(R.id.rl_aboutActivity);
        rl_others = (RelativeLayout) findViewById(R.id.rl_others);
        scb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb1.isChecked()==false){
                    scb1.setChecked(true,true);
                    scb2.setChecked(false);
                    scb3.setChecked(false);
                    scb4.setChecked(false);
                }
            }
        });
        rl_productIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb1.isChecked()==false){
                    scb1.setChecked(true,true);
                    scb2.setChecked(false);
                    scb3.setChecked(false);
                    scb4.setChecked(false);
                }
            }
        });
        scb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb2.isChecked()==false){
                    scb1.setChecked(false);
                    scb2.setChecked(true,true);
                    scb3.setChecked(false);
                    scb4.setChecked(false);
                }
            }
        });
        rl_accountException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb2.isChecked()==false){
                    scb1.setChecked(false);
                    scb2.setChecked(true,true);
                    scb3.setChecked(false);
                    scb4.setChecked(false);
                }
            }
        });
        scb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb3.isChecked()==false){
                    scb1.setChecked(false);
                    scb2.setChecked(false);
                    scb3.setChecked(true,true);
                    scb4.setChecked(false);
                }
            }
        });
        rl_aboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb3.isChecked()==false){
                    scb1.setChecked(false);
                    scb2.setChecked(false);
                    scb3.setChecked(true,true);
                    scb4.setChecked(false);
                }
            }
        });
        scb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb4.isChecked()==false){
                    scb1.setChecked(false);
                    scb2.setChecked(false);
                    scb3.setChecked(false);
                    scb4.setChecked(true,true);
                }
            }
        });
        rl_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scb4.isChecked()==false){
                    scb1.setChecked(false);
                    scb2.setChecked(false);
                    scb3.setChecked(false);
                    scb4.setChecked(true,true);
                }
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
    public void showPicturePicker(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("图片来源");
        builder.setNegativeButton("取消", null);
        builder.setItems(new String[]{"拍照","相册"}, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PICTURE:
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                        //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;

                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    //将保存在本地的图片取出并缩小后显示在界面上
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/image.jpg");
                    Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
                    //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
                    bitmap.recycle();

                    //将处理过的图片显示在界面上，并保存到本地
                    iv_image.setImageBitmap(newBitmap);
                    ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
                    break;

                case CHOOSE_PICTURE:
                    ContentResolver resolver = getContentResolver();
                    //照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {
                        //使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            //为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                            Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
                            //释放原始图片占用的内存，防止out of memory异常发生
                            photo.recycle();

                            iv_image.setImageBitmap(smallBitmap);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                showPicturePicker(FeedbackActivity.this);
            } else {
                // Permission Denied
                ToastUtil.show("请赋予权限才能拍照！");
            }
        }
    }
}
