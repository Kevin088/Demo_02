package com.demo.cn.retrofit_rxjava;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface MyApiEndpointInterface {//videoLink
    @POST("AppFiftyToneGraph/{a}")
    Call<RetrofitEntity> getAllVedio(@Path("a") String a);
}
