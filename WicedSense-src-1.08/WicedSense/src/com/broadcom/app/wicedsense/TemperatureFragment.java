package com.broadcom.app.wicedsense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 温度
 */
public class TemperatureFragment extends BaseThermoFragment {
    public static final int SCALE_F = 0;
    public static final int SCALE_C = 1;

    private int mScaleType = SCALE_F;

    @Override
    protected void initRangeValues() {
        if (mScaleType == SCALE_F) {
            mMaxValue = SensorDataParser.SENSOR_TEMP_MAX_F;
            mMinValue = SensorDataParser.SENSOR_TEMP_MIN_F;
        } else {
            mMaxValue = SensorDataParser.SENSOR_TEMP_MAX_C;
            mMinValue = SensorDataParser.SENSOR_TEMP_MIN_C;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mScaleType == SCALE_F) {
            return inflater.inflate(R.layout.temperature_fragment, null);
        } else {
            return inflater.inflate(R.layout.temperature_fragment_c, null);
        }
    }

    @Override
    protected void setGaugeText(float value) {
        if (mScaleType == SCALE_F) {
            mGaugeValue.setText(getString(R.string.temperature_value_f,
                    String.format("%.1f", value)));
        } else {
            mGaugeValue.setText(getString(R.string.temperature_value_c,
                    String.format("%.1f", value)));
        }
    }

    @Override
    protected String getPropertyName() {
        return "temp";
    }

    public void setScaleType(int scaleType) {
        mScaleType = scaleType;
        initRange();
    }

    public int getScaleType() {
        return mScaleType;
    }

    public float getLastValue() {
        return mValue;

    }
}
