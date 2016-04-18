
package com.broadcom.ui;

import com.broadcom.ui.ExitConfirmFragment.ExitConfirmCallback;

import android.app.FragmentManager;

/**
 * 回调对话框
 *
 *
 */
public class ExitConfirmUtils implements ExitConfirmCallback {

    private final ExitConfirmFragment.ExitConfirmCallback mListener;
    private ExitConfirmFragment mDialog;

    @Override
    public void onExit() {
        mDialog = null;
        if (mListener != null) {
            try {
                mListener.onExit();
            } catch (Throwable t) {
            }
        }
    }

    @Override
    public void onExitCancelled() {
        mDialog = null;
        if (mListener != null) {
            try {
                mListener.onExitCancelled();
            } catch (Throwable t) {
            }
        }
    }

    public ExitConfirmUtils(ExitConfirmFragment.ExitConfirmCallback listener) {
        mListener = listener;
    }

    public void show(FragmentManager mgr) {

        if (mDialog == null) {
            mDialog = ExitConfirmFragment.createDialog(this);
            mDialog.show(mgr, null);
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.setCallback(null);
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
