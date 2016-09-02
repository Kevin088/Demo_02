package com.demo.cn.okhttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * JSON POST 网络请求
 */
public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    private String url="http://unity.huaeryun.com/api/Account/UserLoginByGT";
    private String params="{\"TenancyName\":\"jdjz\",\"zuhuId\":\"kMwwPkPREWA%\",\"clientId\":\"cb73bcab49a1bfa712d3ece76b01c3ec\",\"sourceId\":\"2\",\"passWord\":\"ZZ3ukVclQE2PM*y*GaBSHg%%\",\"userName\":\"muPKMPBETEBkud_l_EZFLg%%\"}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //post(url,params);
        uploadImag();
    }
    //post请求 json参数传递
   public  void post(String url, String json)  {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(this)
                .build();
        Call call=client.newCall(request);
       //enqueue()开启子线程执行网络请求 ，回调函数在子线程中执行
        call.enqueue(new Callback(){
          @Override
          public void onFailure(Call call, IOException e) {

          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
              Log.e("dddd",response.body().string());
          }
      });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.dispatcher().cancelAll();
    }
    //form表单 文件上传
    public void uploadImag(){
        final String url = "http://image.huaeryun.com/api/image/uploadimage";
        File file1 = new File("/storage/emulated/0/DCIM/Camera", "IMG_20160707_102203.jpg");
        File file2 = new File("/storage/emulated/0/DCIM/Camera", "IMG_20160707_102834.jpg");
        File file3 = new File("/storage/emulated/0/DCIM/Camera", "IMG_20160728_101422.jpg");
        File file4 = new File("/storage/emulated/0/DCIM/Camera", "IMG_20160728_101425.jpg");

        MediaType MEDIA_TYPE_PNG=MediaType.parse("image/png");
        MultipartBody.Builder build=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image","xll.jpg",RequestBody.create(MEDIA_TYPE_PNG,file1))
                .addFormDataPart("image","xll.jpg",RequestBody.create(MEDIA_TYPE_PNG,file2))
                .addFormDataPart("image","xll.jpg",RequestBody.create(MEDIA_TYPE_PNG,file3))
                .addFormDataPart("image","xll.jpg",RequestBody.create(MEDIA_TYPE_PNG,file4))
                .addFormDataPart("imagetype", "1")
                .addFormDataPart("tenantid","13")
                ;


        Request request = new Request.Builder()
                .url(url)
                .post(build.build())
                .build();
        Call call=client.newCall(request);
        //enqueue()开启子线程执行网络请求 ，回调函数在子线程中执行
        call.enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("dddd",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("dddd",response.body().string());
            }
        });


    }
}
