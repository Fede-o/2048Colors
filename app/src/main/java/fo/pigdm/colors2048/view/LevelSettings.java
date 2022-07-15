package fo.pigdm.colors2048.view;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import fo.pigdm.colors2048.R;

public class LevelSettings extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}