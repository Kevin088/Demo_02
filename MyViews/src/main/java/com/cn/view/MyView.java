package com.cn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.cn.R;

import java.util.Random;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/8/8.
 */
public class MyView extends View {
    Context context;
    int[] yPoints={100,200,300,400,500,600};
    int xMargin=100;
    Paint paint=new Paint();
    Point yuandian=new Point(100,700);
    int []array=new int[30];
    Scroller mScroller;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        Random random=new Random();
        for(int i=0;i<30;i++){
            array[i]=random.nextInt(601);
            Log.e("ssss",array[i]+"");
        }
        mScroller=new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.grey));
        //y轴
        canvas.drawLine(yuandian.x,0,yuandian.x,yuandian.y,paint);
        //x轴
        canvas.drawLine(yuandian.x,yuandian.y,yuandian.x+xMargin*30,yuandian.y,paint);

        for(int i=1;i<=yPoints.length;i++){
            canvas.drawLine(yuandian.x,yuandian.y-i*100,yuandian.x+xMargin*30,yuandian.y-i*100,paint);
            paint.setTextSize(22);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(yPoints[i-1]+"",yuandian.x/2,yuandian.y-i*100,paint);
        }
        for(int i=0;i<30;i++){
            paint.setColor(ContextCompat.getColor(context, R.color.grey));
            canvas.drawText(i+1+"号",yuandian.x+i*xMargin,yuandian.y+25,paint);
            if(i>0){
                paint.setColor(ContextCompat.getColor(context,R.color.blue));
                canvas.drawLine(yuandian.x+(i-1)*xMargin,yuandian.y-array[i-1],yuandian.x+i*xMargin,yuandian.y-array[i],paint);

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


    }
    float mScrollLastx;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mScroller!=null&&!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mScrollLastx=x;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dataX=mScrollLastx-x;
                //smoothScrollBy(dataX,0);
                scrollBy((int)dataX, 0);
                mScrollLastx=x;
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                //mScroller.setFinalX((int)x); //纠正指针位置
                postInvalidate();
                return true;
        }


        return super.onTouchEvent(event);
    }

//    public void smoothScrollBy(float dx, float dy){
//        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), (int)dx, (int)dy);
//    }
//    @Override
//    public void computeScroll() {
//        if(mScroller.computeScrollOffset()){
//
//            postInvalidate();
//        }
//        super.computeScroll();
//    }
}
