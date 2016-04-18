package com.broadcom.ui;


import com.broadcom.app.wicedsense.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * 展示对话框提醒用户停止应用
 *
 */
public class ExitConfirmFragment extends DialogFragment implements
        android.content.DialogInterface.OnClickListener {

    public static interface ExitConfirmCallback {
        public void onExit();

        public void onExitCancelled();
    }

    public static ExitConfirmFragment createDialog(ExitConfirmCallback cb) {
        ExitConfirmFragment f = new ExitConfirmFragment();
        f.mCallback = cb;
        return f;
    }

    private ExitConfirmCallback mCallback;

    public void setCallback(ExitConfirmCallback cb) {
        mCallback = cb;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.exit_title))
                .setPositiveButton(R.string.exit_ok, this)
                .setNegativeButton(R.string.exit_cancel, this).create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (mCallback != null) {
            mCallback.onExitCancelled();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mCallback != null) {
            try {
                if (which == AlertDialog.BUTTON_POSITIVE) {
                    mCallback.onExit();
                } else if (which == AlertDialog.BUTTON_NEGATIVE) {
                    mCallback.onExitCancelled();
                }

            } catch (Throwable t) {
            }
        }
    }

}
