package com.broadcom.app.wicedsense;


import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {

    SettingsFragment mSettingsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingsFragment = new SettingsFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}
