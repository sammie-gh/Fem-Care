package com.sammiegh.femcare.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.servise.EvaAlarmReceiver;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NotificationDetailsAdapter extends CursorAdapter {
    int risultatone;
    int activeUID;
    Date alarmInitialDate;
    Intent alarmIntent;
    int checkNot;
    int completo;
    int custom;
    String date;
    Date dateActualDATA;
    String dateBefore;
    Date dateBeforeDATA;
    String dateentry;
    int daysciclo;
    int daysmestruazioni;
    long daysokFINAL;
    int daysovulation;
    JCGSQLiteHelper db;
    long diff;
    int diffDAYSIntFINAL;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    String frequency;
    int giornoovulazione;
    int idPer;
    int idnot;
    int iniziofertilita;
    int intentKey;
    int interval = 86400000;
    AlarmManager manager;
    int monthBefore;
    String name;
    PendingIntent pendingIntent;
    PendingIntent pendingIntentCustom;
    int periodInterval;
    int pregnancy;
    Period selectedPeriod;
    int thePosition;
    int timehour;
    int timemin;
    String tipoAllarme;
    int tipologiaCustom;
    String todayString;
    int type;
    int uidPer;
    int used;
    int dayofmonth = Calendar.getInstance().get(5);
    int month = (Calendar.getInstance().get(2) + 1);
    int year = Calendar.getInstance().get(1);

    public NotificationDetailsAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
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
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_notification_details, parent, false);
    }

    public void bindView(View view, final Context context, Cursor cursor) {
        Object valueOf;
        CheckBox checkNotification = (CheckBox) view.findViewById(R.id.checkNotDetail);
        TextView txtNotificationName = (TextView) view.findViewById(R.id.txtNotificationname);
        TextView txtNotificationTime = (TextView) view.findViewById(R.id.txtNotificationTime);
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        idnot = cursor.getInt(cursor.getColumnIndexOrThrow("idnot"));
        name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        frequency = cursor.getString(cursor.getColumnIndexOrThrow("frequency"));
        timehour = cursor.getInt(cursor.getColumnIndexOrThrow("timehour"));
        timemin = cursor.getInt(cursor.getColumnIndexOrThrow("timemin"));
        custom = cursor.getInt(cursor.getColumnIndexOrThrow("custom"));
        used = cursor.getInt(cursor.getColumnIndexOrThrow("used"));
        type = cursor.getInt(cursor.getColumnIndexOrThrow("type"));
        dateentry = cursor.getString(cursor.getColumnIndexOrThrow("dateentry"));
        if (idnot == 0) {
            txtNotificationName.setText(context.getResources().getString(R.string.ovulation_alarm_details_day0));
        } else if (idnot == 1) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_day1));
        } else if (idnot == 2) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_dayn));
        } else if (idnot == 3) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_dayn));
        } else if (idnot == 4) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_dayn));
        } else if (idnot == 5) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_dayn));
        } else if (idnot == 6) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_dayn));
        } else if (idnot == 7) {
            txtNotificationName.setText(idnot + " " + context.getResources().getString(R.string.ovulation_alarm_details_dayn));
        }
        String timehourString = String.valueOf(timehour < 10 ? "0" + timehour : Integer.valueOf(timehour));
        if (timemin < 10) {
            valueOf = "0" + timemin;
        } else {
            valueOf = Integer.valueOf(timemin);
        }
        txtNotificationTime.setText(timehourString + ":" + valueOf);
        if (used == 1) {
            checkNotification.setChecked(true);
        } else {
            checkNotification.setChecked(false);
        }
        checkNotification.setTag(Integer.valueOf(idnot));
        db = new JCGSQLiteHelper(context);
        activeUID = db.readActiveUID();
        tipoAllarme = db.readKeySetting(activeUID, "tempalarm");
        thePosition = Integer.parseInt(tipoAllarme);
        checkNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkNot = 1;
                } else {
                    checkNot = 0;
                }
                int position = ((Integer) v.getTag()).intValue();
                intentKey = (type * 10) + position;
                if (custom == 0) {
                    alarmIntent = new Intent(context.getApplicationContext(), EvaAlarmReceiver.class);
                    alarmIntent.putExtra("tipo", tipoAllarme);
                    alarmIntent.putExtra("giorniprima", String.valueOf(position));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
                    } else pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, 0);

                }
                if (checkNot == 1) {
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
                    if (thePosition == 0) {
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
                        if (position == 0) {
                            calendarM.add(5, daysciclo);
                        } else if (position == 1) {
                            calendarM.add(5, daysciclo - 1);
                        } else if (position == 2) {
                            calendarM.add(5, daysciclo - 2);
                        } else if (position == 3) {
                            calendarM.add(5, daysciclo - 3);
                        } else if (position == 4) {
                            calendarM.add(5, daysciclo - 4);
                        } else if (position == 5) {
                            calendarM.add(5, daysciclo - 5);
                        } else if (position == 6) {
                            calendarM.add(5, daysciclo - 6);
                        } else if (position == 7) {
                            calendarM.add(5, daysciclo - 7);
                        }
                        calendarM.set(11, timehour);
                        calendarM.set(12, timemin);
                        calendarM.set(13, 0);
                        Date dateAlarmM = calendarM.getTime();
                        periodInterval = interval * daysciclo;
                        diff = dateAlarmM.getTime() - dateOGGI.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        if (diffDAYSIntFINAL >= 0) {
                            manager.set(AlarmManager.RTC_WAKEUP, calendarM.getTimeInMillis(), pendingIntent);
                        }
                    } else if (thePosition == 1) {
                        monthBefore = db.countBeforePeriodRow(activeUID, todayString);
                        if (monthBefore > 0) {
                            dateBefore = db.selectBeforePeriodRow(activeUID, todayString);
                            selectedPeriod = db.readPeriod(activeUID, dateBefore);
                            initializePeriod();
                            try {
                                dateBeforeDATA = formatodata.parse(dateBefore);
                            } catch (ParseException e3) {
                                e3.printStackTrace();
                            }
                        }
                        Calendar calendarF = Calendar.getInstance();
                        calendarF.setTime(dateBeforeDATA);
                        iniziofertilita = (daysciclo - daysovulation) - 5;
                        if (position == 0) {
                            calendarF.add(5, iniziofertilita);
                        } else if (position == 1) {
                            calendarF.add(5, iniziofertilita - 1);
                        } else if (position == 2) {
                            calendarF.add(5, iniziofertilita - 2);
                        } else if (position == 3) {
                            calendarF.add(5, iniziofertilita - 3);
                        } else if (position == 4) {
                            calendarF.add(5, iniziofertilita - 4);
                        } else if (position == 5) {
                            calendarF.add(5, iniziofertilita - 5);
                        } else if (position == 6) {
                            calendarF.add(5, iniziofertilita - 6);
                        } else if (position == 7) {
                            calendarF.add(5, iniziofertilita - 7);
                        }
                        calendarF.set(11, timehour);
                        calendarF.set(12, timemin);
                        calendarF.set(13, 0);
                        diff = calendarF.getTime().getTime() - dateOGGI.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        if (diffDAYSIntFINAL >= 0) {
                            manager.set(AlarmManager.RTC_WAKEUP, calendarF.getTimeInMillis(), pendingIntent);
                        }
                    } else if (thePosition == 2) {
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
                        Calendar calendarO = Calendar.getInstance();
                        calendarO.setTime(dateBeforeDATA);
                        giornoovulazione = daysciclo - daysovulation;
                        if (position == 0) {
                            calendarO.add(5, giornoovulazione);
                        } else if (position == 1) {
                            calendarO.add(5, giornoovulazione - 1);
                        } else if (position == 2) {
                            calendarO.add(5, giornoovulazione - 2);
                        } else if (position == 3) {
                            calendarO.add(5, giornoovulazione - 3);
                        } else if (position == 4) {
                            calendarO.add(5, giornoovulazione - 4);
                        } else if (position == 5) {
                            calendarO.add(5, giornoovulazione - 5);
                        } else if (position == 6) {
                            calendarO.add(5, giornoovulazione - 6);
                        } else if (position == 7) {
                            calendarO.add(5, giornoovulazione - 7);
                        }
                        calendarO.set(11, timehour);
                        calendarO.set(12, timemin);
                        calendarO.set(13, 0);
                        diff = calendarO.getTime().getTime() - dateOGGI.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        if (diffDAYSIntFINAL >= 0) {
                            manager.set(AlarmManager.RTC_WAKEUP, calendarO.getTimeInMillis(), pendingIntent);
                        }
                    } else if (thePosition > 2) {
                        tipologiaCustom = Integer.parseInt(frequency);
                        if (tipologiaCustom == 1000) {
                            try {
                                alarmInitialDate = formatodata.parse(dateentry);
                            } catch (ParseException e5) {
                                e5.printStackTrace();
                            }
                            diff = dateOGGI.getTime() - alarmInitialDate.getTime();
                            daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            diffDAYSIntFINAL = (int) daysokFINAL;
                            risultatone = diffDAYSIntFINAL % 1;
                            if (diffDAYSIntFINAL >= 0 && risultatone == 0) {
                                manager.set(AlarmManager.RTC_WAKEUP, calOGGI.getTimeInMillis(), pendingIntentCustom);
                            }
                        } else if (tipologiaCustom >= 71 && tipologiaCustom <= 77) {
                            try {
                                alarmInitialDate = formatodata.parse(dateentry);
                            } catch (ParseException e6) {
                                e6.printStackTrace();
                            }
                            if (tipologiaCustom - 70 == whichDayOfWeek(calOGGI).intValue()) {
                                manager.set(AlarmManager.RTC_WAKEUP, calOGGI.getTimeInMillis(), pendingIntentCustom);
                            }
                        } else if (tipologiaCustom >= 1 && tipologiaCustom <= 31) {
                            if (tipologiaCustom == calOGGI.get(5)) {
                                manager.set(AlarmManager.RTC_WAKEUP, calOGGI.getTimeInMillis(), pendingIntentCustom);
                            }
                        } else if (tipologiaCustom >= 102 && tipologiaCustom <= 280) {
                            try {
                                alarmInitialDate = formatodata.parse(dateentry);
                            } catch (ParseException e7) {
                                e7.printStackTrace();
                            }
                            diff = dateOGGI.getTime() - alarmInitialDate.getTime();
                            daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            diffDAYSIntFINAL = (int) daysokFINAL;
                            risultatone = diffDAYSIntFINAL % (tipologiaCustom - 100);
                            if (diffDAYSIntFINAL >= 0 && risultatone == 0) {
                                manager.set(AlarmManager.RTC_WAKEUP, calOGGI.getTimeInMillis(), pendingIntentCustom);
                            }
                        }
                    }
                } else if (thePosition >= 0 && thePosition <= 2) {
                    manager.cancel(pendingIntent);
                } else if (thePosition > 2) {
                    manager.cancel(pendingIntentCustom);
                }
                db.updateNotificationCheck(activeUID, thePosition, position, checkNot);
            }
        });
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

    public Integer whichDayOfWeek(Calendar calendar) {
        if (calendar.get(7) == 2) {
            return 1;
        }
        if (calendar.get(7) == 3) {
            return 2;
        }
        if (calendar.get(7) == 4) {
            return 3;
        }
        if (calendar.get(7) == 5) {
            return 4;
        }
        if (calendar.get(7) == 6) {
            return 5;
        }
        if (calendar.get(7) == 7) {
            return 6;
        }
        if (calendar.get(7) == 1) {
            return 7;
        }
        return null;
    }
}
