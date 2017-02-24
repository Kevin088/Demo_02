package test.com.bindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    MusicService service=null;
    ServiceConnection connection=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        connection=new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                service=null;
                Log.e("ssss","onServiceDisconnected=========================");

            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                service=((MusicService.MyBinder)binder).getService();
                Log.e("ssss","onServiceConnected=========================");
            }
        };
    }
    AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {

    }
};
public void onClick(View v){
        switch(v.getId()){
            case R.id.bofang:
                if(service==null){

                    AudioManager mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
                    boolean mAudioFouus =  mAudioManager.requestAudioFocus(mAudioFocusListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
                    if(mAudioFouus){
                        //录制
                    }

                    //点击播放按钮2次才能播放，  为了优化，在绑定代码中   加入  start()方法；
                    bindService(new Intent(this, MusicService.class), connection, BIND_AUTO_CREATE);

                }else{

                    service.start();
                }
                break;
            case R.id.zanting:
                if(service!=null)
                    service.pause();
                break;
            case R.id.tingzhi:
                if(service!=null){
                    service.stop();
                    unbindService(connection);}
                break;

        }
    }
}
