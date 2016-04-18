package com.broadcom.app.wicedsense;

import com.broadcom.util.PreferenceUtils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragment implements
        OnSharedPreferenceChangeListener {

    private String mPreferenceKeyPrefix;
    private PreferenceScreen mRootPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferenceKeyPrefix = Settings.SETTINGS_KEY_PREFIX;
        addPreferencesFromResource(R.xml.settings);
        mRootPref = getPreferenceScreen();

        getPreferenceManager().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        PreferenceUtils.setSummaryToValue((PreferenceGroup) mRootPref);
        Preference p = findPreference(Settings.SETTINGS_KEY_VERSION);
        String versionName = Settings.getVersionName();
        if (versionName == null) {
            versionName = "??";
        }
        p.setSummary(versionName);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (mPreferenceKeyPrefix == null || !key.startsWith(mPreferenceKeyPrefix)) {
            return;
        }
        PreferenceUtils.setSummaryToValue(mRootPref, key);
    }

}
