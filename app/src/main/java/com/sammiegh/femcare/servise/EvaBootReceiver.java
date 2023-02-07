package com.sammiegh.femcare.servise;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.model.Period;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EvaBootReceiver extends BroadcastReceiver {
    String imagedefault;
    int activeUID;
    Intent alarmIntent;
    Calendar calTODAY = Calendar.getInstance();
    int completo;
    String date;
    Date dateActualDATA;
    String dateBefore;
    Date dateBeforeDATA;
    int dayofmonth = calTODAY.get(5);
    int daysciclo;
    int daysmestruazioni;
    long daysokFINAL;
    int daysovulation;
    JCGSQLiteHelper db;
    long diff;
    int diffDAYSIntFINAL;
    DateFormat formatodata;
    int giornoovulazione;
    int idPer;
    int iniziofertilita;
    int intentKey;
    AlarmManager manager;
    int month = (calTODAY.get(2) + 1);
    int monthBefore;
    Notifications[] notificationsSelected;
    PendingIntent pendingIntent;
    int pregnancy;
    int selCountRow;
    Period selectedPeriod;
    int timehour;
    int timemin;
    String todayString;
    int type;
    int uidPer;
    int year = calTODAY.get(1);

    public EvaBootReceiver() {
        Object valueOf;
        Object valueOf2;
        StringBuilder append = new StringBuilder().append(year).append("");
        if (month < 10) {
            valueOf = "0" + month;
        } else {
            valueOf = Integer.valueOf(month);
        }
        StringBuilder append2 = append.append(valueOf).append("");
        if (dayofmonth < 10) {
            valueOf2 = "0" + dayofmonth;
        } else {
            valueOf2 = Integer.valueOf(dayofmonth);
        }
        todayString = append2.append(valueOf2).toString();
        formatodata = new SimpleDateFormat("yyyyMMdd");
        imagedefault = "mood_default_name";
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            db = new JCGSQLiteHelper(context);
            activeUID = db.readActiveUID();
            selCountRow = db.countNotificationsSelected(activeUID);
            notificationsSelected = db.readUsedNotifications(activeUID);
            for (int i = 0; i < selCountRow; i++) {
                intentKey = notificationsSelected[i].getId();
                alarmIntent = new Intent(context.getApplicationContext(), EvaAlarmReceiver.class);
                alarmIntent.putExtra("tipo", String.valueOf(notificationsSelected[i].getType()));
                alarmIntent.putExtra("giorniprima", String.valueOf(notificationsSelected[i].getIdnotifications()));
                pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, 0);
                if (notificationsSelected[i].getType() == 0) {
                    try {
                        dateActualDATA = formatodata.parse(todayString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calOGGI = Calendar.getInstance();
                    calOGGI.setTime(dateActualDATA);
                    calOGGI.set(11, timehour);
                    calOGGI.set(12, timemin);
                    calOGGI.set(13, 0);
                    Date dateOGGI = calOGGI.getTime();
                    monthBefore = db.countBeforePeriodRow(activeUID, todayString);
                    if (monthBefore > 0) {
                        dateBefore = db.selectBeforePeriodRow(activeUID, todayString);
                        selectedPeriod = db.readPeriod(activeUID, dateBefore);
                        initializePeriod();
                        try {
                            dateBeforeDATA = formatodata.parse(dateBefore);
                        } catch (ParseException e2) {
                            e2.printStackTrace();
                        }
                    }
                    Calendar calendarM = Calendar.getInstance();
                    calendarM.setTime(dateBeforeDATA);
                    if (notificationsSelected[i].getIdnotifications() == 0) {
                        calendarM.add(5, daysciclo);
                    } else if (notificationsSelected[i].getIdnotifications() == 1) {
                        calendarM.add(5, daysciclo - 1);
                    } else if (notificationsSelected[i].getIdnotifications() == 2) {
                        calendarM.add(5, daysciclo - 2);
                    } else if (notificationsSelected[i].getIdnotifications() == 3) {
                        calendarM.add(5, daysciclo - 3);
                    } else if (notificationsSelected[i].getIdnotifications() == 4) {
                        calendarM.add(5, daysciclo - 4);
                    } else if (notificationsSelected[i].getIdnotifications() == 5) {
                        calendarM.add(5, daysciclo - 5);
                    } else if (notificationsSelected[i].getIdnotifications() == 6) {
                        calendarM.add(5, daysciclo - 6);
                    } else if (notificationsSelected[i].getIdnotifications() == 7) {
                        calendarM.add(5, daysciclo - 7);
                    }
                    calendarM.set(11, timehour);
                    calendarM.set(12, timemin);
                    calendarM.set(13, 0);
                    diff = calendarM.getTime().getTime() - dateOGGI.getTime();
                    daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    diffDAYSIntFINAL = (int) daysokFINAL;
                    if (diffDAYSIntFINAL >= 0) {
                        manager.set(AlarmManager.RTC_WAKEUP, calendarM.getTimeInMillis(), pendingIntent);
                    }
                }
                if (notificationsSelected[i].getType() == 1) {
                    try {
                        dateActualDATA = formatodata.parse(todayString);
                    } catch (ParseException e3) {
                        e3.printStackTrace();
                    }
                    Calendar calOGGI2 = Calendar.getInstance();
                    calOGGI2.setTime(dateActualDATA);
                    calOGGI2.set(11, timehour);
                    calOGGI2.set(12, timemin);
                    calOGGI2.set(13, 0);
                    Date dateOGGI2 = calOGGI2.getTime();
                    monthBefore = db.countBeforePeriodRow(activeUID, todayString);
                    if (monthBefore > 0) {
                        dateBefore = db.selectBeforePeriodRow(activeUID, todayString);
                        selectedPeriod = db.readPeriod(activeUID, dateBefore);
                        initializePeriod();
                        try {
                            dateBeforeDATA = formatodata.parse(dateBefore);
                        } catch (ParseException e4) {
                            e4.printStackTrace();
                        }
                    }
                    Calendar calendarF = Calendar.getInstance();
                    calendarF.setTime(dateBeforeDATA);
                    iniziofertilita = (daysciclo - daysovulation) - 5;
                    notificationsSelected[i].getIdnotifications();
                    if (notificationsSelected[i].getIdnotifications() == 0) {
                        calendarF.add(5, iniziofertilita);
                    } else if (notificationsSelected[i].getIdnotifications() == 1) {
                        calendarF.add(5, iniziofertilita - 1);
                    } else if (notificationsSelected[i].getIdnotifications() == 2) {
                        calendarF.add(5, iniziofertilita - 2);
                    } else if (notificationsSelected[i].getIdnotifications() == 3) {
                        calendarF.add(5, iniziofertilita - 3);
                    } else if (notificationsSelected[i].getIdnotifications() == 4) {
                        calendarF.add(5, iniziofertilita - 4);
                    } else if (notificationsSelected[i].getIdnotifications() == 5) {
                        calendarF.add(5, iniziofertilita - 5);
                    } else if (notificationsSelected[i].getIdnotifications() == 6) {
                        calendarF.add(5, iniziofertilita - 6);
                    } else if (notificationsSelected[i].getIdnotifications() == 7) {
                        calendarF.add(5, iniziofertilita - 7);
                    }
                    calendarF.set(11, timehour);
                    calendarF.set(12, timemin);
                    calendarF.set(13, 0);
                    diff = calendarF.getTime().getTime() - dateOGGI2.getTime();
                    daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    diffDAYSIntFINAL = (int) daysokFINAL;
                    if (diffDAYSIntFINAL >= 0) {
                        manager.set(AlarmManager.RTC_WAKEUP, calendarF.getTimeInMillis(), pendingIntent);
                    }
                }
                if (notificationsSelected[i].getType() == 2) {
                    try {
                        dateActualDATA = formatodata.parse(todayString);
                    } catch (ParseException e5) {
                        e5.printStackTrace();
                    }
                    Calendar calOGGI3 = Calendar.getInstance();
                    calOGGI3.setTime(dateActualDATA);
                    calOGGI3.set(11, timehour);
                    calOGGI3.set(12, timemin);
                    calOGGI3.set(13, 0);
                    Date dateOGGI3 = calOGGI3.getTime();
                    monthBefore = db.countBeforePeriodRow(activeUID, todayString);
                    if (monthBefore > 0) {
                        dateBefore = db.selectBeforePeriodRow(activeUID, todayString);
                        selectedPeriod = db.readPeriod(activeUID, dateBefore);
                        initializePeriod();
                        try {
                            dateBeforeDATA = formatodata.parse(dateBefore);
                        } catch (ParseException e6) {
                            e6.printStackTrace();
                        }
                    }
                    Calendar calendarO = Calendar.getInstance();
                    calendarO.setTime(dateBeforeDATA);
                    giornoovulazione = daysciclo - daysovulation;
                    if (notificationsSelected[i].getIdnotifications() == 0) {
                        calendarO.add(5, giornoovulazione);
                    } else if (notificationsSelected[i].getIdnotifications() == 1) {
                        calendarO.add(5, giornoovulazione - 1);
                    } else if (notificationsSelected[i].getIdnotifications() == 2) {
                        calendarO.add(5, giornoovulazione - 2);
                    } else if (notificationsSelected[i].getIdnotifications() == 3) {
                        calendarO.add(5, giornoovulazione - 3);
                    } else if (notificationsSelected[i].getIdnotifications() == 4) {
                        calendarO.add(5, giornoovulazione - 4);
                    } else if (notificationsSelected[i].getIdnotifications() == 5) {
                        calendarO.add(5, giornoovulazione - 5);
                    } else if (notificationsSelected[i].getIdnotifications() == 6) {
                        calendarO.add(5, giornoovulazione - 6);
                    } else if (notificationsSelected[i].getIdnotifications() == 7) {
                        calendarO.add(5, giornoovulazione - 7);
                    }
                    calendarO.set(11, timehour);
                    calendarO.set(12, timemin);
                    calendarO.set(13, 0);
                    diff = calendarO.getTime().getTime() - dateOGGI3.getTime();
                    daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    diffDAYSIntFINAL = (int) daysokFINAL;
                    if (diffDAYSIntFINAL >= 0) {
                        manager.set(AlarmManager.RTC_WAKEUP, calendarO.getTimeInMillis(), pendingIntent);
                    }
                }
            }
        }
    }

    public void initializePeriod() {
        idPer = selectedPeriod.getId();
        uidPer = selectedPeriod.getUid();
        type = selectedPeriod.getType();
        date = selectedPeriod.getDate();
        completo = selectedPeriod.getCompleto();
        daysmestruazioni = selectedPeriod.getDaysMestruazioni();
        daysciclo = selectedPeriod.getDaysCiclo();
        daysovulation = selectedPeriod.getDaysOvulation();
        pregnancy = selectedPeriod.getPregnancy();
    }
}
