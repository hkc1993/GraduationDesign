package com.broadcom.app.wicedsmart.ota.ui;

import com.broadcom.app.wicedsense.R;
import java.util.List;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class OtaChooserFragment extends DialogFragment implements
        android.content.DialogInterface.OnClickListener {

    public interface Callback {
        public void onOtaSoftwareSelected(OtaResource f);

        public void onOtaCancelled();
    }

    public static OtaChooserFragment createDialog(List<OtaResource> otaResources, Callback cb) {
        OtaChooserFragment f = new OtaChooserFragment();
        f.mOtaResources = otaResources;
        f.mCallback = cb;
        return f;
    }

    private List<OtaResource> mOtaResources;
    private OtaResource mSelected;
    private Callback mCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String[] otaResourceNames = new String[mOtaResources == null ? 0 : mOtaResources.size()];
        for (int i = 0; i < otaResourceNames.length; i++) {
            otaResourceNames[i] = mOtaResources.get(i).getName();
        }
        return new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.ota_chooser_title)).setItems(otaResourceNames, this)
                .setNegativeButton(R.string.ota_lbl_cancel, null).create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mSelected == null) {
            if (mCallback != null) {
                mCallback.onOtaCancelled();
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        mSelected = mOtaResources.get(which);
        if (mCallback != null) {
            mCallback.onOtaSoftwareSelected(mSelected);
        }
    }
}
