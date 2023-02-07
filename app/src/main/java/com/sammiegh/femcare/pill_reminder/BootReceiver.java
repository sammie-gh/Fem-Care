package com.sammiegh.femcare.pill_reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class BootReceiver extends BroadcastReceiver {
    private static final long milDay = 86400000;
    private static final long milHour = 3600000;
    private static final long milMinute = 60000;
    private static final long milMonth = 2592000000L;
    private static final long milWeek = 604800000;
    private String mActive;
    private AlarmReceiver mAlarmReceiver;
    private Calendar mCalendar;
    private String mDate;
    private String[] mDateSplit;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private int mReceivedID;
    private String mRepeat;
    private String mRepeatNo;
    private long mRepeatTime;
    private String mRepeatType;
    private String mTime;
    private String[] mTimeSplit;
    private String mTitle;
    private int mYear;

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            ReminderDatabase reminderDatabase = new ReminderDatabase(context);
            this.mCalendar = Calendar.getInstance();
            this.mAlarmReceiver = new AlarmReceiver();
            for (Reminder next : reminderDatabase.getAllReminders()) {
                this.mReceivedID = next.getID();
                this.mRepeat = next.getRepeat();
                this.mRepeatNo = next.getRepeatNo();
                this.mRepeatType = next.getRepeatType();
                this.mActive = next.getActive();
                this.mDate = next.getDate();
                this.mTime = next.getTime();
                this.mDateSplit = this.mDate.split("/");
                this.mTimeSplit = this.mTime.split(":");
                this.mDay = Integer.parseInt(this.mDateSplit[0]);
                this.mMonth = Integer.parseInt(this.mDateSplit[1]);
                this.mYear = Integer.parseInt(this.mDateSplit[2]);
                this.mHour = Integer.parseInt(this.mTimeSplit[0]);
                this.mMinute = Integer.parseInt(this.mTimeSplit[1]);
                Calendar calendar = this.mCalendar;
                int i = this.mMonth - 1;
                this.mMonth = i;
                calendar.set(2, i);
                this.mCalendar.set(1, this.mYear);
                this.mCalendar.set(5, this.mDay);
                this.mCalendar.set(11, this.mHour);
                this.mCalendar.set(12, this.mMinute);
                this.mCalendar.set(13, 0);
                if (this.mRepeatType.equals("Minute")) {
                    this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milMinute;
                } else if (this.mRepeatType.equals("Hour")) {
                    this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milHour;
                } else if (this.mRepeatType.equals("Day")) {
                    this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milDay;
                } else if (this.mRepeatType.equals("Week")) {
                    this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milWeek;
                } else if (this.mRepeatType.equals("Month")) {
                    this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milMonth;
                }
                if (this.mActive.equals("true")) {
                    if (this.mRepeat.equals("true")) {
                        this.mAlarmReceiver.setRepeatAlarm(context, this.mCalendar, this.mReceivedID, this.mRepeatTime);
                    } else if (this.mRepeat.equals("false")) {
                        this.mAlarmReceiver.setAlarm(context, this.mCalendar, this.mReceivedID);
                    }
                }
            }
        }
    }
}
