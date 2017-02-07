package test.com.keduchidemo;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 *自定义刻度尺
 *@author xull
 *@date 2017/2/6
 */

public class MnScaleBar extends View {
    private Context mContext;

    /**
     * 最大刻度
     */
    private int max=200;
    private int screenWidth=720;//屏幕宽度
    private int mScaleMargin=15;//刻度间距
    private int mScaleHeight=20;//刻度高度
    private int mScaleMaxHeight=mScaleHeight*2;//整刻度线的高度
    private int mRectWidth=max*mScaleMargin;//总宽度
    private int mRectHeight=150;
    private Rect mRect;

    private Scroller mScroller;
    private int mCountScale;
    private int mScrollLastX;
    private OnScrollListener onScrollListener;
    private int mScreenMidCountScale = screenWidth/mScaleMargin/2; //中间刻度
    public MnScaleBar(Context context) {
        this(context,null);
    }

    public MnScaleBar(Context context, AttributeSet attrs) {
        this(context,attrs,-1);
    }

    public MnScaleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        screenWidth=getPhoneW(context);
        mScreenMidCountScale = screenWidth/mScaleMargin/2; //中间刻度
        mScroller=new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置LayoutParams 属性
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(mRectWidth,mRectHeight);
        this.setLayoutParams(lp);
        mRect=new Rect(0,0,mRectWidth,mRectHeight);
        //
        Paint mPaint=new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mRect,mPaint);


        onDrawScale(canvas);
        onDrawPointer(canvas);
        super.onDraw(canvas);
    }
    //画刻度
    private void onDrawScale(Canvas canvas){
        if(canvas==null)return;
        Paint mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(20);
        for(int i=0;i<max;i++){
            if(i!=0&&i!=max){
                if(i%10==0){
                    canvas.drawLine(i*mScaleMargin,mRectHeight,i*mScaleMargin,mRectHeight-mScaleMaxHeight,mPaint);
                    canvas.drawText(i+"",i*mScaleMargin,mRectHeight-mScaleMaxHeight-10,mPaint);
                }else{
                    canvas.drawLine(i*mScaleMargin,mRectHeight,i*mScaleMargin,mRectHeight-mScaleHeight,mPaint);
                }
            }
        }
    }
    //画指针
    private void onDrawPointer(Canvas canvas){
        if(canvas==null)return;
        Paint mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(20);

        int countScale=screenWidth/mScaleMargin/2;
        int finalX=mScroller.getFinalX();
        int temCountScale=(int)Math.rint((double)finalX/(double)mScaleMargin);
        mCountScale=temCountScale+countScale;
        if(onScrollListener!=null)
            onScrollListener.onScrollScale(mCountScale);
        canvas.drawLine(screenWidth/2+finalX,mRectHeight,screenWidth/2+finalX,mRectHeight-mScaleMaxHeight,mPaint);
        canvas.drawText(mCountScale+"",countScale*mScaleMargin+finalX,mRectHeight-mScaleMaxHeight-10,mPaint);
    }

    /**
     * 获取手机分辨率--W
     * */
    public static int getPhoneW(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int disW = dm.widthPixels;
        return disW;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getX();
        Log.e("onTouchEvent",event.getAction()+"");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(mScroller!=null&&!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mScrollLastX=x;

                return true;
            case MotionEvent.ACTION_MOVE:
                int dataX=mScrollLastX-x;
                if(mCountScale>=max&&dataX>0)
                    return super.onTouchEvent(event);
                if(mCountScale<=0&&dataX<0)
                    return super.onTouchEvent(event);
                smoothScrollBy(dataX,0);
                mScrollLastX=x;
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                if(mCountScale<0) mCountScale=0;
                if(mCountScale>max) mCountScale=max;
                int finalX = (mCountScale-mScreenMidCountScale) * mScaleMargin;
                mScroller.setFinalX(finalX); //纠正指针位置
                postInvalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }
    public void smoothScrollBy(int dx, int dy){
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        Log.e("smoothScrollBy",mScroller.getFinalX()+","+mScroller.getFinalY()+","+dx+","+dy);
    }
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
        Log.e("computeScroll",mScroller.getCurrX()+"");
        super.computeScroll();
    }
}
