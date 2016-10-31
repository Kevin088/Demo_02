package com.demo.cn.retrofit_rxjava;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.demo.cn.retrofit_rxjava.bean.HttpResult;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by xll on 2016/10/31.
 */

public class OnSuccessAndFailSub extends Subscriber<ResponseBody> {
    /**
     * 请求码
     */
    private int requestCode;
    /**
     * 请求回调
     */
    private OnHttpResquestCallBack callBack;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 构造方法
     * @param requestCode
     * @param callBack
     */
    public OnSuccessAndFailSub(int requestCode,OnHttpResquestCallBack callBack,Context context){
        this.requestCode=requestCode;
        this.callBack=callBack;
        this.context=context;
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {//请求超时
            Toast.makeText(context, "请求超时",Toast.LENGTH_LONG).show();
        } else if (e instanceof ConnectException) {//网络连接超时
            Toast.makeText(context, "网络连接超时",Toast.LENGTH_LONG).show();
        } else if (e instanceof SSLHandshakeException) {//安全证书异常
            Toast.makeText(context, "安全证书异常",Toast.LENGTH_LONG).show();
        } else if (e instanceof HttpException) {//请求的地址不存在
            int code = ((HttpException) e).code();
            if (code == 504) {
                Toast.makeText(context, "网络请求失败",Toast.LENGTH_LONG).show();
            } else if (code == 404) {
                Toast.makeText(context, "网络请求失败",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "网络请求失败",Toast.LENGTH_LONG).show();
            }
        } else if (e instanceof UnknownHostException) {//域名解析失败
            Toast.makeText(context, "网络请求失败",Toast.LENGTH_LONG).show();
            //加网络连接否的判断

        } else {
            Toast.makeText(context, "网络堵车",Toast.LENGTH_LONG).show();
        }
        Log.e("dddd",e.toString());
    }

    @Override
    public void onNext(ResponseBody body) {
        try {
            final String result = body.string();
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("result");
            String errorMsg = jsonObject.getString("errorMsg");
            if (resultCode == 1) {
                callBack.OnSuccessResult(requestCode,result);
            } else {
                callBack.OnFailResult(requestCode,errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callBack.OnFailResult(requestCode,"解析异常");
        }
    }


    public interface OnHttpResquestCallBack{
        void OnSuccessResult(int requestCode,String data);
        void OnFailResult(int requestCode,String errorMsg);
    }
}
