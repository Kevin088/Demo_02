package com.demo.cn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTestActivity extends AppCompatActivity implements View.OnClickListener {
    Timer timer;
    TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn_01).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01:

                    timer=new Timer();
                    timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            Log.e("sss","sddddddddddddddddddddddd");
                            Log.e("ss","dddddddddddddddddddd");
                        }
                    };


                timer.schedule(timerTask,2000,2000);
                break;
            case R.id.btn_02:
                timer.cancel();
                timerTask.cancel();
                break;
        }
        }




}
