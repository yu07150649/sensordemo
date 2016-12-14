package com.example.yu.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAccelerometer;
    private TextView tOrientation;
    private TextView tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAccelerometer = (TextView) this.findViewById(R.id.accelerometer);
        tOrientation = (TextView) this.findViewById(R.id.orientation);
        tLight = (TextView) this.findViewById(R.id.light);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener((SensorEventListener) this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener((SensorEventListener) this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener((SensorEventListener) this,mLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorEventListener) this);
    }
    public void onSensorChanged(SensorEvent event){
        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];
        if(event.sensor.getType()==Sensor.TYPE_ORIENTATION)
        {
            tOrientation.setText("方位"+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            tAccelerometer.setText("加速度："+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_LIGHT)
        {
            tLight.setText("光线"+event.values[0]);
        }
    }
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
}
