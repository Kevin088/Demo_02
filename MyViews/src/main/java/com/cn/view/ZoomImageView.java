package com.cn.view;


import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * 类描述:
 *图片放大缩小 ScaleGestureDetector
 * @author xull
 * @date 2017/9/6.
 */
public class ZoomImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final float SCALE_MAX = 4.0f;
    /**
     * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
     */
    private float initScale = 1.0f;
    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];

    private boolean once = true;

    private ScaleGestureDetector mScaleGestureDetector = null;

    private final Matrix mScaleMatrix = new Matrix();

    public ZoomImageView(Context context) {
        this(context,null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        this.setOnTouchListener(this);
    }

    /**
     * 返回值 缩放事件是否被处理
     * @param detector
     * @return
     */
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        /**
         * 相对原图放大的倍数
         */
        float scale=getScale();
        //一次拖拽中 放大或缩小的倍数  ，相对于上一次来说的
        float scaleFactor=detector.getScaleFactor();
        Log.e("scale==========",detector.getScaleFactor()+"===========");
        if(getDrawable()==null)
            return true;
        if((scale<SCALE_MAX&&scaleFactor>1.0f)||(scale>initScale&&scaleFactor<1.0f)){
            if(scaleFactor*scale<initScale){
                scaleFactor=initScale/scale;
            }
            if(scaleFactor*scale>SCALE_MAX){
                scaleFactor=SCALE_MAX/scale;
            }
            mScaleMatrix.postScale(scaleFactor,scaleFactor,getWidth()/2,getHeight()/2);
            setImageMatrix(mScaleMatrix);
        }


        return true;//返回true表示 一次缩放事件的完成；
    }

    /**
     * 缩放开始。该detector是否处理后继的缩放事件。返回false时，不会执行onScale()
     * 一定要返回true才会进入onScale()这个函数
     * @param detector
     * @return
     */
    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    /**
     *  缩放结束时。
     * @param detector
     */
    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mScaleGestureDetector.onTouchEvent(event);
    }

    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    public final float getScale()
    {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if(once){
            Drawable d=getDrawable();
            if(d==null) return;
            int width=getWidth();
            int height=getHeight();
            int dw=d.getIntrinsicWidth();
            int dh=d.getIntrinsicHeight();
            float scale=1.0f;
            if(dw>width&&dh<=height){
                scale=width*1.0f/dw;
            }
            if (dh > height && dw <= width)
            {
                scale = height * 1.0f / dh;
            }
            if (dw > width && dh > height)
            {
                scale = Math.min(dw * 1.0f / width, dh * 1.0f / height);
            }

            initScale=scale;//初始化倍数
            mScaleMatrix.postTranslate((width-dw)/2,(height-dh)/2);
            mScaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mScaleMatrix);
            once=false;

        }
    }
}
