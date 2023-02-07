package com.sammiegh.femcare.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.servise.EvaAlarmReceiver;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.activity.GommoActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TabFragment1 extends Fragment {
    String imagedefault;
    String moodsStringDefault;
    String mucusStringDefault;
    String numDaysCycle;
    int numDaysCycleI;
    String numDaysFertile;
    int numDaysFertileI;
    String numDaysPeriod;
    int numDaysPeriodI;
    String pillStringDefault;
    String symptomsstringdefault;
    int activeUID;
    Intent alarmIntent;
    Calendar calTODAY = Calendar.getInstance();
    int completo;
    String date;
    Date dateActualDATA;
    Date dateActualOGGI;
    Date dateActualPASS;
    String dateAfter;
    Date dateAfterDATA;
    String dateBefore;
    Date dateBeforeDATA;
    String dateNote;
    int dayofmonth = calTODAY.get(5);
    int daysciclo;
    int daysmestruazioni;
    long daysokFINAL;
    long daysokFINALCheckEndDate = 1000;
    int daysovulation;
    JCGSQLiteHelper db;
    int diastolic;
    long diff;
    long diffCheckEndDate;
    int diffDAYSIntFINAL;
    int diffDAYSIntFINALCheckEndDate;
    float fianchi;
    DateFormat formatodata;
    int giornoovulazione;
    int gommo;
    float height;
    int id;
    int idNote;
    int idPer;
    ImageView imageBookmark;
    ImageView imageDate;
    ImageView imageHeart;
    int iniziofertilita;
    int intentKey;
    int intimate;
    String key;
    AlarmManager manager;
    int month = (calTODAY.get(2) + 1);
    int monthAfter;
    int monthBefore;
    String moods;
    String mucus;
    String note;
    Notifications[] notificationsSelected;
    int numorgasm;
    Date oggiDateCheck;
    int ovulationtest;
    boolean paperYeah = false;
    PendingIntent pendingIntent;
    String pill;
    int pregnancy;
    int pressure;
    RelativeLayout rEnd;
    RelativeLayout rIntercourse;
    RelativeLayout rNote;
    RelativeLayout rStart;
    int rowsNumDateNote;
    int rowsNumDatePeriod;
    int rowsNumMoodUid;
    int rowsNumMucusUid;
    int rowsNumPillUid;
    int rowsNumSymptomsUid;
    String sDateKey;
    String sOggiDateCheck;
    String secretnote;
    int selCountRow;
    Note selectedNote;
    Period selectedPeriod;
    Settings selectedSettings;
    float seno;
    int sextimes;
    Switch switchGommo;
    Switch switchPeriodEnd;
    Switch switchPeriodStart;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    TextView textDate;
    EditText textEditNote;
    int timehour;
    int timemin;
    String todayString;
    int type;
    int uid;
    int uid_note;
    int uid_per;
    String value;
    float vita;
    float weight;
    int year = calTODAY.get(1);

    public TabFragment1() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        sDateKey = getArguments().getString("datekey");
        oggiDateCheck = new Date();
        sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(oggiDateCheck);
        db = new JCGSQLiteHelper(getActivity().getApplicationContext());
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        numDaysCycle = db.readKeySetting(uid, "n_cycle_days");
        numDaysCycleI = Integer.parseInt(numDaysCycle);
        numDaysPeriod = db.readKeySetting(uid, "n_mestrual_days");
        numDaysPeriodI = Integer.parseInt(numDaysPeriod);
        numDaysFertile = db.readKeySetting(uid, "n_ovulation_days");
        numDaysFertileI = Integer.parseInt(numDaysFertile);
        textDate = (TextView) view.findViewById(R.id.txtDate);
        imageDate = (ImageView) view.findViewById(R.id.imageDate);
        textEditNote = (EditText) view.findViewById(R.id.editText);
        rStart = (RelativeLayout) view.findViewById(R.id.RelLayHeight);
        rEnd = (RelativeLayout) view.findViewById(R.id.RelLayUseDivider2);
        rIntercourse = (RelativeLayout) view.findViewById(R.id.RelLayUseDivider23);
        rNote = (RelativeLayout) view.findViewById(R.id.RelLayEditTxt);
        imageHeart = (ImageView) view.findViewById(R.id.imageHEart);
        imageBookmark = (ImageView) view.findViewById(R.id.imgBookmark);
        switchPeriodStart = (Switch) view.findViewById(R.id.StartSwitch);
        switchPeriodEnd = (Switch) view.findViewById(R.id.EndSwitch);
        switchGommo = (Switch) view.findViewById(R.id.GommoSwitch);
        rowsNumDateNote = db.countRowsNote(activeUID, sDateKey);
        rowsNumDatePeriod = db.countRowsPeriod(activeUID, sDateKey);
        if (rowsNumDatePeriod == 1) {
            switchPeriodStart.setChecked(true);
        } else {
            switchPeriodStart.setChecked(false);
        }
        monthBefore = db.countBeforePeriodRow(activeUID, sDateKey);
        if (monthBefore > 0) {
            dateBefore = db.selectBeforePeriodRow(activeUID, sDateKey);
            selectedPeriod = db.readPeriod(activeUID, dateBefore);
            initializePeriod();
            try {
                dateBeforeDATA = formatodata.parse(dateBefore);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                dateActualPASS = formatodata.parse(sDateKey);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            diffCheckEndDate = dateActualPASS.getTime() - dateBeforeDATA.getTime();
            daysokFINALCheckEndDate = TimeUnit.DAYS.convert(diffCheckEndDate, TimeUnit.MILLISECONDS);
            diffDAYSIntFINALCheckEndDate = ((int) daysokFINALCheckEndDate) + 1;
            if (diffDAYSIntFINALCheckEndDate == daysmestruazioni && completo == 1) {
                switchPeriodEnd.setChecked(true);
            }
        }
        imageBookmark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openExtraD = new Intent(getActivity(), GommoActivity.class);
                openExtraD.putExtra("datekey", sDateKey);
                startActivity(openExtraD);
            }
        });
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, sDateKey);
            initializeNote();
            textEditNote.setText(note);
            if (intimate == 0) {
                switchGommo.setChecked(false);
            } else {
                switchGommo.setChecked(true);
                imageBookmark.setVisibility(View.VISIBLE);
            }
        } else {
            rowsNumMoodUid = db.countRowsMood(activeUID);
            rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
            rowsNumMucusUid = db.countRowsMucus(activeUID);
            rowsNumPillUid = db.countRowsPills(activeUID);
            moodsStringDefault = fillString(rowsNumMoodUid, '0');
            symptomsstringdefault = fillString(rowsNumSymptomsUid, '0');
            mucusStringDefault = fillString(rowsNumMucusUid, '0');
            pillStringDefault = fillString(rowsNumPillUid, '0');
        }
        if (!sDateKey.substring(0, 4).equals(sOggiDateCheck.substring(0, 4))) {
            textDate.setText(sDateKey.substring(6, 8) + "-" + sDateKey.substring(4, 6) + "-" + sDateKey.substring(0, 4));
        } else if (!sDateKey.substring(4, 6).equals(sOggiDateCheck.substring(4, 6))) {
            textDate.setText(sDateKey.substring(6, 8) + "-" + sDateKey.substring(4, 6) + "-" + sDateKey.substring(0, 4));
        } else if (sDateKey.substring(6, 8).equals(sOggiDateCheck.substring(6, 8))) {
            textDate.setText(getString(R.string.day_today));
        } else if (Integer.parseInt(sDateKey.substring(6, 8)) == Integer.parseInt(sOggiDateCheck.substring(6, 8)) - 1) {
            textDate.setText(getString(R.string.day_yesterday));
        } else if (Integer.parseInt(sDateKey.substring(6, 8)) == Integer.parseInt(sOggiDateCheck.substring(6, 8)) + 1) {
            textDate.setText(getString(R.string.day_tomorrow));
        } else {
            textDate.setText(sDateKey.substring(6, 8) + "-" + sDateKey.substring(4, 6) + "-" + sDateKey.substring(0, 4));
        }
        textEditNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (paperYeah) {
                    rStart.setVisibility(View.VISIBLE);
                    rEnd.setVisibility(View.VISIBLE);
                    rIntercourse.setVisibility(View.VISIBLE);
                    imageHeart.setImageResource(R.drawable.img_allarga);
                } else {
                    rStart.setVisibility(View.GONE);
                    rEnd.setVisibility(View.GONE);
                    rIntercourse.setVisibility(View.GONE);
                    imageHeart.setImageResource(R.drawable.img_stringi);
                }
                if (paperYeah) {
                    paperYeah = false;
                } else {
                    paperYeah = true;
                }
            }
        });
        textEditNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    return;
                }
                if (rowsNumDateNote == 0) {
                    db.insertNote(new Note(activeUID, activeUID, sDateKey, textEditNote.getText().toString(), "", symptomsstringdefault, mucusStringDefault, moodsStringDefault, 0, 0, 0, 0, pillStringDefault, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
                    return;
                }
                db.updateNote(new Note(activeUID, activeUID, dateNote, textEditNote.getText().toString(), secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
            }
        });
        switchGommo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    imageBookmark.setVisibility(View.VISIBLE);
                    if (rowsNumDateNote == 0) {
                        db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsstringdefault, mucusStringDefault, moodsStringDefault, 0, 0, 0, 0, pillStringDefault, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
                    } else {
                        selectedNote = db.readNote(activeUID, sOggiDateCheck);
                        initializeNote();
                        db.updateNote(new Note(idNote, uid_note, dateNote, note, secretnote, symptoms, mucus, moods, 0, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
                    }
                    Intent intent = new Intent(getActivity(), GommoActivity.class);
                    intent.putExtra("datekey", sDateKey);
                    startActivity(intent);
                    return;
                }
                imageBookmark.setVisibility(View.INVISIBLE);
                if (rowsNumDateNote == 0) {
                    db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsstringdefault, mucusStringDefault, moodsStringDefault, 0, 0, 0, 0, pillStringDefault, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
                    return;
                }
                db.updateNote(new Note(idNote, uid_note, dateNote, note, secretnote, symptoms, mucus, moods, 0, 0, 0, 0, pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
            }
        });
        switchPeriodStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    try {
                        dateActualDATA = formatodata.parse(sDateKey);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calTODAY = Calendar.getInstance();
                    int year = calTODAY.get(1);
                    int month = calTODAY.get(2) + 1;
                    int dayofmonth = calTODAY.get(5);
                    try {
                        dateActualOGGI = formatodata.parse(year + "" + (month < 10 ? "0" + month : Integer.valueOf(month)) + "" + (dayofmonth < 10 ? "0" + dayofmonth : Integer.valueOf(dayofmonth)));
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        dateActualDATA = formatodata.parse(sDateKey);
                    } catch (ParseException e3) {
                        e3.printStackTrace();
                    }
                    if (dateActualDATA.compareTo(dateActualOGGI) > 0) {
                        new MaterialDialog.Builder(getActivity()).title((int) R.string.error_future_date_title).iconRes(R.mipmap.ic_error_black_48dp).content((int) R.string.error_future_date_mestruazione).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorAccent).callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                switchPeriodStart.setChecked(false);
                            }
                        }).cancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                            }
                        }).show();
                        return;
                    }
                    monthBefore = db.countBeforePeriodRow(activeUID, sDateKey);
                    monthAfter = db.countAfterPeriodRow(activeUID, sDateKey);
                    if (monthBefore > 0) {
                        dateBefore = db.selectBeforePeriodRow(activeUID, sDateKey);
                    }
                    if (monthAfter > 0) {
                        dateAfter = db.selectAfterPeriodRow(activeUID, sDateKey);
                    }
                    if (monthBefore > 0 && monthAfter <= 0) {
                        db.insertPeriod(new Period(activeUID, activeUID, 0, sDateKey, 0, numDaysPeriodI, numDaysCycleI, numDaysFertileI, 0));
                        reinsertalarms(getActivity().getApplicationContext());
                        try {
                            dateBeforeDATA = formatodata.parse(dateBefore);
                        } catch (ParseException e4) {
                            e4.printStackTrace();
                        }
                        try {
                            dateActualDATA = formatodata.parse(sDateKey);
                        } catch (ParseException e5) {
                            e5.printStackTrace();
                        }
                        diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        db.updateCicloDays(activeUID, dateBefore, diffDAYSIntFINAL);
                    }
                    if (monthBefore > 0 && monthAfter > 0) {
                        try {
                            dateAfterDATA = formatodata.parse(dateAfter);
                        } catch (ParseException e6) {
                            e6.printStackTrace();
                        }
                        try {
                            dateActualDATA = formatodata.parse(sDateKey);
                        } catch (ParseException e7) {
                            e7.printStackTrace();
                        }
                        diff = dateAfterDATA.getTime() - dateActualDATA.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        db.insertPeriod(new Period(activeUID, activeUID, 0, sDateKey, 0, numDaysPeriodI, diffDAYSIntFINAL, numDaysFertileI, 0));
                        reinsertalarms(getActivity().getApplicationContext());
                        try {
                            dateBeforeDATA = formatodata.parse(dateBefore);
                        } catch (ParseException e8) {
                            e8.printStackTrace();
                        }
                        diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        db.updateCicloDays(activeUID, dateBefore, diffDAYSIntFINAL);
                    }
                    if (monthBefore <= 0 && monthAfter > 0) {
                        try {
                            dateAfterDATA = formatodata.parse(dateAfter);
                        } catch (ParseException e9) {
                            e9.printStackTrace();
                        }
                        try {
                            dateActualDATA = formatodata.parse(sDateKey);
                        } catch (ParseException e10) {
                            e10.printStackTrace();
                        }
                        diff = dateAfterDATA.getTime() - dateActualDATA.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        db.insertPeriod(new Period(activeUID, activeUID, 0, sDateKey, 0, numDaysPeriodI, diffDAYSIntFINAL, numDaysFertileI, 0));
                        reinsertalarms(getActivity().getApplicationContext());
                    }
                    if (monthBefore <= 0 && monthAfter <= 0) {
                        db.insertPeriod(new Period(activeUID, activeUID, 0, sDateKey, 0, numDaysPeriodI, numDaysCycleI, numDaysFertileI, 0));
                        reinsertalarms(getActivity().getApplicationContext());
                        return;
                    }
                    return;
                }
                db.deletePeriod(activeUID, sDateKey);
                reinsertalarms(getActivity().getApplicationContext());
            }
        });
        switchPeriodEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    dateActualDATA = formatodata.parse(sDateKey);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calTODAY = Calendar.getInstance();
                int i = calTODAY.get(6);
                int year = calTODAY.get(1);
                int month = calTODAY.get(2) + 1;
                int dayofmonth = calTODAY.get(5);
                int actualMaximum = calTODAY.getActualMaximum(5);
                int actualMinimum = calTODAY.getActualMinimum(5);
                try {
                    dateActualOGGI = formatodata.parse(year + "" + (month < 10 ? "0" + month : Integer.valueOf(month)) + "" + (dayofmonth < 10 ? "0" + dayofmonth : Integer.valueOf(dayofmonth)));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                try {
                    dateActualDATA = formatodata.parse(sDateKey);
                } catch (ParseException e3) {
                    e3.printStackTrace();
                }
                monthBefore = db.countBeforePeriodRow(activeUID, sDateKey);
                if (monthBefore > 0) {
                    dateBefore = db.selectBeforePeriodRow(activeUID, sDateKey);
                    try {
                        dateBeforeDATA = formatodata.parse(dateBefore);
                    } catch (ParseException e4) {
                        e4.printStackTrace();
                    }
                }
                if (dateActualDATA.compareTo(dateActualOGGI) <= 0) {
                    if (monthBefore > 0) {
                        diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                        db.updateMestruoDays(activeUID, dateBefore, diffDAYSIntFINAL + 1);
                    }
                    if (monthBefore == 0) {
                        diffDAYSIntFINAL = 0;
                        db.updateMestruoDays(activeUID, sDateKey, diffDAYSIntFINAL + 1);
                        return;
                    }
                    return;
                }
                new MaterialDialog.Builder(getActivity()).title((int) R.string.error_future_date_title).iconRes(R.mipmap.ic_error_black_48dp).content((int) R.string.error_future_date_mestruazione).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorAccent).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        switchPeriodEnd.setChecked(false);
                    }
                }).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                    }
                }).show();
            }
        });
        imageHeart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (paperYeah) {
                    rStart.setVisibility(View.VISIBLE);
                    rEnd.setVisibility(View.VISIBLE);
                    rIntercourse.setVisibility(View.VISIBLE);
                    imageHeart.setImageResource(R.drawable.img_allarga);
                } else {
                    rStart.setVisibility(View.GONE);
                    rEnd.setVisibility(View.GONE);
                    rIntercourse.setVisibility(View.GONE);
                    imageHeart.setImageResource(R.drawable.img_stringi);
                }
                if (paperYeah) {
                    paperYeah = false;
                } else {
                    paperYeah = true;
                }
            }
        });
        return view;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    public void initializePeriod() {
        idPer = selectedPeriod.getId();
        uid_per = selectedPeriod.getUid();
        type = selectedPeriod.getType();
        date = selectedPeriod.getDate();
        completo = selectedPeriod.getCompleto();
        daysmestruazioni = selectedPeriod.getDaysMestruazioni();
        daysciclo = selectedPeriod.getDaysCiclo();
        daysovulation = selectedPeriod.getDaysOvulation();
        pregnancy = selectedPeriod.getPregnancy();
    }

    public void initializeNote() {
        idNote = selectedNote.getId();
        uid_note = selectedNote.getUid();
        dateNote = selectedNote.getDate();
        note = selectedNote.getNotes();
        secretnote = selectedNote.getSecretNotes();
        symptoms = selectedNote.getSymptoms();
        mucus = selectedNote.getMucus();
        moods = selectedNote.getMoods();
        intimate = selectedNote.getIntimate();
        gommo = selectedNote.getGommo();
        sextimes = selectedNote.getSextimes();
        numorgasm = selectedNote.getNumorgasm();
        pill = selectedNote.getPill();
        ovulationtest = selectedNote.getOvulationtest();
        temperature = selectedNote.getTemperature();
        weight = selectedNote.getWeight();
        height = selectedNote.getHeight();
        seno = selectedNote.getSeno();
        vita = selectedNote.getVita();
        fianchi = selectedNote.getFianchi();
        systolic = selectedNote.getSystolic();
        diastolic = selectedNote.getDiastolic();
        pressure = selectedNote.getPressure();
        testgravidanza = selectedNote.getTestgravidanza();
    }

    public static String fillString(int count, char c) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void reinsertalarms(Context context) {
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
            manager.cancel(pendingIntent);
        }
        for (int i2 = 0; i2 < selCountRow; i2++) {
            intentKey = notificationsSelected[i2].getId();
            alarmIntent = new Intent(context.getApplicationContext(), EvaAlarmReceiver.class);
            alarmIntent.putExtra("tipo", String.valueOf(notificationsSelected[i2].getType()));
            alarmIntent.putExtra("giorniprima", String.valueOf(notificationsSelected[i2].getIdnotifications()));
            pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, 0);
            if (notificationsSelected[i2].getType() == 0) {
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
                if (notificationsSelected[i2].getIdnotifications() == 0) {
                    calendarM.add(5, daysciclo);
                } else if (notificationsSelected[i2].getIdnotifications() == 1) {
                    calendarM.add(5, daysciclo - 1);
                } else if (notificationsSelected[i2].getIdnotifications() == 2) {
                    calendarM.add(5, daysciclo - 2);
                } else if (notificationsSelected[i2].getIdnotifications() == 3) {
                    calendarM.add(5, daysciclo - 3);
                } else if (notificationsSelected[i2].getIdnotifications() == 4) {
                    calendarM.add(5, daysciclo - 4);
                } else if (notificationsSelected[i2].getIdnotifications() == 5) {
                    calendarM.add(5, daysciclo - 5);
                } else if (notificationsSelected[i2].getIdnotifications() == 6) {
                    calendarM.add(5, daysciclo - 6);
                } else if (notificationsSelected[i2].getIdnotifications() == 7) {
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
            } else if (notificationsSelected[i2].getType() == 1) {
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
                int idnotifications = notificationsSelected[i2].getIdnotifications();
                if (notificationsSelected[i2].getIdnotifications() == 0) {
                    calendarF.add(5, iniziofertilita);
                } else if (notificationsSelected[i2].getIdnotifications() == 1) {
                    calendarF.add(5, iniziofertilita - 1);
                } else if (notificationsSelected[i2].getIdnotifications() == 2) {
                    calendarF.add(5, iniziofertilita - 2);
                } else if (notificationsSelected[i2].getIdnotifications() == 3) {
                    calendarF.add(5, iniziofertilita - 3);
                } else if (notificationsSelected[i2].getIdnotifications() == 4) {
                    calendarF.add(5, iniziofertilita - 4);
                } else if (notificationsSelected[i2].getIdnotifications() == 5) {
                    calendarF.add(5, iniziofertilita - 5);
                } else if (notificationsSelected[i2].getIdnotifications() == 6) {
                    calendarF.add(5, iniziofertilita - 6);
                } else if (notificationsSelected[i2].getIdnotifications() == 7) {
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
            } else if (notificationsSelected[i2].getType() == 2) {
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
                if (notificationsSelected[i2].getIdnotifications() == 0) {
                    calendarO.add(5, giornoovulazione);
                } else if (notificationsSelected[i2].getIdnotifications() == 1) {
                    calendarO.add(5, giornoovulazione - 1);
                } else if (notificationsSelected[i2].getIdnotifications() == 2) {
                    calendarO.add(5, giornoovulazione - 2);
                } else if (notificationsSelected[i2].getIdnotifications() == 3) {
                    calendarO.add(5, giornoovulazione - 3);
                } else if (notificationsSelected[i2].getIdnotifications() == 4) {
                    calendarO.add(5, giornoovulazione - 4);
                } else if (notificationsSelected[i2].getIdnotifications() == 5) {
                    calendarO.add(5, giornoovulazione - 5);
                } else if (notificationsSelected[i2].getIdnotifications() == 6) {
                    calendarO.add(5, giornoovulazione - 6);
                } else if (notificationsSelected[i2].getIdnotifications() == 7) {
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
