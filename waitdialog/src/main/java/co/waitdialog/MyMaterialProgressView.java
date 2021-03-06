package co.waitdialog;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/3.
 */
public class MyMaterialProgressView extends ViewGroup {

        protected CircleImageView mCircleView;
        protected MaterialProgressDrawable mProgress;
        protected static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
        protected static final int CIRCLE_DIAMETER = 40;
        protected int mCircleDiameter;
        protected int mExtraShadowSpace;

        public MyMaterialProgressView(Context context) {
            super(context);
            initProgressView();
        }

        public MyMaterialProgressView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initProgressView();
        }

        public MyMaterialProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initProgressView();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MyMaterialProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            initProgressView();
        }

        protected void initProgressView() {
            setWillNotDraw(false);
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            mCircleDiameter = (int) (CIRCLE_DIAMETER * metrics.density);
            mExtraShadowSpace = (int) ((0.1333 * CIRCLE_DIAMETER) * metrics.density);
            createProgressView();
            setVisibility(VISIBLE);
        }

        protected void createProgressView() {
            mCircleView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT);
            mProgress = new MaterialProgressDrawable(getContext(), this);
            mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
            mCircleView.setImageDrawable(mProgress);
            setColorViewAlpha(255);
            addView(mCircleView);
        }

        public void setColorViewAlpha(int targetAlpha) {
            mCircleView.getBackground().setAlpha(targetAlpha);
            mProgress.setAlpha(targetAlpha);
        }

        public void setProgressBackgroundColor(@ColorInt int color) {
            mCircleView.setBackgroundColor(color);
            mProgress.setBackgroundColor(color);
        }

        public void setColorSchemeColors(@ColorInt int[] colors) {
            mProgress.setColorSchemeColors(colors);
        }

        @Override
        public void setVisibility(int visibility) {
            if (visibility == GONE || visibility == INVISIBLE) {
                mCircleView.clearAnimation();
                mProgress.stop();
            } else {
                mProgress.start();
            }
            super.setVisibility(visibility);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            int width = getMeasuredWidth();
            int circleWidth = mCircleView.getMeasuredWidth();
            int circleHeight = mCircleView.getMeasuredHeight();
            mCircleView.layout((width / 2 - circleWidth / 2), 0, (width / 2 + circleWidth / 2), circleHeight);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            mCircleView.measure(MeasureSpec.makeMeasureSpec(mCircleDiameter, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(mCircleDiameter, MeasureSpec.EXACTLY));
            setMeasuredDimension(mCircleDiameter + mExtraShadowSpace, mCircleDiameter + mExtraShadowSpace);
        }


}
