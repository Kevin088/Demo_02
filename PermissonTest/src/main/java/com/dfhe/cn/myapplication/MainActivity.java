package com.dfhe.cn.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_01:
                checkPermissionSdCard();
                //takePhoto();
                break;
            case R.id.btn_02:
                startActivity(new Intent(this,PermissonActivity.class));
                break;
        }
    }

//ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)  小米一直返回false
    public void checkPermissionSdCard(){
        int checkSelfPermission=-1;
        /**
         * 低于6.0的手机，在安装的时候，将一些权限关闭了，checkSelfPermission会导致程序崩溃
         */
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        } catch (RuntimeException e) {
            Toast.makeText(this, "please open this permission", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(checkSelfPermission!=
                PackageManager.PERMISSION_GRANTED){
//            if(){
//                showMessageOKCancel("xll授予权限", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                200);
//                    }
//                });
//            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                        200);
         //   }

        }else{
            Toast.makeText(this,"this已授权",Toast.LENGTH_LONG).show();
            takePhoto();
        }
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
          startActivityForResult(intentFromCapture,
                  200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==200){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"xll已授权",Toast.LENGTH_LONG).show();
                takePhoto();
            }else{
                Toast.makeText(this,"xll未授权",Toast.LENGTH_LONG).show();

            }
        }
    }



}
