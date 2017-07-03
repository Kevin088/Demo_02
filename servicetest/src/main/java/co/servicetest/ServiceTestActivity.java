package co.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;


public class ServiceTestActivity extends Activity {
    MyService service=null;
    ServiceConnection connection=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                service=((MyService.MyBinder)binder).getService();
                Log.e("ssss","Connected=============");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                service=null;
            }
        };
        findViewById(R.id.btn01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceTestActivity.this.bindService(new Intent( ServiceTestActivity.this, MyService.class), connection, BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btn02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service!=null){
                    ServiceTestActivity.this.unbindService(connection);
                    service=null;
                }

            }
        });


        findViewById(R.id.btn03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceTestActivity.this.startService(new Intent(ServiceTestActivity.this,MyService.class));
            }
        });
        findViewById(R.id.btn04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceTestActivity.this.stopService(new Intent(ServiceTestActivity.this,MyService.class));
            }
        });
        findViewById(R.id.btn06).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service!=null)
                service.serviceMethod();
            }
        });
    }


}
