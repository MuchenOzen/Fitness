package orozdevelopment.fitness.posture;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import orozdevelopment.fitness.R;

/**
 * Created by oroz7_000 on 5/12/2016.
 */
public class Alarm {
    //sets normal alarm
    public void setAlarm(Context context){
        // Define our intention of executing AlertReceiver
        Intent alertIntent = new Intent(context, AlertReceiver.class);
        PendingIntent pintent = PendingIntent.getBroadcast(context,1,
                alertIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        //get saved settings values
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String notifTimeStr = preferences.getString("pref_notif_times", "15");
        float notifTimeFloat = Float.parseFloat(notifTimeStr);
        long notifTime = (long)notifTimeFloat;

        //convert time from minute to seconds
        long notifTimeSec = notifTime * 60;


        Log.i("CHOSEN TIME", "" + notifTime);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                30 * 1000,
                (notifTimeSec * 1000), pintent);
        // Toast.makeText(context, "Notification reminders have been set", Toast.LENGTH_SHORT).show();
    }
    //sets smart alarm
    public void setSmartAlarm(Context context){


        AlarmManager scheduler = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, PositionListener.class );
        PendingIntent scheduledIntent = PendingIntent.getService(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //goes off after every approximately 1 minute
        scheduler.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, 80 * 1000,
                60000, scheduledIntent);

        // Toast.makeText(context, "smartNotification reminders have been set", Toast.LENGTH_SHORT).show();
    }

    //cancels the alarm by cancelling the pending intent
    public void cancelAlarm(Context context){

        //  Toast.makeText(context, "Notification reminders have been cancelled", Toast.LENGTH_SHORT).show();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alertIntent = new Intent(context, AlertReceiver.class);
        PendingIntent pintent = PendingIntent.getBroadcast(context, 1,
                alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pintent);
    }
    //cancels the smart alarm by cancelling the pending intent
    public void cancelSmartAlarm(Context context){
        // Toast.makeText(context, "smartNotification reminders have been cancelled", Toast.LENGTH_SHORT).show();

        AlarmManager scheduler = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,PositionListener.class );
        PendingIntent scheduledIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        scheduler.cancel(scheduledIntent);
    }

    //sends the notification
    public void sendNotif(Context context){

        //get saved settings
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String msg = preferences.getString("pref_notif_main_text", "Fix your posture!");
        String msgText = preferences.getString("pref_notif_secondary_text", "Remember to keep your head in a neutral position.");
        String sound = preferences.getString("pref_notif_sound", "DEFAULT_SOUND");

        Uri uri = Uri.parse(sound);

        Log.d("pref URI", "" + uri);
        Log.d("pref sound name", sound);



        // Builds a notification
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context);

                mBuilder.setSmallIcon(R.drawable.ic_menu_camera)
                        .setColor(Color.BLUE)
                        .setContentTitle(msg)
                        .setTicker("Posture Reminder")
                        .setSound(Uri.parse(sound))
                        .setContentText(msgText);

                if(sound.equals("DEFAULT_SOUND")){
                    Log.d("changing to:", "default");
                    mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                }

        boolean vibrateOn = PreferenceManager.getDefaultSharedPreferences
                (context).getBoolean("pref_notif_vibrate", false);

        //sets vibration
        if (vibrateOn){
            long vib[] = {400,600};
            mBuilder.setVibrate(vib);
        }


        // Auto cancels the notification when clicked on in the task bar
        mBuilder.setAutoCancel(true);


        mBuilder.setOnlyAlertOnce(true);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification
        mNotificationManager.notify(1, mBuilder.build());
    }
}
