package com.broadcom.app.wicedsense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author huangkuncan
 * 压力
 *
 */
public class PressureFragment extends BaseThermoFragment {

    @Override
    public void initRangeValues() {
        mMaxValue = SensorDataParser.SENSOR_PRESSURE_MAX;
        mMinValue = SensorDataParser.SENSOR_PRESSURE_MIN;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pressure_fragment, null);
        return v;
    }

    @Override
    protected void setGaugeText(float value) {
        mGaugeValue.setText(getString(R.string.pressure_value, String.valueOf((int) value)));
    }

    @Override
    protected String getPropertyName() {
        return "pres";
    }

}
