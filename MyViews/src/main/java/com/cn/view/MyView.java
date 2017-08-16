package com.cn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;

import com.cn.R;
import com.cn.util.Utils;

import java.util.Random;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/8/8.
 */
public class MyView extends View {
    int width;
    int height;
    Context context;
    public static int[] yPoints=new int[6];
    int xMargin,yMargin;
    Paint paint=new Paint();
    public Point yuandian=new Point();
    int []array=new int[30];
    Scroller mScroller;
    int value;
    int pointerX;
    int padingBottom=100;

    private int screenWidth;
    private int screenHeight;
    private int lineChartEndY;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        mScroller=new Scroller(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        lineChartEndY = screenHeight *29/ 100;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        height=getHeight();
        yMargin=(height-padingBottom)/7;
        for(int i=0;i<6;i++){
            yPoints[i]=yMargin*(i+1);
        }
        xMargin=(height-padingBottom)/7;
        yuandian.x=0;
        yuandian.y=height-padingBottom;

        Random random=new Random();
        for(int i=0;i<30;i++){
            array[i]=random.nextInt(height*7/8);
        }

        width=getWidth();
        pointerX= ((width-yuandian.x)/2/xMargin+1)*xMargin+yuandian.x;


    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) getViewWidth()+yuandian.x;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (lineChartEndY + 300);
        }

        setMeasuredDimension(width, height);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.grey));
//        //y轴
//        canvas.drawLine(yuandian.x,0,yuandian.x,yuandian.y,paint);
        //x轴
        canvas.drawLine(yuandian.x,yuandian.y,yuandian.x+xMargin*30,yuandian.y,paint);

        for(int i=1;i<=yPoints.length;i++){
            canvas.drawLine(yuandian.x,yuandian.y-i*yMargin,yuandian.x+xMargin*30,yuandian.y-i*yMargin,paint);
//            paint.setTextSize(22);
//            paint.setTextAlign(Paint.Align.CENTER);
//            canvas.drawText(yPoints[i-1]+"",yuandian.x/2,yuandian.y-i*yMargin,paint);
        }
        paint.setTextSize(22);

        for(int i=0;i<30;i++){
            if(i==0){
                paint.setTextAlign(Paint.Align.LEFT);
            }else{
                paint.setTextAlign(Paint.Align.CENTER);
            }
            paint.setColor(ContextCompat.getColor(context, R.color.grey));
            canvas.drawText(i+1+"号",yuandian.x+i*xMargin,yuandian.y+25,paint);
            if(i>0){
                paint.setColor(ContextCompat.getColor(context,R.color.blue));
                canvas.drawLine(yuandian.x+(i-1)*xMargin,yuandian.y-array[i-1],yuandian.x+i*xMargin,yuandian.y-array[i],paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(yuandian.x+(i-1)*xMargin,yuandian.y-array[i-1],10,paint);
                paint.setColor(ContextCompat.getColor(context,R.color.write));
                canvas.drawCircle(yuandian.x+(i-1)*xMargin,yuandian.y-array[i-1],8,paint);
                if(i==29){
                    paint.setColor(ContextCompat.getColor(context,R.color.blue));
                    canvas.drawCircle(yuandian.x+(i)*xMargin,yuandian.y-array[i],10,paint);
                    paint.setColor(ContextCompat.getColor(context,R.color.write));
                    canvas.drawCircle(yuandian.x+(i)*xMargin,yuandian.y-array[i],8,paint);
                }
            }

        }
        paint.setColor(ContextCompat.getColor(context,R.color.blue));
        canvas.drawLine(pointerX+mScroller.getFinalX(),90,pointerX+mScroller.getFinalX(),yuandian.y,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(pointerX+mScroller.getFinalX(),50,40,paint);

        int scrollx=mScroller.getFinalX()+pointerX-yuandian.x;
        if(scrollx%xMargin==0){
            int index=scrollx/xMargin;
            if(index>=0&&index<array.length){
                value=array[index];
            }
        }
        canvas.drawText(value+"",pointerX+mScroller.getFinalX(),50,paint);
    }
    float mScrollLastx;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
//        float x=event.getX();
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                if(mScroller!=null&&!mScroller.isFinished()){
//                    mScroller.abortAnimation();
//                }
//                mScrollLastx=x;
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float dataX=mScrollLastx-x;
//                if(dataX<0&&mScroller.getFinalX()<0&&mScroller.getFinalX()<=(-pointerX+yuandian.x))
//                    return true;
//                if(dataX>0&&mScroller.getFinalX()>0&&mScroller.getFinalX()>=(yuandian.x+xMargin*29-pointerX))
//                    return  true;
//
//                smoothScrollBy(dataX,0);
//                mScrollLastx=x;
//                postInvalidate();
//
//
//                Log.e("ssss",mScroller.getFinalX()+"============");
//                return true;
//            case MotionEvent.ACTION_UP:
//                int finalX=mScroller.getFinalX();
//                int a=finalX%xMargin;
//                if(a>0){
//                    int b=xMargin/2-a;
//                    if(b>0){
//                        mScroller.setFinalX(finalX-a);
//                    }if(b<0){
//                        mScroller.setFinalX(finalX+xMargin-a);
//                    }
//                }else{
//                    int b=xMargin/2+a;
//                    if(b>0){
//                        mScroller.setFinalX(finalX-a);
//                    }if(b<0){
//                        mScroller.setFinalX(finalX-(xMargin+a));
//                    }
//                }
//
//                postInvalidate();
//                return true;
//        }

    }

    public void smoothScrollBy(float dx, float dy){
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), (int)dx, (int)dy);
    }
    @Override
    public void computeScroll() {
        /**
         * computeScrollOffset 调用一次进行一次 移动计算 ，并执行scrollTo来实现view的真正移动
         * Invalidate()执行后会调用onDrow(),onDrow会调用computeScroll()方法；
         */
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),0);

            postInvalidate();
        }
        super.computeScroll();
    }

    public float getViewWidth() {
        return  30*xMargin;
    }
}
