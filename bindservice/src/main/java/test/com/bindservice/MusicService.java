package test.com.bindservice;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

public class MusicService extends Service {
	MediaPlayer player=null;
	@Override
	public IBinder onBind(Intent intent) {
		//
		start();
		Log.e("ssss","onBind=========================");
		return new MyBinder();
	}
	public class MyBinder extends Binder{
		public MusicService getService(){
			return MusicService.this;
		}
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-onUnbind method stub
		Log.e("ssss","onUnbind=========================");
		if(player!=null){
			player.reset();
			player.release();
		}


		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		Log.e("ssss","onCreate=========================");

		// TODO Auto-generated method stub
		super.onCreate();
		//创建 MediaPlayer----------------------------------------
		if(player==null){
			player=MediaPlayer.create(this, R.raw.dj);
			player.setLooping(false);
			
			//释放资源
			player.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.release();
				}
			});
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("ssss","onDestroy=========================");

		super.onDestroy();
	}
	public void stop(){
		if(player!=null)
		player.stop();
	}
	public void start(){

		if(player!=null&&!player.isPlaying())
		{
		try {
			player.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.start();
		}
	}
	public void pause(){
		if(player!=null&&player.isPlaying())
		player.pause();
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Log.e("ssss","onRebind=========================");

	}
}
