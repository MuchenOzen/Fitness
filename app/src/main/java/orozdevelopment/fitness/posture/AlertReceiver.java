package orozdevelopment.fitness.posture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by oroz7_000 on 5/12/2016.
 */
public class AlertReceiver extends BroadcastReceiver{
    // Called when a broadcast is made targeting this class
    @Override
    public void onReceive(Context context, Intent intent) {
        //create instance of an alarm
        Alarm alarm = new Alarm();
        Log.d("AlertReceiever", "Regular notif");
        //send the notification
        alarm.sendNotif(context);
    }
}
