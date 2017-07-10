package com.demo.cn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/2/24.
 */
public class RoundProgressBarWithNumber extends ProgressBar {
    private int mRadius ;
    private static final int DEFAULT_TEXT_SIZE_SCORE = 36;
    private static final int DEFAULT_TEXT_SIZE_OTHER = 14;
    private static final int DEFAULT_TEXT_COLOR = 0XFFEF4949;
    private static final int DEFAULT_TEXT_COLOR_OTHER = 0XFFD1D1D1;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFffffff;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

    /**
     * painter of all drawing things
     */
    protected Paint mPaint = new Paint();
    /**
     * color of progress number
     */
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mTextColorOther = DEFAULT_TEXT_COLOR_OTHER;
    /**
     * size of text (sp)
     */
    protected int mTextSize = sp2px( DEFAULT_TEXT_SIZE_SCORE);

    protected int mTextSizeOther=sp2px(DEFAULT_TEXT_SIZE_OTHER);
    /**
     * height of reached progress bar
     */
    protected int mReachedProgressBarHeight = dp2px( DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

    /**
     * color of reached bar
     */
    protected int mReachedBarColor = DEFAULT_TEXT_COLOR;
    /**
     * color of unreached bar
     */
    protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;
    /**
     * height of unreached progress bar
     */
    protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);
    /**
     * view width except padding
     */
    protected int mRealWidth;

    protected String PaperCountText="共100题";
    protected String PaperTimeText="使用1分钟";

    public void setPaperCountText(String paperCountText) {
        PaperCountText = paperCountText;
    }

    public void setPaperTimeText(String paperTimeText) {
        PaperTimeText = paperTimeText;
    }

    public RoundProgressBarWithNumber(Context context) {
        super(context);
    }
    public RoundProgressBarWithNumber(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public RoundProgressBarWithNumber(Context context, AttributeSet attrs,
                                           int defStyle)
    {
        super(context, attrs, defStyle);
        obtainStyledAttributes(attrs);
        mUnReachedProgressBarHeight=mReachedProgressBarHeight;
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    /**
     * get the styled attributes
     *
     * @param attrs
     */
    private void obtainStyledAttributes(AttributeSet attrs) {
        // init values from custom attributes
        final TypedArray attributes = getContext().obtainStyledAttributes(
                attrs, R.styleable.RoundProgressBarWidthNumber);
        mReachedProgressBarHeight = (int) attributes
                .getDimension(
                        R.styleable.RoundProgressBarWidthNumber_progress_reached_bar_height,
                        mReachedProgressBarHeight);

        mRadius = (int) attributes.getDimension(
                R.styleable.RoundProgressBarWidthNumber_radius, mRadius);
        attributes.recycle();
    }
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec)
    {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int paintWidth = mReachedProgressBarHeight;

        if (heightMode != MeasureSpec.EXACTLY) {

            float textHeight = (mPaint.descent() + mPaint.ascent());
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math
                    .max(mReachedProgressBarHeight, Math.abs(textHeight)));

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
                    MeasureSpec.EXACTLY);
        }
        if (widthMode != MeasureSpec.EXACTLY) {
            int exceptWidth = (int) (getPaddingLeft() + getPaddingRight()
                    + mRadius * 2 + paintWidth);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth,
                    MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        String text = getProgress()+"";
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setStyle(Paint.Style.STROKE);
        // draw unreaded bar
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        // draw reached bar
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(new RectF(0, 0, mRadius * 2, mRadius * 2), -90,
                sweepAngle, false, mPaint);
        // draw text
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        int textHeight= (int) Math.ceil(fm.descent - fm.top) + 2;
        canvas.drawText(text, mRadius, mRadius+textHeight/2 ,
                mPaint);
        // draw text other
        mPaint.setTextSize(mTextSizeOther);
        mPaint.setColor(mTextColorOther);
        canvas.drawText(PaperCountText, mRadius, mRadius +textHeight/2-120,
                mPaint);
        canvas.drawText(PaperTimeText, mRadius, mRadius +textHeight/2+70,
                mPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth = w - getPaddingRight() - getPaddingLeft();

    }

    protected int dp2px(int dpVal)
    {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
    protected int sp2px(int spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());

    }
}
