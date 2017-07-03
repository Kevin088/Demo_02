package co.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    MyService service=null;
    ServiceConnection connection=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ServiceTestActivity.class));
            }
        });
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                service=((MyService.MyBinder)binder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                service=null;
                Toast.makeText(MainActivity.this,"Disconnected",Toast.LENGTH_SHORT).show();
            }
        };
        findViewById(R.id.btn02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.bindService(new Intent( MainActivity.this, MyService.class), connection, BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btn03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.unbindService(connection);
            }
        });
        findViewById(R.id.btn04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startService(new Intent(MainActivity.this,MyService.class));
            }
        });
        findViewById(R.id.btn05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.stopService(new Intent(MainActivity.this,MyService.class));
            }
        });
        findViewById(R.id.btn06).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.serviceMethod();
            }
        });
    }

}
