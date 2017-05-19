package com.dfhe.hewk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.qq).setOnClickListener(this);
        findViewById(R.id.wx).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.qq:
                loginByWeixinQq(QQ.NAME);
            break;
            case R.id.wx:
                loginByWeixinQq(Wechat.NAME);
                break;
            default:
            break;
        }
    }

    public void loginByWeixinQq(String platName){

        ShareSDK.initSDK(this);
        Platform plat=ShareSDK.getPlatform(this,platName);
        if (plat.isValid ()) {
            plat.removeAccount();
        }
        plat.SSOSetting(false);
        plat.setPlatformActionListener(this);
        plat.authorize();//授权
    }

    @Override
    public void onComplete(final Platform platform, int i, HashMap<String, Object> hashMap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String userId = platform.getDb().getUserId(); // 获取用户在此平台的ID
                String nickname = platform.getDb().get("nickname");
                String avater = platform.getDb().getUserIcon();
                String type = QQ.NAME.equals(platform.getName()) ? "2" : "1";
                Log.e("sssss",userId+":"+nickname+":"+avater);
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.e("sssssonError",throwable.getMessage());
    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
