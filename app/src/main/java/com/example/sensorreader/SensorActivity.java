package com.example.sensorreader;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

import android.widget.TextView;

public class SensorActivity  extends Activity implements SensorEventListener {

    private final SensorManager sensorManager;
    private final Sensor temperatureSensor;
    private TextView zValue;
    private TextView xValue;
    private TextView yValue;
    private String value = null;


    //Initializing sensor activity
    public SensorActivity(Context context,  TextView xValue, TextView yValue, TextView zValue) {
        this.xValue=xValue;
        this.yValue=yValue;
        this.zValue=zValue;
        this.sensorManager=(SensorManager) context.getSystemService(SENSOR_SERVICE);
        this.temperatureSensor =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();

    }

    //register sensor event
    public void register() {
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        value = "Sensing";
    }

    //unregister sensor event
    public void unRegister() {

        sensorManager.unregisterListener(this,temperatureSensor);
        value = null;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        //Calculating acceleration

        final float alpha = (float) 0.8;


        float[] gravity= new float[3];
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        float[] linear_acceleration=new float[3];
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];



        String xAccl=String.valueOf(linear_acceleration[0]);
        String yAccl=String.valueOf(linear_acceleration[1]);
        String zAccl=String.valueOf(linear_acceleration[2]);

        if (value != null){
            xValue.setText(xAccl+ " m/s^2");
            yValue.setText(yAccl+" m/s^2");
            zValue.setText(zAccl+" m/s^2");


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
