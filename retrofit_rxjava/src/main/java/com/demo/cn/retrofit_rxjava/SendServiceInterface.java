package com.demo.cn.retrofit_rxjava;


import com.demo.cn.retrofit_rxjava.bean.UserInfoData;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by xll on 2016/10/17.
 */

public interface SendServiceInterface {
    /**
     * 登录
     */
    @Headers("Content-Type: application/json")
    @POST("api/Account/UserLoginByGT")
    Observable<ResponseBody>UserLoginByGT(@Body RequestBody jsonParams);

    /**
     * 文件上传
     */
    @POST("http://imagetest.huaeryun.com/api/image/uploadimage")
    Observable<ResponseBody>uploadFiles(@Body MultipartBody body);

}
