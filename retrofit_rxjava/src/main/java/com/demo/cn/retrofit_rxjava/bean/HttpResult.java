package com.demo.cn.retrofit_rxjava.bean;

/**
 * Created by xll on 2016/10/31.
 */

public class HttpResult<T> {
    public int TotalCount;
    public int TotalPage;
    public T Data;
    public String Result;
    public String ErrorMsg;
}
