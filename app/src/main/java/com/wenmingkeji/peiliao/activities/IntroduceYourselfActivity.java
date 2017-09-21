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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.utils.ImageTools;
import com.wenmingkeji.peiliao.utils.ToastUtil;
import com.wenmingkeji.peiliao.utils.WorkCountUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 10564 on 2016/7/21.
 */
public class IntroduceYourselfActivity extends Activity{
    private ImageButton ib_back;
    private ImageButton takePhoto;
    private ImageView showPhoto;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE=1;
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_PICTURE = 1;
    private static final int SCALE = 5;//照片缩小比例
    public int num = 400;
    public CharSequence temp;
    public int selectionStart;
    public int selectionEnd;
    private EditText content,content1,content2;
    private TextView wordCount,wordCount1,wordCount2;
    private Button save;
    private int flag;
    private String contentFlag;
    private String contentFlag1;
    private String contentFlag2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal_data);
        initBack();
        takePhoto();
        initWordCount();
        savePersonalData();
    }

    private void savePersonalData() {
        save = (Button) findViewById(R.id.save);
        contentFlag = content.getText().toString();
        contentFlag1 = content1.getText().toString();
        contentFlag2 = content2.getText().toString();
        flag = 0;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentFlag !=null&&contentFlag1 !=null&&contentFlag2 !=null) {
                    flag = flag + 3;
                }else if (contentFlag !=null&&contentFlag1 !=null&&contentFlag2 ==null){
                    flag = flag + 2;
                }else if(contentFlag ==null&&contentFlag1 !=null&&contentFlag2 !=null){
                    flag = flag + 2;
                }else if(contentFlag !=null&&contentFlag1 ==null&&contentFlag2 !=null){
                    flag = flag + 2;
                }else if(contentFlag ==null&&contentFlag1 ==null&&contentFlag2 !=null){
                    flag = flag + 1;
                }else if(contentFlag ==null&&contentFlag1 !=null&&contentFlag2 ==null){
                    flag = flag + 1;
                }else if(contentFlag !=null&&contentFlag1 ==null&&contentFlag2 ==null){
                    flag = flag + 1;
                }
               // ToastUtil.show(flag);
            }
        });


    }

    private void initWordCount() {
            content = (EditText) findViewById(R.id.et_introduceMyself);
            wordCount = (TextView) findViewById(R.id.wordCount1);
            content1 = (EditText) findViewById(R.id.et_serviceAdvantage);
            wordCount1 = (TextView) findViewById(R.id.wordCount2);
            content2 = (EditText) findViewById(R.id.et_careerExperience);
            wordCount2 = (TextView) findViewById(R.id.wordCount3);
            wordCount.setText(num+"/400");
            WorkCountUtils workCount = new WorkCountUtils();
            workCount.initWordCount(content,wordCount,num);
            workCount.initWordCount(content1,wordCount1,num);
            workCount.initWordCount(content2,wordCount2,num);
        }


    private void takePhoto() {
        takePhoto = (ImageButton) findViewById(R.id.ib_takePhoto);
        showPhoto = (ImageView) findViewById(R.id.iv_show);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断Android版本，如果版本大于23，则要添加过程中的权限
                int currentapiVersion=android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion>=23){
                    //6.0以后必须添加个过程中的权限，若不添加回报错
                    if (ContextCompat.checkSelfPermission(IntroduceYourselfActivity.this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(IntroduceYourselfActivity.this, new String[]{Manifest.permission.CAMERA},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    }else{
                        showPicturePicker(IntroduceYourselfActivity.this);
                    }
                }else{
                    showPicturePicker(IntroduceYourselfActivity.this);
                }
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
                    showPhoto.setImageBitmap(newBitmap);
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

                            showPhoto.setImageBitmap(smallBitmap);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                showPicturePicker(IntroduceYourselfActivity.this);
            } else {
                // Permission Denied
                ToastUtil.show("请赋予权限才能拍照！");
            }
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
}
