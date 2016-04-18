package com.broadcom.app.wicedsmart.ota.ui;

import com.broadcom.app.wicedsense.R;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;

public class OtaProgressFragment extends DialogFragment implements
        android.content.DialogInterface.OnClickListener, OnShowListener {

    public interface Callback {
        public void onOtaProgressDialogShow();
        public void onOtaCancelled();
    }

    public static OtaProgressFragment createDialog(Callback cb, String initialMsg) {
        OtaProgressFragment f = new OtaProgressFragment();
        f.mCallback = cb;
        f.mInitialMsg = initialMsg;
        return f;
    }

    public ProgressDialog mProgressDialog;
    private Callback mCallback;
    private String mInitialMsg;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog d = new ProgressDialog(getActivity());
        d.setTitle(R.string.ota_dialog_progress_title);
        d.setButton(Dialog.BUTTON_POSITIVE, getString(R.string.ota_lbl_cancel), this);
        d.setCancelable(false);
        d.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        d.setMax(100);
        d.setProgress(0);
        d.setMessage(mInitialMsg == null ? "" : mInitialMsg);
        mProgressDialog = d;
        mProgressDialog.setOnShowListener(this);
        return d;
    }

    public void setProgressMax(int max) {
        ProgressDialog d = mProgressDialog;
        if (d == null)
            return;
        d.setMax(max);
    }

    public void setProgress(int p) {
        ProgressDialog d = mProgressDialog;
        if (d == null)
            return;
        d.setProgress(p);
    }

    public void setMessage(String m) {
        ProgressDialog d = mProgressDialog;
        if (d == null)
            return;
        d.setMessage(m);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            if (mCallback != null) {
                mCallback.onOtaCancelled();
            }
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (mCallback != null) {
            mCallback.onOtaProgressDialogShow();
        }
    }
}
