package com.broadcom.app.wicedsmart.ota.ui;

import com.broadcom.app.wicedsense.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class OtaNoFirmwareFragment extends DialogFragment {
    public static OtaNoFirmwareFragment createDialog() {
        OtaNoFirmwareFragment f = new OtaNoFirmwareFragment();
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.ota_dialog_title))
                .setMessage(getString(R.string.ota_nofirmware_msg))
                .setPositiveButton(R.string.ota_lbl_ok, null).create();
    }

}
