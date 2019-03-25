package com.example.sensorreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView xValue;
    private TextView yValue;
    private TextView zValue;
    private SensorActivity sensorActivity;
    private Button button;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Sensing function
    public void sensing(View view) {
        if (xValue== null) xValue = findViewById(R.id.xValue);
        if(yValue==null) yValue=findViewById(R.id.yValue);
        if(zValue==null) zValue=findViewById(R.id.zValue);
        if (sensorActivity == null) sensorActivity=new SensorActivity(this, xValue,yValue,zValue);
        if (button == null) button = findViewById(R.id.startButton);
        //after pressing start button acceleration sensing activated
        if (button.getText().equals("Start")) {
            button.setText("Stop");
            sensorActivity.register();
        //sensing stopped
        } else {
            button.setText("Start");
            xValue.setText("Not Calculating");
            yValue.setText("Not Calculating");
            zValue.setText("Not Calculating");
            sensorActivity.unRegister();
        }
    }
}
