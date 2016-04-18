package com.broadcom.app.wicedsmart.ota.ui;

import com.broadcom.app.wicedsense.R;

import com.broadcom.app.wicedsmart.ota.OtaSettings;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class OtaConfirmFragment extends DialogFragment implements
        android.content.DialogInterface.OnClickListener {
    public interface Callback {
        public void onOtaConfirmed();

        public void onOtaCancelled();
    }

    private static final String TAG = OtaSettings.TAG_PREFIX + "OtaConnectConfirmFragment";

    public static OtaConfirmFragment createDialog(Callback cb, OtaResource resource) {
        OtaConfirmFragment f = new OtaConfirmFragment();
        f.mCallback = cb;
        f.mOtaResource = resource;
        return f;
    }

    private Callback mCallback;
    private boolean mIsOk;
    private OtaResource mOtaResource;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog()");
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.ota_confirm_msg, mOtaResource.getName()))
                .setPositiveButton(R.string.ota_lbl_ok, this)
                .setNegativeButton(R.string.ota_lbl_cancel, this);
        if (mOtaResource.isMandatory()) {
            b.setTitle(getString(R.string.ota_dialog_title_mandatory));
        } else {
            b.setTitle(getString(R.string.ota_dialog_title_available));
        }
        return b.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mCallback == null) {
            return;
        }
        if (which == AlertDialog.BUTTON_POSITIVE) {
            mIsOk = true;
            mCallback.onOtaConfirmed();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (!mIsOk) {
            if (mCallback != null) {
                mCallback.onOtaCancelled();
            }
        }
    }

}
