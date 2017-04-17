package com.demo.cn;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        findViewById(R.id.btn_grant).setOnClickListener(this);
        findViewById(R.id.btn_version).setOnClickListener(this);

        findViewById(R.id.btn_04).setOnClickListener(this);
        image= (ImageView) findViewById(R.id.image);
        findViewById(R.id.btn_06).setOnClickListener(this);
        findViewById(R.id.btn_07).setOnClickListener(this);
        findViewById(R.id.btn_08).setOnClickListener(this);
    }

    public void checkPermissionSdCard(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED){
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                showMessageOKCancel("授予权限", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                200);
                    }
                });
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                        200);
            }

        }else{
            Toast.makeText(this,"this已授权",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==200){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"已授权",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"未授权",Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_grant:
                checkPermissionSdCard();
                takePhoto();
                break;
            case R.id.btn_version:
                getAndroidSDKVersion();
                break;

                //ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                Glide.with(this)
//                        .load("http://img4.imgtn.bdimg.com/it/u=3222645689,1083516885&fm=21&gp=0.jpg")
//                        .into(image);




            case R.id.btn_04:
                startActivity(new Intent(this,CircleProgressBarActivity.class));
                break;
            case R.id.btn_05:
                startActivity(new Intent(this,CircleProgressBarActivity.class));
            case R.id.btn_06:
                startActivity(new Intent(this,ViewPaperActivity.class));
                break;
            case R.id.btn_07:
                break;
            case R.id.btn_08:
                startActivity(new Intent(this,ImageCodeActivity.class));
                break;
        }

    }


    public static int getAndroidSDKVersion() {
        int version=0;
        try {
            int version1 = Build.VERSION.SDK_INT;
            Log.e("ss",version+"");
            Log.e("ssss",version1+"");
        } catch (NumberFormatException e) {
            Log.e("ss",e.toString());
        }


        sss();
        int v=Build.VERSION_CODES.M;
        return version;
    }

    private static void sss() {
        int s= Build.VERSION.SDK_INT;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private void takePhoto(){
        Intent intentFromCapture = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)) {
            intentFromCapture.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(
                            Environment
                                    .getExternalStorageDirectory(),
                            "image.png")));
        }
      //  startActivityForResult(intentFromCapture,
      //          200);
    }
    private void test(){
        File file=new File("D://test.txt");
        file.lastModified();
    }
}
