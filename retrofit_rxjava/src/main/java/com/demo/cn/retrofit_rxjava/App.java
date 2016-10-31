package com.demo.cn.retrofit_rxjava;

import android.app.Application;

import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by xll on 2016/10/21.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("Huaeryun")
        .logLevel(LogLevel.FULL);



    }
}
