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
        findViewById(R.id.btn_01).setOnClickListener(this);
        findViewById(R.id.btn_02).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01:

                    timer=new Timer();
                    timerTask=new TimerTask() {
                        @Override
                        public void run() {
                            Log.e("ss","dddddddddddddddddddd");
                        }
                    };


                timer.schedule(timerTask,2000,3000);
                break;
            case R.id.btn_02:
                if(timer!=null&&timerTask!=null){
                    timer.cancel();
                    timerTask.cancel();
                }
                break;
        }
    }
}
