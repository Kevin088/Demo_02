package com.demo.cn.retrofit_rxjava;

import retrofit.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface RetrofitAndRxjavaInterface {
    @POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEntity> getAllVedioBy(@Body boolean once_no);
}
