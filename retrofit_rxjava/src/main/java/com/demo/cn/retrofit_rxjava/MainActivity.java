package com.demo.cn.retrofit_rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String BASE_URL = " http://www.izaodao.com/Api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    findViewById(R.id.tv).setOnClickListener(this);
    }
    public void sendServce(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApiEndpointInterface apiService=retrofit.create(MyApiEndpointInterface.class);
        Call<RetrofitEntity> call=apiService.getAllVedio("videoLink");
        call.enqueue(new Callback<RetrofitEntity>() {
            @Override
            public void onResponse(Call<RetrofitEntity> call, Response<RetrofitEntity> response) {
                RetrofitEntity entity=response.body();
                Log.e("aaaa",entity.toString());
            }

            @Override
            public void onFailure(Call<RetrofitEntity> call, Throwable t) {
                Log.e("tag", "onFailure----->" + t.toString());
            }
        });
    }
    public void sendServceRxjavaAndRetrofit(){


    }
    @Override
    public void onClick(View view) {
        sendServce();
    }
}
