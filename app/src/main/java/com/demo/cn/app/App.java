package com.demo.cn.app;

import android.app.Application;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/10.
 */
public class App extends Application {
    public static Application application = null;
    @Override
    public void onCreate() {
        super.onCreate();
        application= (Application) getApplicationContext();
    }
}
