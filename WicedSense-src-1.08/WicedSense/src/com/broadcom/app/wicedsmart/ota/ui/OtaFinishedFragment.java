package com.broadcom.app.wicedsmart.ota.ui;

import com.broadcom.app.wicedsense.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class OtaFinishedFragment extends DialogFragment {
    public interface Callback {
        public void onOtaFinished(boolean updateComplete);
    }

    public static OtaFinishedFragment createDialog(Callback cb, String msg, boolean isError,
            boolean isAbort) {
        OtaFinishedFragment f = new OtaFinishedFragment();
        f.mCallback = cb;
        f.mMessage = msg;
        f.mIsError = isError;
        f.mIsAbort = isAbort;
        return f;
    }

    private Callback mCallback;
    private String mMessage;
    private boolean mIsError;
    private boolean mIsAbort;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.ota_dialog_title)).setMessage(mMessage)
                .setPositiveButton(R.string.ota_lbl_ok, null).create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mCallback != null) {
            mCallback.onOtaFinished(!mIsError && !mIsAbort);
        }
    }

}
