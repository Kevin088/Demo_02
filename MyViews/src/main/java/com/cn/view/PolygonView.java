package com.cn.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.cn.R;
import com.cn.util.Utils;


/**
 * 项目名称：gaocaisheng3.0
 * 类描述：自定义渐变图形
 * 创建人：lh
 * 创建时间：2017/2/15 15:41
 */
public class PolygonView extends View {

    private Context mContext;
    private int startColor;//渐变色左边初始颜色
    private int endColor;//渐变色右边最终颜色
    private int firstX;
    private int firstY;
    private int secondX;
    private int secondY;
    private int thirdX;
    private int thirdY;
    private int fourthX;
    private int fourthY;
    private int fifthX;
    private int fifthY;
    private int screenWidth;
    private int borderCount;//标记有几条边
    private Paint paint;
    private float middleX;

    public PolygonView(Context context) {
        this(context, null);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        screenWidth = Utils.getScreenWidth(mContext);
        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientShape);
        //获取自定义属性和默认值
        startColor = mTypedArray.getColor(R.styleable.GradientShape_start_color, ContextCompat.getColor(mContext,R.color.gradient_left));
        endColor = mTypedArray.getColor(R.styleable.GradientShape_end_color, ContextCompat.getColor(mContext,R.color.gradient_right));

        borderCount = mTypedArray.getInteger(R.styleable.GradientShape_border_count, 5);
        firstX = mTypedArray.getInteger(R.styleable.GradientShape_first_x, 0);
        firstY = mTypedArray.getInteger(R.styleable.GradientShape_first_y, 0);
        secondX = mTypedArray.getInteger(R.styleable.GradientShape_second_x, 0);
        secondY = mTypedArray.getInteger(R.styleable.GradientShape_second_y, 50);
        thirdX = mTypedArray.getInteger(R.styleable.GradientShape_third_x, screenWidth / 2);
        thirdY = mTypedArray.getInteger(R.styleable.GradientShape_third_y, 100);
        fourthX = mTypedArray.getInteger(R.styleable.GradientShape_fourth_x, screenWidth);
        fourthY = mTypedArray.getInteger(R.styleable.GradientShape_fourth_y, 50);
        fifthX = mTypedArray.getInteger(R.styleable.GradientShape_fifth_x, screenWidth);
        fifthY = mTypedArray.getInteger(R.styleable.GradientShape_fifth_y, 0);
        middleX = mTypedArray.getFloat(R.styleable.GradientShape_middle_x, 0.5f);
        mTypedArray.recycle();
    }


    public void setPointXy(int borderCount, int firstX, int firstY, int secondX, int secondY, int fourthX, int fourthY, int fifthX, int fifthY) {

        this.borderCount = borderCount;
        this.firstX = firstX;
        this.firstY = firstY;
        this.secondX = secondX;
        this.secondY = secondY;
        this.fourthX = fourthX;
        this.fourthY = fourthY;
        this.fifthX = fifthX;
        this.fifthY = fifthY;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*设置背景为白色*/
        canvas.drawColor(Color.TRANSPARENT);

		/*去锯齿*/
        paint.setAntiAlias(true);
        /*设置paint的外框宽度*/
        paint.setStrokeWidth(3);

		/*设置paint　的style为　FILL：实心*/
        paint.setStyle(Paint.Style.FILL);

        Shader mShader = new LinearGradient(0, 0, screenWidth, 0,
                new int[]{startColor, endColor},
                null, Shader.TileMode.REPEAT);
        paint.setShader(mShader);

        Path path = new Path();
        if (borderCount == 3) {//三角形使用1,3,5点
            path.moveTo(Utils.dip2Px(mContext, firstX), Utils.dip2Px(mContext, firstY));
            path.lineTo(screenWidth * middleX, Utils.dip2Px(mContext, thirdY));
            path.lineTo(fifthX, Utils.dip2Px(mContext, fifthY));
        } else if (borderCount == 4) {//四边形使用1,2,4,5点
            path.moveTo(Utils.dip2Px(mContext, firstX), Utils.dip2Px(mContext, firstY));
            path.lineTo(Utils.dip2Px(mContext, secondX), Utils.dip2Px(mContext, secondY));
            path.lineTo(fourthX, Utils.dip2Px(mContext, fourthY));
            path.lineTo(fifthX, Utils.dip2Px(mContext, fifthY));
        } else if (borderCount == 5) {//五边形使用1,2,3,4,5点
            path.moveTo(Utils.dip2Px(mContext, firstX), Utils.dip2Px(mContext, firstY));
            path.lineTo(Utils.dip2Px(mContext, secondX), Utils.dip2Px(mContext, secondY));
            path.lineTo(screenWidth * middleX, Utils.dip2Px(mContext, thirdY));
            path.lineTo(fourthX, Utils.dip2Px(mContext, fourthY));
            path.lineTo(fifthX, Utils.dip2Px(mContext, fifthY));
        }
        path.close();
        canvas.drawPath(path, paint);
    }

}
