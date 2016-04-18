package com.broadcom.app.wicedsmart.ota.ui;

import com.broadcom.app.wicedsense.R;

import java.io.File;
import com.broadcom.app.wicedsmart.ota.OtaAppInfo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OtaAppInfoFragment extends DialogFragment implements OnShowListener {

    public interface Callback {
        public void onOtaSoftwareSelected(File f);

        public void onOtaCancelled();
    }

    public static OtaAppInfoFragment createDialog(BluetoothDevice device, OtaAppInfo appInfo) {
        OtaAppInfoFragment f = new OtaAppInfoFragment();
        f.mAppInfo = appInfo;
        f.mDevice = device;
        return f;
    }

    public static void setAppInfoWidgets(View rootView, BluetoothDevice device, OtaAppInfo appInfo,
            String noDeviceValue, String noDeviceNameValue, String unknownValue) {
        TextView deviceName = (TextView) rootView.findViewById(R.id.ota_device_name);
        TextView deviceAddress = (TextView) rootView.findViewById(R.id.ota_device_address);
        TextView appId = (TextView) rootView.findViewById(R.id.ota_app_id);
        TextView majorVersion = (TextView) rootView.findViewById(R.id.ota_major_version);
        TextView minorVersion = (TextView) rootView.findViewById(R.id.ota_minor_version);
        if (device == null) {
            deviceName.setText(noDeviceValue);
            deviceAddress.setText("");
        } else {
            String name = device.getName();
            String addr = device.getAddress();
            if (name == null || name.length() <= 0) {
                deviceName.setText(noDeviceNameValue);
            } else {
                deviceName.setText(name);
            }
            deviceAddress.setText(addr);
        }

        if (appInfo == null) {
            appId.setText(unknownValue);
            majorVersion.setText(unknownValue);
            minorVersion.setText(unknownValue);
        } else {
            appId.setText(String.format("0x%x", appInfo.mAppId));
            majorVersion.setText(String.valueOf(appInfo.mMajorVersion));
            minorVersion.setText(String.valueOf(appInfo.mMinorVersion));
        }
    }

    private OtaAppInfo mAppInfo;
    private Dialog mDialog;
    private BluetoothDevice mDevice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity()).setTitle(
                getString(R.string.ota_app_info_title))
                .setPositiveButton(R.string.ota_lbl_ok, null);
        View v = getActivity().getLayoutInflater().inflate(R.layout.ota_app_info, null);
        b.setView(v);
        mDialog = b.create();
        mDialog.setOnShowListener(this);

        return mDialog;
    }

    @Override
    public void onShow(DialogInterface d) {
        TextView deviceName = (TextView) mDialog.findViewById(R.id.ota_device_name);

        setAppInfoWidgets(deviceName.getRootView(), mDevice, mAppInfo,
                getString(R.string.ota_unknown_value), getString(R.string.ota_unknown_device_name),
                getString(R.string.ota_unknown_value));
    }

}
