package com.broadcom.app.license;

import com.broadcom.app.wicedsense.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 一进来先显示的对话框
 *
 */
public class LicenseDialog extends DialogFragment implements OnClickListener {
    public interface OnLicenseAcceptListener {
        public abstract void onLicenseAccepted(boolean accepted);
    }

    private OnLicenseAcceptListener listener = null;
    private boolean mLicenseAccepted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_license, container);
        TextView tv = (TextView) view.findViewById(R.id.license_text);

        Button btn = (Button) view.findViewById(R.id.btnAccept);
        btn.setOnClickListener(this);

        tv.setText("1、本软件根据博通官网的相关资料进行开发，只是简单的功能演示。\n2、本项目的所有者为西安电子科技大学。\n3、请勿将该软件用于商用，请尊重开发者的劳动成果。");
        getDialog().setTitle(R.string.title_license);

        return view;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mLicenseAccepted = false;
        if (listener != null)
            listener.onLicenseAccepted(false);
        super.onCancel(dialog);
    }

    @Override
    public void onClick(View view) {
        mLicenseAccepted = true;
        if (listener != null)
            listener.onLicenseAccepted(true);
        dismiss();
    }


    //---------------------Public APIs--------------------------
    public void setListener(OnLicenseAcceptListener listener) {
        this.listener = listener;
    }

    public boolean isLicenseAccepted() {
        return mLicenseAccepted;
    }

}
