package co.orientationscreen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;

import java.util.Calendar;

public class MainActivity extends Activity implements SensorEventListener {
    private OrientationEventListener mOrientationListener;
    // screen orientation listener
    private boolean mScreenProtrait = true;
    private boolean mCurrentOrient = false;
    String TAG="MainActivity";
    private int mX, mY, mZ;
    private long lastTimeStamp = 0;
    private Calendar mCalendar;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startOrientationChangeListener();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
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



    /**
     * 获取一个最大值
     *
     * @param px
     * @param py
     * @param pz
     * @return
     */
    private int getMaxValue(int px, int py, int pz) {
        int max = 0;
        if (px > py && px > pz) {
            max = px;
        } else if (py > px && py > pz) {
            max = py;
        } else if (pz > px && pz > py) {
            max = pz;
        }
        return max;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) {
            return;
        }

        if ((event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)) {
            int x = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];
            mCalendar = Calendar.getInstance();
            long stamp = mCalendar.getTimeInMillis() / 1000l;

            int second = mCalendar.get(Calendar.SECOND);// 53

            int px = Math.abs(mX - x);
            int py = Math.abs(mY - y);
            int pz = Math.abs(mZ - z);

            int maxvalue = getMaxValue(px, py, pz);
            if (maxvalue > 2 && (stamp - lastTimeStamp) > 1) {
                lastTimeStamp = stamp;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
            mX = x;
            mY = y;
            mZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



}
