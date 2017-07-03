package test.com.startservice;



import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class DownloadService extends IntentService {
	String str="http://www.baidu.com/img/bdlogo.gif";
	public DownloadService() {
		super("");
		// TODO Auto-generated constructor stub
	}
	
	
	public DownloadService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
//子线程执行的程序========================================================
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		byte[]data=HttpURLConnHelper.loadByteFromURL(str);
		boolean flag=SDCardHelper.saveFileToSDCardPublicDir(data, Environment.DIRECTORY_PICTURES, "my.jpg");
		if(flag){
			//服务没有界面经常和通知 一起使用；
			System.out.println("ddddddd");
			//通知需要一个通知经理把这个通知 给 通知出来；
			NotificationManager ma=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Notification.Builder b=new Notification.Builder(this);

			//必须有icon   才有弹出效果
			b.setSmallIcon(R.mipmap.ic_launcher);
			b.setContentTitle("提示：");
			b.setContentText("文件下载完毕！");
			b.setTicker("文件下载完毕！");
			b.setAutoCancel(true);
			Intent in=new Intent(this, MainActivity.class);
			in.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
			PendingIntent pIn=PendingIntent.getActivity(this, 1, in, PendingIntent.FLAG_ONE_SHOT);
			
			b.setContentIntent(pIn);
			
			ma.notify(0, b.build());
			
			
			
		}
	}

}
