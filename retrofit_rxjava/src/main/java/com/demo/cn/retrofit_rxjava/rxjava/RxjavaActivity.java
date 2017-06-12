package com.demo.cn.retrofit_rxjava.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo.cn.retrofit_rxjava.R;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxjavaActivity extends AppCompatActivity {
    String hello="Hello RxAndroid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
//        Subscriber<String> subscriber=new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.d("ssss", "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d("ssss", "onNext :"+s);
//            }
//        };
//
//        Observable observable= Observable.create(new Observable.OnSubscribe<String>(){
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext(hello);
//                subscriber.onCompleted();
//
//            }
//        });
//
//        observable.subscribe(subscriber);

        Observable.just(hello).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"  end...";
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                Log.d("dddddd", "filter :"+s);
                return s!=null;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("ssss", "Action1 :"+s);
            }
        });


    }


}
