package com.demo.cn.mytest;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by xll on 2016/11/1.
 */

public class MyTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    /**
     * 视频播放器
     */
    private MediaPlayer mediaPlayer;
    public MyTextureView(Context context) {
        super(context);
    }

    public MyTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(){
        setSurfaceTextureListener(this);
    }

    /**
     *TextureView的监听回调
     */
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
