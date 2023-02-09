package com.sammiegh.femcare.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.servise.EvaAlarmReceiver;
import com.sammiegh.femcare.utils.CustomTypefaceSpan;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int CHECK;
    int FINITE = 1;
    int GIORNI_PREGNANZA = 280;
    String IMAGEDEFAULT;
    int INIZIATE = 0;
    int NUMACCESSI_TH = 3;
    String NumDaysCycle;
    String NumDaysFertile;
    int NumDaysFertileI;
    String NumDaysPeriod;
    int NumDaysPeriodI;
    int STATUS;
    int activeUID;
    Intent alarmIntent;
    String answer;
    String answerLast;
    int avatar;
    Calendar calTODAY = Calendar.getInstance();
    int completo;
    int countDateBefore;
    String cycleL;
    String date;
    Date dateActualDATA;
    String dateBefore;
    Date dateBeforeDATA;
    String datePickStr;
    Date datePicker;
    int dayofmonth = calTODAY.get(5);
    int daysCiclo;
    int daysciclo;
    int daysmestruazioni;
    long daysokFINAL;
    int daysovulation;
    JCGSQLiteHelper db;
    MaterialDialog dialog;
    MaterialDialog dialogEND;
    MaterialDialog dialogSTART;
    long diff;
    int diffDAYSIntFINAL;
    EditText editCycleLenght;
    EditText edtPeriodLenght;
    String email;
    DateFormat formatodata;
    DateFormat formatodataView;
    int giornoovulazione;
    int id;
    int id_per;
    ImageView imageFertile1;
    ImageView imageFertile2;
    ImageView imageTopPreg;
    ImageButton imgBtnActionEND;
    ImageButton imgBtnActionSTART;
    String initLanguage;
    int iniziofertilita;
    int intentKey;
    String key;

    public AdView mAdView;
    AlarmManager manager;
    int month = (calTODAY.get(2) + 1);
    int monthBefore;
    int newUsed;
    TextView nextFERTILE;
    TextView nextMESTRUO;
    Notifications[] notificationsSelected;
    String numUsed;
    int numUsedI;
    Date oggiDate;
    String password;
    PendingIntent pendingIntent;
    String periodL;
    View positiveAction;
    View positiveActionEND;
    View positiveActionSTART;
    int pregnancy;
    String question;
    RelativeLayout relBtnActionEND;
    RelativeLayout relDATAFERTILE;
    RelativeLayout relDataNEXTRMESTRUO;
    RelativeLayout relDaysMAIN;
    RelativeLayout relLayActionEND;
    RelativeLayout relLayActionSTART;
    String sOggiDate;
    int selCountRow;
    Period selectedPeriod;
    Settings selectedSettings;
    User selectedUser;
    int sfondoFertilizzato = 0;
    SingleDateAndTimePicker singleDateAndTimePicker;
    SingleDateAndTimePicker singleDateAndTimePickerEND;
    SingleDateAndTimePicker singleDateAndTimePickerSTART;
    int status;
    Date tempDateOVULov1max;
    Date tempDateOVULov1min;
    Date tempDateOVULov2max;
    Date tempDateOVULov2min;
    int theme;
    int timehour;
    int timemin;
    String todayString;
    TextView txtActionEND;
    TextView txtActionSTART;
    TextView txtBtnClickEND;
    TextView txtBtnClickSTART;
    TextView txtData;
    TextView txtGiorniMAIN;
    TextView txtStartDate;
    TextView txtTestoMestruazioni;
    int type;
    int uid;
    int uid_per;
    String username;
    String value;
    int year = calTODAY.get(1);
    private BottomNavigationView bottomNavigation;
    public MainActivity() {
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
        IMAGEDEFAULT = "mood_default_name";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        String tempGiorniS;
        String tempGiorniS2;
        String tempGiorniS3;
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);

        bottomNavigation =  findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_calendar:
                        activeUID = db.readActiveUID();
                        db.updateSettings(new Settings(activeUID, activeUID, "olddate", sOggiDate));
                        db.updateSettings(new Settings(activeUID, activeUID, "tempdate", sOggiDate));
                        startActivity(new Intent(MainActivity.this, CustomCalendarActivity.class));
                        return true;

                    case R.id.menu_charts:
                        startActivity(new Intent(MainActivity.this, ChartsActivity.class));
                        return true;

                    case R.id.nav_more:
                        Intent openNote = new Intent(MainActivity.this, NoteActivity.class);
                        openNote.putExtra("datekey", sOggiDate);
                        startActivity(openNote);

                        return true;

                    case R.id.menu_note:
                        startActivity(new Intent(MainActivity.this, LogActivity.class));
                        return true;

                    case R.id.menu_setting:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));

                        return true;

                }
                return false;
            }
        });

        Typeface BillFont = Typeface.createFromAsset(getAssets(), getString(R.string.app_bar_font));
        String actionBarTitle = getString(R.string.app_name_toolbar);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(actionBarTitle);
        spannableStringBuilder.setSpan(new CustomTypefaceSpan("test", BillFont), 0, actionBarTitle.length(), 34);

        getSupportActionBar().setTitle((CharSequence) spannableStringBuilder);
        txtGiorniMAIN = (TextView) findViewById(R.id.txtGiorniMAIN);
        txtTestoMestruazioni = (TextView) findViewById(R.id.txtTestoMestruazioni);
        imgBtnActionSTART = (ImageButton) findViewById(R.id.imgBtnActionSTART);
        imgBtnActionEND = (ImageButton) findViewById(R.id.imgBtnActionEND);
        imageFertile1 = (ImageView) findViewById(R.id.imageFertile1);
        imageFertile2 = (ImageView) findViewById(R.id.imageFertile2);
        imageTopPreg = (ImageView) findViewById(R.id.imageTopPreg);
        txtData = (TextView) findViewById(R.id.txtData);
        nextMESTRUO = (TextView) findViewById(R.id.nextMESTRUO);
        nextFERTILE = (TextView) findViewById(R.id.nextFERTILE);
        txtActionSTART = (TextView) findViewById(R.id.txtActionSTART);
        txtActionEND = (TextView) findViewById(R.id.txtActionEND);
        txtBtnClickEND = (TextView) findViewById(R.id.txtBtnClickEND);
        txtBtnClickSTART = (TextView) findViewById(R.id.txtBtnClickSTART);
        relLayActionEND = (RelativeLayout) findViewById(R.id.relBtnActionEND);
        relLayActionSTART = (RelativeLayout) findViewById(R.id.relBtnActionSTART);
        relBtnActionEND = (RelativeLayout) findViewById(R.id.relBtnActionEND);
        relDATAFERTILE = (RelativeLayout) findViewById(R.id.relDATAFERTILE);
        relDaysMAIN = (RelativeLayout) findViewById(R.id.relDaysMAIN);
        relDataNEXTRMESTRUO = (RelativeLayout) findViewById(R.id.relDataNEXTRMESTRUO);
        oggiDate = new Date();
        sOggiDate = new SimpleDateFormat("yyyyMMdd").format(oggiDate);
        datePickStr = sOggiDate;
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        initLanguage = db.readKeySetting(activeUID, "locale");

        if (initLanguage.equals("en")) {
            formatodataView = DateFormat.getDateInstance(3, Locale.ENGLISH);
        } else if (initLanguage.equals("it")) {
            formatodataView = DateFormat.getDateInstance(3, Locale.ITALIAN);
        } else if (initLanguage.equals("fr")) {
            formatodataView = DateFormat.getDateInstance(3, Locale.FRENCH);
        } else if (initLanguage.equals("de")) {
            formatodataView = DateFormat.getDateInstance(3, Locale.GERMAN);
        } else {
            formatodataView = DateFormat.getDateInstance(3, Locale.getDefault());
        }
        selectedUser = db.readUser(activeUID);
        initializeUser();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        numUsed = db.readKeySetting(activeUID, "used");
        numUsedI = Integer.valueOf(numUsed).intValue();
        answerLast = db.readKeySetting(activeUID, "answeruse");
        newUsed = numUsedI + 1;
        db.updateSettings(new Settings(id, activeUID, "used", String.valueOf(newUsed)));
        CHECK = numUsedI % NUMACCESSI_TH;
        if (CHECK == 0 && answerLast.equals("L")) {
            dialog = new MaterialDialog.Builder(this).title((int) R.string.my_own_title).iconRes(R.mipmap.ic_img_org).content((int) R.string.my_own_message).onPositive(new MaterialDialog.SingleButtonCallback() {
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    db.updateSettings(new Settings(id, activeUID, "answeruse", "Y"));
                    newUsed = numUsedI + 1;
                    db.updateSettings(new Settings(id, activeUID, "used", String.valueOf(newUsed)));
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.androworld.evaperiodtracker")));
                }
            }).onNeutral(new MaterialDialog.SingleButtonCallback() {
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    db.updateSettings(new Settings(id, activeUID, "answeruse", "L"));
                    newUsed = numUsedI + 1;
                    db.updateSettings(new Settings(id, activeUID, "used", String.valueOf(newUsed)));
                }
            }).cancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    db.updateSettings(new Settings(id, activeUID, "answeruse", "N"));
                    newUsed = numUsedI + 1;
                    db.updateSettings(new Settings(id, activeUID, "used", String.valueOf(newUsed)));
                }
            }).positiveText((int) R.string.my_own_rate).negativeText((int) R.string.my_own_thanks).neutralText((int) R.string.my_own_cancel).positiveColorRes(R.color.colorPrimary).negativeColorRes(R.color.colorGrey).neutralColorRes(R.color.colorBluTiffanyDark).build();
            dialog.show();
        }
        db.updateSettings(new Settings(activeUID, activeUID, "olddate", sOggiDate));
        db.updateSettings(new Settings(activeUID, activeUID, "tempdate", sOggiDate));
        NumDaysCycle = db.readKeySetting(activeUID, "n_cycle_days");
        daysCiclo = Integer.parseInt(NumDaysCycle);
        NumDaysPeriod = db.readKeySetting(activeUID, "n_mestrual_days");
        NumDaysPeriodI = Integer.parseInt(NumDaysPeriod);
        NumDaysFertile = db.readKeySetting(activeUID, "n_ovulation_days");
        NumDaysFertileI = Integer.parseInt(NumDaysFertile);
        try {
            dateActualDATA = formatodata.parse(sOggiDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtData.setText(formatodataView.format(dateActualDATA));
        countDateBefore = db.countBeforePeriodRow(activeUID, sOggiDate);
        if (countDateBefore != 0) {
            dateBefore = db.selectBeforePeriodRowMain(activeUID, sOggiDate);
            selectedPeriod = db.readPeriod(activeUID, dateBefore);
            initializePeriod();
            if (pregnancy == 0) {
                if (completo == 0) {
                    STATUS = INIZIATE;
                } else {
                    STATUS = FINITE;
                }
                try {
                    dateBeforeDATA = formatodata.parse(dateBefore);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                diffDAYSIntFINAL = ((int) daysokFINAL) + 1;
                if (STATUS == INIZIATE) {
                    relLayActionEND.setVisibility(View.VISIBLE);
                    relLayActionSTART.setVisibility(View.GONE);
                    if (diffDAYSIntFINAL != daysmestruazioni) {
                        txtGiorniMAIN.setText(String.valueOf(getString(R.string.day2) + " " + diffDAYSIntFINAL));
                        txtTestoMestruazioni.setText(String.valueOf(getString(R.string.dialog_di) + " " + String.valueOf(getString(R.string.main_mestruazioni))));
                        relLayActionEND.setVisibility(View.VISIBLE);
                        relLayActionSTART.setVisibility(View.GONE);
                    } else {
                        if (diffDAYSIntFINAL == daysmestruazioni) {
                            txtGiorniMAIN.setText(getString(R.string.ultimo_giorno));
                            txtTestoMestruazioni.setText(String.valueOf(getString(R.string.dialog_di) + " " + String.valueOf(getString(R.string.main_mestruazioni))));
                            relLayActionEND.setVisibility(View.VISIBLE);
                            relLayActionSTART.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (STATUS == FINITE) {
                        relLayActionEND.setVisibility(View.GONE);
                        relLayActionSTART.setVisibility(View.VISIBLE);
                        if (diffDAYSIntFINAL <= daysciclo) {
                            int tempMancaGiorni = (daysciclo - diffDAYSIntFINAL) + 1;
                            if (tempMancaGiorni == 1) {
                                tempGiorniS3 = getString(R.string.day);
                            } else {
                                tempGiorniS3 = getString(R.string.days);
                            }
                            txtGiorniMAIN.setText(tempMancaGiorni + " " + tempGiorniS3);
                            txtTestoMestruazioni.setText(String.valueOf(getString(R.string.main_mestruazioni_nextp)));
                        } else {
                            if (diffDAYSIntFINAL > daysciclo) {
                                int ritardoGiorni = daysCiclo - diffDAYSIntFINAL;
                                if (ritardoGiorni == 1) {
                                    tempGiorniS2 = getString(R.string.day);
                                } else {
                                    tempGiorniS2 = getString(R.string.days);
                                }
                                txtGiorniMAIN.setText(Math.abs(ritardoGiorni) + " " + tempGiorniS2);
                                txtTestoMestruazioni.setText(String.valueOf(getString(R.string.main_late)));
                            }
                        }
                    }
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateBeforeDATA);
                cal.add(5, daysCiclo);
                nextMESTRUO.setText(String.valueOf(getString(R.string.main_next_mestruazioni) + " " + formatodataView.format(cal.getTime())));
                Calendar calOVULov1max = Calendar.getInstance();
                calOVULov1max.setTime(dateBeforeDATA);
                calOVULov1max.add(5, ((daysciclo - daysovulation) + 2) - 1);
                tempDateOVULov1max = calOVULov1max.getTime();
                String NEXTFertileOVULov1max = formatodata.format(tempDateOVULov1max);
                db.updateSettings(new Settings(id, activeUID, "ov1max", NEXTFertileOVULov1max));
                Calendar calOVULov1min = Calendar.getInstance();
                calOVULov1min.setTime(dateBeforeDATA);
                calOVULov1min.add(5, ((daysciclo - daysovulation) - 4) - 1);
                tempDateOVULov1min = calOVULov1min.getTime();
                String NEXTFertileOVULov1min = formatodata.format(tempDateOVULov1min);
                db.updateSettings(new Settings(id, activeUID, "ov1min", NEXTFertileOVULov1min));
                Calendar calOVULov2max = Calendar.getInstance();
                calOVULov2max.setTime(dateBeforeDATA);
                calOVULov2max.add(5, (((daysciclo - daysovulation) + 2) - daysCiclo) - 1);
                tempDateOVULov2max = calOVULov2max.getTime();
                String NEXTFertileOVULov2max = formatodata.format(tempDateOVULov2max);
                db.updateSettings(new Settings(id, activeUID, "ov2max", NEXTFertileOVULov2max));
                Calendar calOVULov2min = Calendar.getInstance();
                calOVULov2min.setTime(dateBeforeDATA);
                calOVULov2min.add(5, (((daysciclo - daysovulation) - 4) - daysCiclo) - 1);
                tempDateOVULov2min = calOVULov2min.getTime();
                String NEXTFertileOVULov2min = formatodata.format(tempDateOVULov2min);
                db.updateSettings(new Settings(id, activeUID, "ov2min", NEXTFertileOVULov2min));
                if ((dateActualDATA.after(tempDateOVULov1min) && dateActualDATA.before(tempDateOVULov1max)) || ((dateActualDATA.after(tempDateOVULov2min) && dateActualDATA.before(tempDateOVULov2max)) || dateActualDATA.equals(tempDateOVULov1min) || dateActualDATA.equals(tempDateOVULov1max) || dateActualDATA.equals(tempDateOVULov2min) || dateActualDATA.equals(tempDateOVULov2max))) {
                    Log.e("abcd", "450");
                    Log.e("abcd", String.valueOf(getString(R.string.cal_fertile)));
                    nextFERTILE.setText(String.valueOf(getString(R.string.cal_fertile)));
                    sfondoFertilizzato = 1;
                } else if (dateActualDATA.before(tempDateOVULov2min)) {
                    Log.e("abcd", "456");
                    Log.e("abcd", String.valueOf(getString(R.string.main_next_fertile) + " " + formatodataView.format(tempDateOVULov2min)));
                    nextFERTILE.setText(String.valueOf(getString(R.string.main_next_fertile) + " " + formatodataView.format(tempDateOVULov2min)));
                } else if (!dateActualDATA.after(tempDateOVULov2max) || !dateActualDATA.before(tempDateOVULov1min)) {
                    Calendar calOVULovAFTERmin = Calendar.getInstance();
                    calOVULovAFTERmin.setTime(dateBeforeDATA);
                    calOVULovAFTERmin.add(5, (((daysciclo + daysCiclo) - daysovulation) - 4) - 1);
                    Log.e("abcd", "456");
                    Log.e("abcd", String.valueOf(getString(R.string.main_next_fertile) + " " + formatodataView.format(calOVULovAFTERmin.getTime())));
                    nextFERTILE.setText(String.valueOf(getString(R.string.main_next_fertile) + " " + formatodataView.format(calOVULovAFTERmin.getTime())));
                } else {
                    Log.e("abcd", "456");
                    Log.e("abcd", String.valueOf(getString(R.string.main_next_fertile) + " " + formatodataView.format(tempDateOVULov2min)));
                    nextFERTILE.setText(String.valueOf(getString(R.string.main_next_fertile) + " " + formatodataView.format(tempDateOVULov1min)));
                }
            } else if (pregnancy == 1) {
                dateBefore = db.selectBeforePeriodRowMain(activeUID, sOggiDate);
                selectedPeriod = db.readPeriod(activeUID, dateBefore);
                initializePeriod();
                try {
                    dateBeforeDATA = formatodata.parse(dateBefore);
                } catch (ParseException e3) {
                    e3.printStackTrace();
                }
                imageTopPreg.setVisibility(View.VISIBLE);
                diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
                daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                diffDAYSIntFINAL = ((int) daysokFINAL) + 1;
                if (diffDAYSIntFINAL == 1) {
                    tempGiorniS = getString(R.string.day);
                } else {
                    tempGiorniS = getString(R.string.days);
                }
                int countdown2 = GIORNI_PREGNANZA - diffDAYSIntFINAL;
                txtGiorniMAIN.setTextColor(Color.parseColor("#" + getString(R.string.colorPreg)));
                txtTestoMestruazioni.setTextColor(Color.parseColor("#" + getString(R.string.colorPreg)));
                txtGiorniMAIN.setText(String.valueOf(countdown2 + " " + tempGiorniS));
                txtTestoMestruazioni.setText(getString(R.string.pregnancy_dialog_set_nascita));
                relLayActionEND.setVisibility(View.INVISIBLE);
                relLayActionSTART.setVisibility(View.INVISIBLE);
                relBtnActionEND.setVisibility(View.INVISIBLE);
                relDATAFERTILE.setVisibility(View.INVISIBLE);
                relDataNEXTRMESTRUO.setVisibility(View.INVISIBLE);
            }
        } else {
            Log.e("abcd", "492");
            txtGiorniMAIN.setText("");
            txtTestoMestruazioni.setText("");
            nextFERTILE.setText("");
            nextMESTRUO.setText("");
            relLayActionEND.setVisibility(View.GONE);
            relLayActionSTART.setVisibility(View.VISIBLE);
        }

        if (sfondoFertilizzato == 0) {
            imageFertile1.setVisibility(View.INVISIBLE);
            imageFertile2.setVisibility(View.INVISIBLE);

        } else {
            imageFertile1.setVisibility(View.VISIBLE);
            imageFertile2.setVisibility(View.VISIBLE);

        }


        if (db.readKeySetting(activeUID, "firstround").equals("1")) {
            dialog = new MaterialDialog.Builder(this).title((int) R.string.intro_welcome_title).iconRes(R.mipmap.ic_launcher).customView(R.layout.dialog_customview_launch, true)
                    .callback(new MaterialDialog.ButtonCallback() {
                        public void onPositive(MaterialDialog dialog) {
                            activeUID = db.readActiveUID();
                            periodL = edtPeriodLenght.getText().toString();
                            cycleL = editCycleLenght.getText().toString();
                            if (datePickStr.equals("") || diffDAYSIntFINAL < 0 || periodL.equals("") || cycleL.equals("")) {
                                STATUS = FINITE;
                            } else {
                                db.insertPeriod(new Period(activeUID, activeUID, 0, datePickStr, 0, Integer.parseInt(periodL), Integer.parseInt(cycleL), 14, 0));
                                REINSERTALARMS(getApplicationContext());
                                db.updateSettings(new Settings(id, activeUID, "n_cycle_days", cycleL));
                                db.updateSettings(new Settings(id, activeUID, "n_mestrual_days", periodL));
                                STATUS = INIZIATE;
                            }
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            finish();
                        }
                    }).cancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            STATUS = FINITE;
                        }
                    }).negativeText((int) R.string.dialog_cancel).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).build();
            positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
            View viewD2 = dialog.getCustomView();
            singleDateAndTimePicker = (SingleDateAndTimePicker) viewD2.findViewById(R.id.dateFirstMestruo);
            editCycleLenght = (EditText) viewD2.findViewById(R.id.edtCycleLenght);
            edtPeriodLenght = (EditText) viewD2.findViewById(R.id.edtPeriodLenght);
            singleDateAndTimePicker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
                public void onDateChanged(String displayed, Date date) {
                    datePicker = date;
                    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
                    datePickStr = formatodata.format(date);
                    diff = oggiDate.getTime() - datePicker.getTime();
                    daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    diffDAYSIntFINAL = (int) daysokFINAL;
                }
            });
            dialog.show();
            db.updateSettings(new Settings(id, activeUID, "firstround", "0"));
        }

        relDATAFERTILE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activeUID = db.readActiveUID();
                db.updateSettings(new Settings(activeUID, activeUID, "olddate", sOggiDate));
                db.updateSettings(new Settings(activeUID, activeUID, "tempdate", sOggiDate));
                startActivity(new Intent(MainActivity.this, CustomCalendarActivity.class));
            }
        });
        relDaysMAIN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activeUID = db.readActiveUID();
                db.updateSettings(new Settings(activeUID, activeUID, "olddate", sOggiDate));
                db.updateSettings(new Settings(activeUID, activeUID, "tempdate", sOggiDate));
                startActivity(new Intent(MainActivity.this, CustomCalendarActivity.class));
            }
        });
        relDataNEXTRMESTRUO.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activeUID = db.readActiveUID();
                db.updateSettings(new Settings(activeUID, activeUID, "olddate", sOggiDate));
                db.updateSettings(new Settings(activeUID, activeUID, "tempdate", sOggiDate));
                startActivity(new Intent(MainActivity.this, CustomCalendarActivity.class));
            }
        });
        imgBtnActionSTART.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogSTART = new MaterialDialog.Builder(MainActivity.this).title((int) R.string.dialog_main_start_date_title).iconRes(R.mipmap.ic_launcher).customView(R.layout.dialog_customview_start, true).callback(new MaterialDialog.ButtonCallback() {
                    public void onPositive(MaterialDialog dialog) {
                        if (!datePickStr.equals("") && diffDAYSIntFINAL >= 0) {
                            db.insertPeriod(new Period(activeUID, activeUID, 0, datePickStr, 0, NumDaysPeriodI, daysCiclo, NumDaysFertileI, 0));
                            REINSERTALARMS(getApplicationContext());
                        }
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    }
                }).negativeText((int) R.string.dialog_cancel).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).negativeColorRes(R.color.colorGrey).build();
                positiveActionSTART = dialogSTART.getActionButton(DialogAction.POSITIVE);
                View viewD = dialogSTART.getCustomView();
                singleDateAndTimePickerSTART = (SingleDateAndTimePicker) viewD.findViewById(R.id.dateStartMestruo);
                singleDateAndTimePickerSTART.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
                    public void onDateChanged(String displayed, Date date) {
                        datePicker = date;
                        DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
                        datePickStr = formatodata.format(date);
                        diff = oggiDate.getTime() - datePicker.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                    }
                });
                dialogSTART.show();
            }
        });
        imgBtnActionEND.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogEND = new MaterialDialog.Builder(MainActivity.this).title((int) R.string.dialog_main_end_date_title).iconRes(R.mipmap.ic_launcher).customView(R.layout.dialog_customview_end, true).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        if (!datePickStr.equals("") && diffDAYSIntFINAL >= 0) {
                            db.updateMestruoDays(activeUID, dateBefore, diffDAYSIntFINAL + 1);
                        }
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    }
                }).negativeText((int) R.string.dialog_cancel).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).negativeColorRes(R.color.colorGrey).build();
                positiveActionEND = dialogEND.getActionButton(DialogAction.POSITIVE);
                View viewD = dialogEND.getCustomView();
                String startDate = formatodataView.format(dateBeforeDATA);
                txtStartDate = (TextView) viewD.findViewById(R.id.txtStartDate);
                txtStartDate.setText(startDate);
                singleDateAndTimePickerEND = (SingleDateAndTimePicker) viewD.findViewById(R.id.dateEndMestruo);
                singleDateAndTimePickerEND.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
                    public void onDateChanged(String displayed, Date date) {
                        datePicker = date;
                        DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
                        datePickStr = formatodata.format(date);
                        diff = datePicker.getTime() - dateBeforeDATA.getTime();
                        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        diffDAYSIntFINAL = (int) daysokFINAL;
                    }
                });
                dialogEND.show();
            }
        });

    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    public void initializeUser() {
        id = selectedUser.getId();
        uid = selectedUser.getUid();
        status = selectedUser.getStatus();
        username = selectedUser.getUsername();
        password = selectedUser.getPassword();
        email = selectedUser.getEmail();
        question = selectedUser.getQuestion();
        answer = selectedUser.getAnswer();
        theme = selectedUser.getTheme();
        avatar = selectedUser.getAvatar();
    }

    public void initializePeriod() {
        id_per = selectedPeriod.getId();
        uid_per = selectedPeriod.getUid();
        type = selectedPeriod.getType();
        date = selectedPeriod.getDate();
        completo = selectedPeriod.getCompleto();
        daysmestruazioni = selectedPeriod.getDaysMestruazioni();
        daysciclo = selectedPeriod.getDaysCiclo();
        daysovulation = selectedPeriod.getDaysOvulation();
        pregnancy = selectedPeriod.getPregnancy();
    }

    public void onBackPressed() {

        showAlertDialogButtonClicked();
    }

    public void launchMarket() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

    public void showAlertDialogButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Exit");
        builder.setMessage((CharSequence) "Are You Sure You Want Exit?");
        builder.setPositiveButton((CharSequence) "Rate", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                launchMarket();
            }
        });
        builder.setNeutralButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent homeIntent = new Intent("android.intent.action.MAIN");
                homeIntent.addCategory("android.intent.category.HOME");
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        builder.setNegativeButton((CharSequence) "no", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void REINSERTALARMS(Context context) {
        manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        db = new JCGSQLiteHelper(context);
        activeUID = db.readActiveUID();
        selCountRow = db.countNotificationsSelected(activeUID);
        notificationsSelected = db.readUsedNotifications(activeUID);
        for (int i = 0; i < selCountRow; i++) {
            intentKey = notificationsSelected[i].getId();
            alarmIntent = new Intent(context.getApplicationContext(), EvaAlarmReceiver.class);
            alarmIntent.putExtra("tipo", String.valueOf(notificationsSelected[i].getType()));
            alarmIntent.putExtra("giorniprima", String.valueOf(notificationsSelected[i].getIdnotifications()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
            }else pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, 0);
            manager.cancel(pendingIntent);
        }
        for (int i2 = 0; i2 < selCountRow; i2++) {
            intentKey = notificationsSelected[i2].getId();
            alarmIntent = new Intent(context.getApplicationContext(), EvaAlarmReceiver.class);
            alarmIntent.putExtra("tipo", String.valueOf(notificationsSelected[i2].getType()));
            alarmIntent.putExtra("giorniprima", String.valueOf(notificationsSelected[i2].getIdnotifications()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
            }else pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), intentKey, alarmIntent, 0);
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
            }
            if (notificationsSelected[i2].getType() == 1) {
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
            }
            if (notificationsSelected[i2].getType() == 2) {
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
