package io.github.trytonvanmeer.libretrivia.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import io.github.trytonvanmeer.libretrivia.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
