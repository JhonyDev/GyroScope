package com.app.gyroscope;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public interface GyroListener {
    void accuracyChanged(Sensor sensor, int accuracy);

    void sensorChanged(SensorEvent event);

    void coordinateListener(float x, float y, float z);
}
