package com.broadcom.app.wicedsmart.ota;

public class OtaAppInfo {
    public int mAppId;
    public int mMajorVersion;
    public int mMinorVersion;

    public OtaAppInfo(int appId, int majorVersion, int minorVersion) {
        mAppId = appId;
        mMajorVersion = majorVersion;
        mMinorVersion = minorVersion;
    }
}
