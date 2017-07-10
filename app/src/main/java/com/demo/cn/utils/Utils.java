package com.demo.cn.utils;

import android.content.Context;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/10.
 */
public class Utils {

    /**
     * sp转px
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int sp2px(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue * scale + 0.5f);
    }
    /**
     * 将dip转换为px
     *
     * @return
     */
    public static int dip2Px(Context context, float dip) {
        return (int) (context.getResources().getDisplayMetrics().density * dip);
    }

}
