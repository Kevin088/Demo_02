package com.demo.cn;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/10.
 */

/**
 * onMeasure()：1.调用时间，当控件的父元素放置该控件时，用于告诉父元素该控件的大小；
 *              2.传入参数 widthMeasureSpec，heightMeasureSpec。这两个参数由高32和低16位组成，
 */
public class MyViewPager extends ViewGroup {
    private Scroller mScroller;
    private Context context;
    private float mTouchSlop;
    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initLayout();
    }
    public void initLayout(){
        mScroller=new Scroller(context);
        ViewConfiguration configuration=ViewConfiguration.get(context);
        mTouchSlop= ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed){
            int childCount=getChildCount();
            for(int i=0;i<childCount;i++){
                View childView=getChildAt(i);
                childView.layout(i*childView.getMeasuredWidth(),0,(i+1)*childView.getMeasuredWidth(),childView.getMeasuredHeight());
            }
            leftBorder=getChildAt(0).getLeft();
            rightBorder=getChildAt(getChildCount()-1).getRight();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            //测量每一个子控件的大小
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }
    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;
    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;
    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXDown=event.getRawX();
                mXLastMove=mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove=event.getRawX();
                float diff=Math.abs(mXMove-mXDown);
                mXLastMove=mXMove;
                if(diff>mTouchSlop)
                    return true;
                break;
        }
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;

                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                //scrollBy(dx,0);
                invalidate();

                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void computeScroll() {
        Log.e("ssss","computeScroll=========");
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
            Log.e("ssss","computeScroll====(  )=====");
        }
    }
}
