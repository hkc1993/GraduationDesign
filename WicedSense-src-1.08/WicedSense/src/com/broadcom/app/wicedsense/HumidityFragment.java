package com.broadcom.app.wicedsense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author huangkuncan
 *  湿度
 */
public class HumidityFragment extends BaseThermoFragment {

    @Override
    protected void initRangeValues() {
        mMaxValue = SensorDataParser.SENSOR_HUMIDITY_MAX;
        mMinValue = SensorDataParser.SENSOR_HUMIDITY_MIN;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.humidity_fragment, null);
        return v;
    }

    @Override
    protected void setGaugeText(float value) {
        if (mGaugeLevel == null) {
            return;
        }
        mGaugeValue.setText(getString(R.string.humidity_value, String.format("%.1f", value)));
    }

    @Override
    protected String getPropertyName() {
        return "humd";
    }

}
