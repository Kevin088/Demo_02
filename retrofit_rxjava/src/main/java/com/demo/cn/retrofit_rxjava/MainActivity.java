package com.demo.cn.retrofit_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnSuccessAndFailSub.OnHttpResquestCallBack {
    String BASE_URL = " http://www.izaodao.com/Api/";
    private Subscription subscription;
    private Subscriber<RetrofitEntity> subscriber;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private String params="{\"TenancyName\":\"public\",\"zuhuId\":\"QxpERaZrriI%\",\"clientId\":\"dca0ca8f01835e2067cb79514a2495da\",\"sourceId\":\"2\",\"passWord\":\"ZZ3ukVclQE2PM*y*GaBSHg%%\",\"userName\":\"SLMfMvTJOH1kud_l_EZFLg%%\"}";
    private String url="http://unitytest.huaeryun.com/api/Account/UserLoginByGT";
    final String Imageurl = "http://imagetest.huaeryun.com/api/image/uploadimage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(this);
        findViewById(R.id.bt_cancel).setOnClickListener(this);
        findViewById(R.id.bt_imageupload).setOnClickListener(this);
        findViewById(R.id.bt_imageupload_part).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        //sendServceByRetrofit();
        //sendServceRxjavaAndRetrofit();
        switch (view.getId()){
            case R.id.tv:
                //sendServceByPackage();
                //sendServceJson();
                sendServceFengzhuang();
                break;
            case R.id.bt_cancel:

                break;
            case R.id.bt_imageupload:
                sendServceUploadImages();
                break;
            case R.id.bt_imageupload_part:
                //sendServceUploadImagesWithMapPart();

                Logger.d("hello");
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        subscription.unsubscribe();
    }


    /**
     * 文件上传
     */
    public void sendServceUploadImages(){


        File file1 = new File("/storage/emulated/0/DCIM/Camera", "01.jpg");
        File file2 = new File("/storage/emulated/0/DCIM/Camera", "02.jpg");
//        File file5 = new File("/storage/emulated/0/DCIM/Camera", "03.jpg");
//        File file6 = new File("/storage/emulated/0/DCIM/Camera", "04.jpg");
//        File file7 = new File("/storage/emulated/0/DCIM/Camera", "05.jpg");
//        File file8 = new File("/storage/emulated/0/DCIM/Camera", "06.jpg");
//        File file9 = new File("/storage/emulated/0/DCIM/Camera", "07.jpg");
//        File file3 = new File("/storage/emulated/0/DCIM/Camera", "08.jpg");
//        File file4 = new File("/storage/emulated/0/DCIM/Camera", "09.jpg");
        List<File> files=new ArrayList<File>();
        files.add(file1);
        files.add(file2);
//        files.add(file3);
//        files.add(file4);
//        files.add(file5);
//        files.add(file6);
//        files.add(file7);
//        files.add(file8);
//        files.add(file9);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("imagetype", "1");
        builder.addFormDataPart("tenantid","4");
        String[] sdf=new String[]{"sds","sdsd"};
       // builder.addFormDataPart("sdsd",sdf);
        for (File file : files) {
            builder.addFormDataPart(file.getName(), file.getName(), MultipartBody.create(MediaType.parse("image/png"), file));
        }
        //builder.addFormDataPart()
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        HttpTaskUtils.getInstence().UpLoadFiles(new OnSuccessAndFailSub(2,this,this),multipartBody);
    }

    /**
     * post json格式请求
     */
    private final int REQUEST_CODE_LOGIN=1;
    public void sendServceFengzhuang(){
        Map<String,String> map= new HashMap<String, String>();
        map.put("TenancyName","public");
        map.put("zuhuId","QxpERaZrriI%");
        map.put("clientId","dca0ca8f01835e2067cb79514a2495da");
        map.put("sourceId","2");
        map.put("passWord","ZZ3ukVclQE2PM*y*GaBSHg%%");
        map.put("userName","SLMfMvTJOH1kud_l_EZFLg%%");

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("TenancyName","public");
            jsonObject.put("zuhuId","QxpERaZrriI%");
            jsonObject.put("clientId","dca0ca8f01835e2067cb79514a2495da");
            jsonObject.put("sourceId","2");
            jsonObject.put("passWord","ZZ3ukVclQE2PM*y*GaBSHg%%");
            jsonObject.put("userName","SLMfMvTJOH1kud_l_EZFLg%%");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON,jsonObject.toString());
        HttpTaskUtils.getInstence().UserLoginByGT(new OnSuccessAndFailSub(REQUEST_CODE_LOGIN,this,this),body);
    }

    @Override
    public void OnSuccessResult(int requestCode, String data) {
        switch (requestCode){
            case REQUEST_CODE_LOGIN:
                Logger.json(data);
                break;
            case 2:
                Logger.json(data);
                break;
        }
    }

    @Override
    public void OnFailResult(int requestCode, String errorMsg) {
        switch (requestCode){
            case REQUEST_CODE_LOGIN:
                Logger.e(errorMsg);
                break;
            case 2:
                Logger.e(errorMsg);
                break;
        }
    }
}
