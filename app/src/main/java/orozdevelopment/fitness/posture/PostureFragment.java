package orozdevelopment.fitness.posture;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import orozdevelopment.fitness.DashboardActivity;
import orozdevelopment.fitness.R;
import orozdevelopment.fitness.SettingsActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostureFragment extends Fragment {
    public static final int POSTURE_FRAGMENT_ID = 2;

    public PostureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PostureFragment.
     */
    public static PostureFragment newInstance() {
        PostureFragment fragment = new PostureFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posture, container, false);

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        Button settingsButton = (Button)v.findViewById(R.id.settingsButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm = new Alarm();
                alarm.sendNotif(getContext());
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),
                        SettingsActivity.class);
                intent.putExtra(DashboardActivity.FRAGMENT_ID, POSTURE_FRAGMENT_ID);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

}
