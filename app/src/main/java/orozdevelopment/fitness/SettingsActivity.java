package orozdevelopment.fitness;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import orozdevelopment.fitness.posture.PostureFragment;
import orozdevelopment.fitness.posture.Preference_PostureFragment;

public class SettingsActivity extends AppCompatActivity {

    private int fragID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        fragID = (int)intent.getExtras().get(DashboardActivity.FRAGMENT_ID);
        Log.i("FRAGMENT ID", "" + fragID);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        Preference_PostureFragment mPreference_PostureFragment = new Preference_PostureFragment();


        switch (fragID){
            case PostureFragment.POSTURE_FRAGMENT_ID:
                mFragmentTransaction.replace(android.R.id.content, mPreference_PostureFragment).commit();
                break;
        }
    }
}
