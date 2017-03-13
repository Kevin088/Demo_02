package com.demo.cn;

import android.util.Log;

import java.io.File;

/**
 * Created by xll on 2016/11/13.
 */

public class Test {
    public static void main(String []args){
//        File file=new File("/sdcard/xst/pic/001.png");
//        file.getName();
////        Log.e("sss",file.getName()+"ss");
//        System.out.print(file.getName());
        String xian="景县";
        int index=xian.indexOf("县");
        System.out.print(xian.substring(0,index+1));

//测试

    }
}
