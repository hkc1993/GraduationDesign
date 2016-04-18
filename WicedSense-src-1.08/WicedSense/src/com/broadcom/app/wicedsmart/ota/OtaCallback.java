package com.broadcom.app.wicedsmart.ota;

public interface OtaCallback {
    public void onOtaStateChanged(int state);

    public void onOtaError(int state, int errorCode);

    public void onOtaUploadProgress(int loopCount, int bytesCurrent, int bytesTotal);

    public void onOtaAborted();

}