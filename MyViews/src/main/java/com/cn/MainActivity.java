package com.cn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cn.activity.MyViewActivity;
import com.cn.activity.Path2Activity;
import com.cn.activity.PathActivity;
import com.cn.activity.ZoomImageActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.path)
    Button path;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
    @OnClick(R.id.path)
    void pathClick(){
        startActivity(new Intent(this,PathActivity.class));
    }
    @OnClick(R.id.path2)
    void pathClick2(){
        startActivity(new Intent(this,Path2Activity.class));
    }
    @OnClick(R.id.myview)
    void pathClick3(){
        startActivity(new Intent(this,MyViewActivity.class));
    }
    @OnClick(R.id.zoom)
    void pathClick4(){
        startActivity(new Intent(this,ZoomImageActivity.class));
    }
}
