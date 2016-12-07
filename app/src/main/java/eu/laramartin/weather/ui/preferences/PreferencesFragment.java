package eu.laramartin.weather.ui.preferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import eu.laramartin.weather.R;

/**
 * Created by Lara on 07/12/2016.
 */

public class PreferencesFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.app_preferences);
    }
}