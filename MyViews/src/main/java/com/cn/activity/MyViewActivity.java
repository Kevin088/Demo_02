package com.cn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cn.R;
import com.cn.view.MyView;

public class MyViewActivity extends Activity {
    MyView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        view= (MyView) findViewById(R.id.my_view_scrll_line);
        //view.getLayoutParams().width= (int) view.getViewWidth();
    }

}
