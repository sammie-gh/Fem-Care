package com.sammiegh.femcare.pill_reminder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import com.sammiegh.femcare.R;

import java.util.Calendar;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    NotificationManager manager;
    Notification myNotication2;

    @SuppressLint("WrongConstant")
    public void onReceive(Context context, Intent intent) {
        int parseInt = Integer.parseInt(intent.getStringExtra(ReminderEditActivity.EXTRA_REMINDER_ID));
        String title = new ReminderDatabase(context).getReminder(parseInt).getTitle();
        Intent intent2 = new Intent(context, ReminderEditActivity.class);
        intent2.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(parseInt));
        NotificationCompat.Builder onlyAlertOnce = new NotificationCompat.Builder(context)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.pill_reminder_icon)).setSmallIcon(R.drawable.ic_alarm_on_white_24dp).setContentTitle("It's time to take your Medicion…").setTicker(title).setVibrate(new long[]{0, 500, 1000}).setContentText(title).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(context, parseInt, intent2, 134217728)).setAutoCancel(true).setOnlyAlertOnce(true);
        this.manager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("Channel_id", "Notification", 4);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.enableLights(true);
            notificationChannel.setShowBadge(true);
            this.manager.createNotificationChannel(notificationChannel);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.myNotication2 = onlyAlertOnce.build();
        } else {
            this.myNotication2 = onlyAlertOnce.getNotification();
        }
        this.manager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            onlyAlertOnce.setChannelId("Channel_id");
        }
        this.manager.notify("Channel_id", parseInt, onlyAlertOnce.build());
    }

    @SuppressLint("WrongConstant")
    public void setAlarm(Context context, Calendar calendar, int i) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(i));
        this.mPendingIntent = PendingIntent.getBroadcast(context, i, intent, 268435456);
        this.mAlarmManager.set(3, SystemClock.elapsedRealtime() + (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()), this.mPendingIntent);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootReceiver.class), 1, 1);
    }

    @SuppressLint("WrongConstant")
    public void setRepeatAlarm(Context context, Calendar calendar, int i, long j) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(i));
        this.mPendingIntent = PendingIntent.getBroadcast(context, i, intent, 268435456);
        long j2 = j;
        this.mAlarmManager.setRepeating(3, SystemClock.elapsedRealtime() + (calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()), j2, this.mPendingIntent);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootReceiver.class), 1, 1);
    }

    @SuppressLint("WrongConstant")
    public void cancelAlarm(Context context, int i) {
        this.mAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(context, AlarmReceiver.class), 0);
        this.mPendingIntent = broadcast;
        this.mAlarmManager.cancel(broadcast);
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootReceiver.class), 2, 1);
    }
}
