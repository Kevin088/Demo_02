package com.cn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ScrollView;
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

    ScrollView dd;
    Context context;
    int[] yPoints={100,200,300,400,500,600};
    int xMargin=100;
    Paint paint=new Paint();
    Point yuandian=new Point(100,700);
    int []array=new int[30];
    Scroller mScroller;
    private VelocityTracker velocityTracker;
    int value;
    int pointerX;
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
        pointerX= (Utils.getScreenWidth(context)-yuandian.x)/2+yuandian.x+10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);


        paint.setStrokeWidth(4);
        paint.setColor(ContextCompat.getColor(context, R.color.grey));
        //y轴
        canvas.drawLine(yuandian.x,0,yuandian.x,yuandian.y,paint);
        //x轴
        canvas.drawLine(yuandian.x,yuandian.y,yuandian.x+xMargin*30,yuandian.y,paint);

        for(int i=1;i<=yPoints.length;i++){
            canvas.drawLine(yuandian.x,yuandian.y-i*100,yuandian.x+xMargin*30,yuandian.y-i*100,paint);
            paint.setStrokeWidth(2);
            paint.setTextSize(22);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(yPoints[i-1]+"",yuandian.x/2,yuandian.y-i*100,paint);
        }
        for(int i=0;i<30;i++){
            paint.setColor(ContextCompat.getColor(context, R.color.grey));
            canvas.drawText(i+1+"号",yuandian.x+i*xMargin,yuandian.y+25,paint);
            if(i>0){
                paint.setStrokeWidth(4);
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
        paint.setStrokeWidth(2);
        canvas.drawText(value+"",pointerX+mScroller.getFinalX(),50,paint);
    }
    float mScrollLastx;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mScroller!=null&&!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mScrollLastx=x;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dataX=mScrollLastx-x;
                if(dataX<0&&mScroller.getFinalX()<0&&mScroller.getFinalX()<=(-pointerX+yuandian.x))
                    return true;
                if(dataX>0&&mScroller.getFinalX()>0&&mScroller.getFinalX()>=(yuandian.x+xMargin*29-pointerX))
                    return  true;

                smoothScrollBy(dataX,0);
                mScrollLastx=x;
                postInvalidate();


                Log.e("ssss",mScroller.getFinalX()+"============");
                return true;
            case MotionEvent.ACTION_UP:
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

                velocityTracker.computeCurrentVelocity(1000);
                mScroller.fling((int) mScroller.getCurrX(), 0, (int) velocityTracker.getXVelocity(), 0, 0, 99999999, 0, 0);
                velocityTracker.recycle();
                velocityTracker = null;
                postInvalidate();
                return true;
        }
        return super.onTouchEvent(event);
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
}
