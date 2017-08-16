package com.cn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.cn.R;

import java.util.Random;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/8/16.
 */
public class YView extends View {
    public int xYuanPoint;
    public int yMargin;
    public int yYuanPoint;
   // int [] yPoints;
    Paint paint=new Paint();

    int padingBottom=100;
    public YView(Context context) {
        this(context,null);
    }

    public YView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public YView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.grey));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(MyView.yPoints!=null){
            //        //y轴
            canvas.drawLine(xYuanPoint,0,xYuanPoint,yYuanPoint,paint);
            paint.setTextSize(22);
            paint.setTextAlign(Paint.Align.CENTER);
            for(int i=1;i<=MyView.yPoints.length;i++){
                canvas.drawText(MyView.yPoints[i-1]+"",xYuanPoint/2,yYuanPoint-i*yMargin,paint);
            }

        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int height=getHeight();
        yMargin=(height-padingBottom)/7;

        xYuanPoint=100;
        yYuanPoint=height-padingBottom;

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
            width = (int) xYuanPoint;
        }


        height = heightSize;


        setMeasuredDimension(width, height);
    }
}
