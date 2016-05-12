package orozdevelopment.fitness.posture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by oroz7_000 on 5/12/2016.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        boolean notif = preferences.getBoolean("pref_notif_on", true);
        boolean smartNotif = preferences.getBoolean("pref_smartNotif_on", false);

        Alarm alarm = new Alarm();

        //checks if any notifications were enabled
        if(notif){
            alarm.setAlarm(context);
        }
        if(smartNotif){
            alarm.setSmartAlarm(context);
        }

    }
}
