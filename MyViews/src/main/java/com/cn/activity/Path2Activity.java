package com.cn.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cn.R;
import com.cn.view.MySurfaceView;

public class Path2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}
