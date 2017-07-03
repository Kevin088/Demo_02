package co.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**                             startCommand
 * 1.service的生命周期  onCreate onBind--------  unBind onDestroy
 * 2.绑定服务：多次绑定只启动第一次，多次解绑会异常；绑定服务和当前Activity共存亡;
 * 一个服务可以被多个activity绑定 只是第一个绑定的会走 onCreate onBind, 最后一个activity销毁后 才会走onUnbind onDestroy;
 * 一个activity 启动绑定服务 ，销魂时 要解绑服务；
 * 3.启动服务：多次启动之后 ，只调用onStartCommand方法；
 * 可以任意activity启动，任意activity停止；
 * 4.混合启动：解除绑定并且停止服务 才会调用onDestroy
 */
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
    public void serviceMethod(){
        Log.e("ssss","serviceMethod=============");
    }
}
