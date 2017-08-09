package com.cn.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/27.
 */
public class Utils {
    /**
     * 获取屏幕宽度(像素)
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    /**
     * 将dip转换为px
     *
     * @return
     */
    public static int dip2Px(Context context, float dip) {
        return (int) (context.getResources().getDisplayMetrics().density * dip);
    }

    /**
     * 将px转换为dip
     *
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }
}
