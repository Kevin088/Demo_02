package com.demo.cn.retrofit_rxjava;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface RetrofitAndRxjavaInterface {
    @POST("AppFiftyToneGraph/{a}")
    Observable<RetrofitEntity> getAllVedio(@Path("a") String a);
}
