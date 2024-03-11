package com.madinaappstudio.deviceanalyzer.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madinaappstudio.deviceanalyzer.CrashReporter;
import com.madinaappstudio.deviceanalyzer.R;

public class Proximity extends Fragment implements SensorEventListener {

    Context context;
    TextView sensorNameHead, sType, sName, sVendor, sVersion, sMaxRange, sResolution, sPower, sUnit,
            vOne, vTwo, vThree, vOneText, vTwoText, vThreeText;

    public Proximity(Context context) {
        this.context = context;
    }
    public Proximity() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sensor_details_fragment, container, false);

        CrashReporter.startCrashThread(context);

        sensorNameHead = view.findViewById(R.id.sensorNameHead);
        sType = view.findViewById(R.id.sType);
        sName = view.findViewById(R.id.sName);
        sVendor = view.findViewById(R.id.sVendor);
        sVersion = view.findViewById(R.id.sVersion);
        sMaxRange = view.findViewById(R.id.sMaxRange);
        sResolution = view.findViewById(R.id.sResolution);
        sPower = view.findViewById(R.id.sPower);
        sUnit = view.findViewById(R.id.sUnit);
        vOne = view.findViewById(R.id.vOne);
        vTwo = view.findViewById(R.id.vTwo);
        vThree = view.findViewById(R.id.vThree);
        vOneText = view.findViewById(R.id.vOneText);
        vTwoText = view.findViewById(R.id.vTwoText);
        vThreeText = view.findViewById(R.id.vThreeText);

        sensorNameHead.setText(R.string.proximity);

        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            sType.setText(R.string.proximity);
            sName.setText(event.sensor.getName());
            sPower.setText(String.valueOf(event.sensor.getPower()));
            sUnit.setText(R.string.cm);
            sVendor.setText(event.sensor.getVendor());
            sVersion.setText(String.valueOf(event.sensor.getVersion()));
            sMaxRange.setText(String.valueOf(event.sensor.getMaximumRange()));
            sResolution.setText(String.valueOf(event.sensor.getResolution()));

            vTwoText.setText(R.string.value);
            vTwo.setText(String.valueOf(event.values[0]).substring(0,3));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}