package com.demo.cn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.demo.cn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/9/1.
 */
public class SelectSeatView extends View{
    private Bitmap seatBitmap;
    private Bitmap checkedSeatBitmapMy;
    private Bitmap checkedSeatBitmapOther;
    private int row=15;
    private int column=20;
    private int horizontalSpace=15;
    private int verticalSpace=15;
    private int magin=20;
    private Context context;
    private Paint paint;
    private int top,left,bottom,right;
    /**
     * 处理点击
     */
    private List<Point>checkedPoints;
    private Point point=new Point();
    private Rect rect=new Rect();

    /**
     * 处理缩放
     */
    private ScaleGestureDetector mScaleGesturedetector;
    private Matrix matrix=new Matrix();
    private float[] m=new float[9];
    private Matrix tempMatrix=new Matrix();

    boolean isScaling;
    boolean isOnclick;

    public SelectSeatView(Context context) {
        this(context, null);
    }

    public SelectSeatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectSeatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
        this.context=context;
        seatBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_uncheck);
        checkedSeatBitmapMy= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_check_my);
        checkedSeatBitmapOther= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_checked_other);

        paint=new Paint();
        top=0;
        left=10;
        bottom=row*seatBitmap.getHeight()+(row-1)*horizontalSpace+top;
        right=60;

        checkedPoints=new ArrayList<>();
        mScaleGesturedetector=new ScaleGestureDetector(context,new ScaleGestureListener());


        matrix.postTranslate(70,0);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 绘制座位
         */
        float translateX=getTranslateX();
        float translateY=getTranslateY();
        Log.e("ssssss===translateY",translateY+"===================");
        float zoom=getMatrixScaleX();
        float scaleX=zoom;
        float scaleY=zoom;


        float _top=0;
        super.onDraw(canvas);
        for(int i=0;i<row;i++){
            float _left=0;
            _top=i*seatBitmap.getHeight()*zoom+i*horizontalSpace*zoom+translateY;
            for(int j=0;j<column;j++){
                _left=j*seatBitmap.getWidth()*zoom+j*verticalSpace*zoom+translateX;
                rect.left=(int)_left;
                rect.top=(int)_top;
                rect.right=rect.left+seatBitmap.getWidth()*(int)zoom;
                rect.bottom= rect.top+seatBitmap.getHeight()*(int)zoom;

                if(rect.contains(point.x,point.y)){
                    Point point1=new Point(i,j);
                    if(checkedPoints.indexOf(point1)<0){
                        checkedPoints.add(point1);
                    }else{
                        checkedPoints.remove(point1);
                    }
                }
                tempMatrix.setTranslate(_left,_top);
                //tempMatrix.postScale(1, 1, _left, _top);
                tempMatrix.postScale(scaleX, scaleY, _left, _top);
                if(checkedPoints.indexOf(new Point(i,j))>0){
                    canvas.drawBitmap(checkedSeatBitmapMy,tempMatrix,paint);
                    paint.setColor(ContextCompat.getColor(context,android.R.color.white));
                    paint.setTextAlign(Paint.Align.CENTER);
                    paint.setTextSize(20*zoom);
                    canvas.drawText(i+1+"排",(_left+seatBitmap.getWidth()*zoom/2),(_top+seatBitmap.getHeight()*zoom/4+8),paint);
                    canvas.drawText(j+1+"号",(_left+seatBitmap.getWidth()*zoom/2),(_top+seatBitmap.getHeight()*3*zoom/4+8),paint);
                }else{
                    canvas.drawBitmap(seatBitmap,tempMatrix,paint);
                }

            }

        }

        //绘制排号背景
        paint.setColor(ContextCompat.getColor(context,R.color.grey));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        RectF rectF=new RectF();
        rectF.top=top*zoom+translateY;
        rectF.bottom=bottom*zoom+translateY;
        rectF.left=left;
        rectF.right=right;

        canvas.drawRoundRect(rectF,(rectF.right-rectF.left)/2,(rectF.right-rectF.left)/2,paint);
        //绘制排号数字
        paint.setColor(ContextCompat.getColor(context,R.color.write));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(28*zoom);
        for(int k=0;k<row;k++){
            canvas.drawText(k+1+"",right-(rectF.right-rectF.left)/2,(10+top+k*(seatBitmap.getHeight()+horizontalSpace)+seatBitmap.getHeight()/2)*zoom+translateY,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        mScaleGesturedetector.onTouchEvent(event);
        float startX=0;
        float startY=0;
        float endX=0;
        float endY=0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                startY=event.getY();
                if(isOnclick){
                    point.x=(int)event.getX();
                    point.y=(int)event.getY();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isOnclick&&!isScaling){
                    endX=event.getX();
                    endY=event.getY();
                    float transX=endX-startX;
                    float transY=endY-startY;
                    Log.e("ddddddd",transX+"====="+transY);
                    if(Math.abs(transX)>1000||Math.abs(transY)>1000){
                        matrix.postTranslate(10,10);
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        isOnclick=false;
        startX=endX;
        startY=endY;
        return true;
    }


    public class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener
    {

        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            float scaleFactor=detector.getScaleFactor();
            if(getMatrixScaleX()*scaleFactor>2){
                scaleFactor=2/getMatrixScaleX();
            }
            if(getMatrixScaleX()*scaleFactor<0.5f){
                scaleFactor=0.5f/getMatrixScaleX();
            }
            matrix.postScale(scaleFactor,scaleFactor,detector.getCurrentSpanX(),detector.getCurrentSpanY());
            invalidate();
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector)
        {
            // TODO Auto-generated method stub
            //一定要返回true才会进入onScale()这个函数
            isScaling=true;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector)
        {
            isScaling=false;
            // TODO Auto-generated method stub
        }
    }

    private GestureDetector gestureDetector =new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            isOnclick=true;

            return super.onSingleTapConfirmed(e);
        }
    });
    private float getMatrixScaleX(){
        matrix.getValues(m);
        return  m[Matrix.MSCALE_X];
    }
    private float getMatrixScaleY() {
        matrix.getValues(m);
        return m[Matrix.MSCALE_Y];
    }
    private float getTranslateX() {
        matrix.getValues(m);
        return m[Matrix.MTRANS_X];
    }

    private float getTranslateY() {
        matrix.getValues(m);
        return m[Matrix.MTRANS_Y];
    }


}
