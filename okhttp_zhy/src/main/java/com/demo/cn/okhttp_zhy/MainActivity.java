package com.demo.cn.okhttp_zhy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private String url="http://unity.huaeryun.com/api/Account/UserLoginByGT";
    private String params="{\"TenancyName\":\"jdjz\",\"zuhuId\":\"kMwwPkPREWA%\",\"clientId\":\"cb73bcab49a1bfa712d3ece76b01c3ec\",\"sourceId\":\"2\",\"passWord\":\"ZZ3ukVclQE2PM*y*GaBSHg%%\",\"userName\":\"muPKMPBETEBkud_l_EZFLg%%\"}";
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(new LoginBean()))
                .build()
                .execute(new MyCallback());

    }
    public class MyCallback extends Callback<String>{
        //子线程  用于解析数据
        @Override
        public String parseNetworkResponse(Response response, int id) throws Exception {
            return response.body().string();
        }
        //主线程
        @Override
        public void onError(Call call, Exception e, int id) {

        }
        //主线程
        @Override
        public void onResponse(String response, int id) {
            tv.setText(response);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
