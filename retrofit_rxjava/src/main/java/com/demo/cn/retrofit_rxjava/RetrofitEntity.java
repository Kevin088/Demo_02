package com.demo.cn.retrofit_rxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class RetrofitEntity {
    public String ret;
    public String msg;
    public List<A> data;
    public static class A{
        public String name;
        public String title;
        public int id;
        public String url;
    }

    @Override
    public String toString() {
        return "RetrofitEntity{" +
                "ret='" + ret + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
