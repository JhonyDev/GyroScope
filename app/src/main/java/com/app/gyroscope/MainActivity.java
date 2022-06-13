package com.app.gyroscope;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity implements GyroListener {

    public static String TAG = "tag";

    TextView textView;
    TextView textView2;
    TextView textView3;
    GraphView graph;

    TextView textView_;
    TextView textView2_;

    GraphView graph_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        graph = findViewById(R.id.graph);

        textView_ = findViewById(R.id.text_);
        textView2_ = findViewById(R.id.text2_);
        graph_ = findViewById(R.id.graph_);

        PhoneGyroscope phoneGyroscope = new PhoneGyroscope(this);
        phoneGyroscope.start();

    }

    @Override
    public void accuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void sensorChanged(SensorEvent event) {
//        Log.i(TAG, "sensorChanged: " + event.values.length);
    }

    @Override
    public void coordinateListener(float x, float y, float z) {
        textView.setText("x - " + x);
        textView2.setText("y - " + y);
        textView3.setText("z - " + z);
        setAccelerometer(x, y);

        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, x),
                new DataPoint(2, y),
                new DataPoint(3, z),
        });

        graph.addSeries(series);

    }

    public void setAccelerometer(float x, float y) {
        textView_.setText("x - " + (1 - x));
        textView2_.setText("y - " + (1 - y));
        graph_.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 1 - x),
                new DataPoint(2, 1 - y),
        });

        graph_.addSeries(series);
    }
}