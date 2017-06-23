package co.pushdemo;

import android.app.Activity;
import android.os.Bundle;

import com.igexin.sdk.PushManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushManager.getInstance().initialize(this.getApplicationContext(), DfhePushService.class);//个推
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DfhePushIntentService.class);
    }
}
