package com.example.medreminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Get id & message from intent.

      mp=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
      mp.setLooping(true);
      mp.start();


//        int notificationID= intent.getIntExtra("notificationId",0);
//        String titleMessage= intent.getStringExtra("reminderTitle");
//        String descMessage= intent.getStringExtra("reminderDesc");
//
//        //When notification is tapped call MainActivity
//        Intent mainIntent= new Intent(context, MainActivity.class);
//        PendingIntent contentIntnet= PendingIntent.getActivity(context,0, mainIntent,0);
//
//        NotificationManager mynotification=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//
//        //prepare notification.
//        Notification.Builder builder= new Notification.Builder(context);
//        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
//                .setContentTitle(titleMessage)
//                .setContentText(descMessage)
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true)
//                .setContentIntent(contentIntnet);
//
//        //Notify
//        mynotification.notify(notificationID, builder.build());


    }
}
