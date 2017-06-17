package co.pushdemo;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import java.io.UnsupportedEncodingException;

public class PushIntentService  extends GTIntentService {
    public PushIntentService() {

    }
    @Override
    public void onReceiveServicePid(Context context, int i) {

    }

    /**
     * 接收 cid
     * @param context
     * @param clientid
     */
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        /*****************获取客户端ID  并保存到缓存中*********/
        if(clientid!=null&&clientid.length()>0){
            Log.e(TAG, "clientID is :"+clientid);
        }else{
            Log.e(TAG, "clientID is null");
        }
    }

    /**
     * 处理透传消息
     * @param context
     * @param msg
     */
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String data = "";
        String Type = "";
        String RelationId = "";
        String RelationName = "";
        String NoticeId = "";
        // 获取透传（payload）数据
        byte[] payload = msg.getPayload();
        if (payload != null){
            try {
                data = new String(payload,"utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Log.e(TAG, "payload:"+data);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }
}
