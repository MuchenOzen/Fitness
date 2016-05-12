package orozdevelopment.fitness.posture;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import orozdevelopment.fitness.R;

/**
 * Created by oroz7_000 on 5/12/2016.
 */
public class Preference_PostureFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.posture_preference);
    }
}
