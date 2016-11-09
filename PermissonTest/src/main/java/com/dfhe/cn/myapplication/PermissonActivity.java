package com.dfhe.cn.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class PermissonActivity extends Activity implements PermissionUtils.PermissionGrant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_permisson);
    }

    public void onCamera(View view){
        //PermissionUtils.requestPermission(this,PermissionUtils.CODE_CAMERA,this);
        takePhoto();
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        switch (requestCode){
            case PermissionUtils.CODE_CAMERA:
                takePhoto();
                break;
        }
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
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, this);
    }
}
