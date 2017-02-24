package test.com.startservice;


import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends Activity {

    ImageView imageView;
    String imagePath=SDCardHelper.getSDCardPublicDir(Environment.DIRECTORY_PICTURES)
            + File.separator+"my.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView) findViewById(R.id.imageView1);

    }


    public void onClick(View v){
        if(new File(imagePath).exists()){
            imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));


        }else{
            startService(new Intent().setAction("test.com.startservice.DownloadService"));
        }




    }
}
