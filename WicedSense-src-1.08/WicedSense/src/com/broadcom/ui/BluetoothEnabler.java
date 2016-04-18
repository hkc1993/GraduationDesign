package com.broadcom.ui;

import com.broadcom.app.wicedsense.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 打开蓝牙
 *
 *
 */
public class BluetoothEnabler {
    public static final int REQUEST_ENABLE_BT = 100;

    public static boolean checkBluetoothOn(Activity a) {

        BluetoothManager bluetoothManager = (BluetoothManager) a
                .getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothManager == null || bluetoothAdapter == null) {
            Toast.makeText(a, R.string.notifier_bluetooth_unsupported, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!bluetoothAdapter.isEnabled()) {
        	
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            a.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            return false;
        }
        return true;
    }

}
