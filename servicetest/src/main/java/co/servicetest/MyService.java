package co.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
        Log.e("ssss","MyService=============");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ssss","onCreate=============");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ssss","onStartCommand=============");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("ssss","onBind=============");
        return new MyBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("ssss","onRebind=============");
        super.onRebind(intent);
    }

    public class MyBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("ssss","onUnbind=============");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("ssss","onDestroy=============");
        super.onDestroy();
    }
}
