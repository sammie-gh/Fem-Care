package com.sammiegh.femcare.servise;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.sammiegh.femcare.R;
import com.sammiegh.femcare.activity.MainActivity;

public class EvaSchedulingService extends IntentService {
    private NotificationManager mNotificationManager;
    String sGiorniPrima;
    String sTipo;

    public EvaSchedulingService() {
        super("SchedulingService");
    }


    public void onHandleIntent(Intent intent) {
        String tempGiorniS;
        sTipo = intent.getStringExtra("tipo");
        sGiorniPrima = intent.getStringExtra("giorniprima");
        String title = "";
        if (sTipo.equals("0")) {
            title = title + getString(R.string.period_alarm);
        } else if (sTipo.equals("1")) {
            title = title + getString(R.string.fertility_alarm);
        } else if (sTipo.equals("2")) {
            title = title + getString(R.string.ovulation_alarm);
        }
        if (sGiorniPrima.equals("1")) {
            tempGiorniS = getString(R.string.day);
        } else {
            tempGiorniS = getString(R.string.days);
        }
        sendNotification(title, getString(R.string.alarm_solo) + " " + sGiorniPrima + " " + tempGiorniS + " " + getString(R.string.alarm_mancano));
    }
    public static Notification notification;

    private void sendNotification(String title, String msg) {

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainActivity.class),
                0
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String NOTIFICATION_CHANNEL_ID = "com.androworld.evaperiodtracker";
            String channelName = "My Remainder Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

            notification = notificationBuilder.setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(title)
                    .setContentIntent(contentIntent)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .build();
            startForeground(2, notification);
        } else {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_not_fiore)
                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setLights(-65281, 500, 500)
                    .setContentText(msg);
            mBuilder.setContentIntent(contentIntent);
            mNotificationManager.notify(1, mBuilder.build());
        }

    }
}
