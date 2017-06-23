package co.orientationscreen;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;

public class MainActivity extends Activity {
    private OrientationEventListener mOrientationListener;
    // screen orientation listener
    private boolean mScreenProtrait = true;
    private boolean mCurrentOrient = false;
    String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startOrientationChangeListener();
    }


    private final void startOrientationChangeListener() {
        mOrientationListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int rotation) {
                if (((rotation >= 0) && (rotation <= 45)) || (rotation >= 315)||((rotation>=135)&&(rotation<=225))) {//portrait
                    mCurrentOrient = true;
                    if(mCurrentOrient!=mScreenProtrait)
                    {
                        mScreenProtrait = mCurrentOrient;
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Log.d(TAG, "Screen orientation changed from Landscape to Portrait!");
                    }
                }
                else if (((rotation > 45) && (rotation < 135))||((rotation>225)&&(rotation<315))) {//landscape
                    mCurrentOrient = false;
                    if(mCurrentOrient!=mScreenProtrait)
                    {
                        mScreenProtrait = mCurrentOrient;
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        Log.d(TAG, "Screen orientation changed from Portrait to Landscape!");
                    }
                }
            }
        };
        mOrientationListener.enable();
    }
}
