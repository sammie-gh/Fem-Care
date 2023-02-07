package com.sammiegh.femcare.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PregnancyActivity extends AppCompatActivity {
    int giorniPregnanza = 280;
    String numdayscycle;
    int activeUID;
    int completo;
    int completoPregnant;
    int countDateBefore;
    String date;
    String dateBefore;
    Date dateBeforeDATA;

    int day;
    int daysCiclo;
    int daysciclo;
    int daysmestruazioni;
    int daysovulation;
    JCGSQLiteHelper db;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatodataView;
    int idPer;
    String initLanguage;
    LinearLayout linLayPregnant;

    int month;
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            Calendar cal = Calendar.getInstance();
            cal.set(1, arg1);
            cal.set(2, arg2);
            cal.set(5, arg3);
            txtDeliveryDate.setText(formatodataView.format(cal.getTime()));
            cal.add(5, -giorniPregnanza);
            db.insertPeriod(new Period(idPer, uidPer, type, formatodata.format(cal.getTime()), 1, daysmestruazioni, giorniPregnanza, daysovulation, 1));
            db.deletePeriod(activeUID, date);
        }
    };
    Date oggiDate;
    int outcase;
    int pregnancy;
    RelativeLayout relativeLayoutDatePreg;
    String sOggiDate;
    Period selectedPeriod;
    Switch switchPin;
    TextView textDeliveryDateTitle;
    TextView textDesPregnant;
    int tipoChiusura;
    TextView txtDeliveryDate;
    int type;
    int typePregnant;
    int uidPer;

    public int year;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnancy);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pregnancy");

        switchPin = (Switch) findViewById(R.id.PinSwitchPregnant);
        relativeLayoutDatePreg = (RelativeLayout) findViewById(R.id.relativeLayoutDatePreg);
        textDesPregnant = (TextView) findViewById(R.id.textDesPregnant);
        txtDeliveryDate = (TextView) findViewById(R.id.txtDeliveryDate);
        textDeliveryDateTitle = (TextView) findViewById(R.id.textDeliveryDateTitle);
        linLayPregnant = (LinearLayout) findViewById(R.id.linLayPregnant);
        oggiDate = new Date();
        sOggiDate = new SimpleDateFormat("yyyyMMdd").format(oggiDate);
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
        countDateBefore = db.countBeforePeriodRow(activeUID, sOggiDate);
        if (countDateBefore != 0) {
            dateBefore = db.selectBeforePeriodRowMain(activeUID, sOggiDate);
            selectedPeriod = db.readPeriod(activeUID, dateBefore);
            initializePeriod();
            try {
                dateBeforeDATA = formatodata.parse(dateBefore);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (pregnancy == 1) {
                switchPin.setChecked(true);
                relativeLayoutDatePreg.setVisibility(View.VISIBLE);
                textDesPregnant.setVisibility(View.VISIBLE);
                Calendar calinit = Calendar.getInstance();
                calinit.setTime(dateBeforeDATA);
                calinit.add(5, giorniPregnanza - 1);
                day = calinit.get(5);
                month = calinit.get(2);
                year = calinit.get(1);
                txtDeliveryDate.setText(formatodataView.format(calinit.getTime()));
            } else {
                switchPin.setChecked(false);
                relativeLayoutDatePreg.setVisibility(View.INVISIBLE);
                textDesPregnant.setVisibility(View.INVISIBLE);
            }
        }
        switchPin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    relativeLayoutDatePreg.setVisibility(View.VISIBLE);
                    textDesPregnant.setVisibility(View.VISIBLE);
                    countDateBefore = db.countBeforePeriodRow(activeUID, sOggiDate);
                    if (countDateBefore != 0) {
                        dateBefore = db.selectBeforePeriodRowMain(activeUID, sOggiDate);
                        selectedPeriod = db.readPeriod(activeUID, dateBefore);
                        initializePeriod();
                        try {
                            dateBeforeDATA = formatodata.parse(dateBefore);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateBeforeDATA);
                        cal.add(5, giorniPregnanza - 1);
                        String dataPartoFinale = formatodataView.format(cal.getTime());
                        day = cal.get(5);
                        month = cal.get(2);
                        year = cal.get(1);
                        txtDeliveryDate.setText(dataPartoFinale);
                        completoPregnant = 0;
                        typePregnant = 1;
                        db.updatePeriod(new Period(idPer, uidPer, type, dateBefore, completoPregnant, daysmestruazioni, giorniPregnanza, daysovulation, typePregnant));
                        new MaterialDialog.Builder(PregnancyActivity.this).title((int) R.string.pregnancy_title).iconRes(R.mipmap.ic_launcher).content((int) R.string.pregnancy_dialog_title_congrat).positiveText((int) R.string.pregnancy_dialog_continue).positiveColorRes(R.color.colorPrimary).show();
                    }
                    return;
                }
                relativeLayoutDatePreg.setVisibility(View.INVISIBLE);
                textDesPregnant.setVisibility(View.INVISIBLE);
                new MaterialDialog.Builder(PregnancyActivity.this).title((int) R.string.pregnancy_dialog_title).items((int) R.array.itemStopPregnancy).iconRes(R.mipmap.ic_last_page_black_48dp).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        switch (which) {
                            case 0:
                                outcase = 1;
                                break;
                            case 1:
                                outcase = 2;
                                break;
                        }
                        numdayscycle = db.readKeySetting(activeUID, "n_cycle_days");
                        daysCiclo = Integer.parseInt(numdayscycle);
                        countDateBefore = db.countBeforePeriodRow(activeUID, sOggiDate);
                        if (countDateBefore != 0) {
                            dateBefore = db.selectBeforePeriodRowMain(activeUID, sOggiDate);
                            selectedPeriod = db.readPeriod(activeUID, dateBefore);
                            initializePeriod();
                            try {
                                dateBeforeDATA = formatodata.parse(dateBefore);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        typePregnant = 0;
                        if (outcase == 1) {
                            tipoChiusura = 1;
                            completoPregnant = 1;
                            db.updatePeriod(new Period(idPer, uidPer, tipoChiusura, dateBefore, completoPregnant, daysmestruazioni, giorniPregnanza, daysovulation, typePregnant));
                            return true;
                        } else if (outcase != 2) {
                            return true;
                        } else {
                            tipoChiusura = 2;
                            completoPregnant = 0;
                            db.updatePeriod(new Period(idPer, uidPer, tipoChiusura, dateBefore, completoPregnant, daysmestruazioni, daysCiclo, daysovulation, typePregnant));
                            return true;
                        }
                    }
                }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).negativeColorRes(R.color.colorGrey).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        relativeLayoutDatePreg.setVisibility(View.VISIBLE);
                        textDesPregnant.setVisibility(View.VISIBLE);
                        switchPin.setChecked(true);
                    }
                }).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    public Dialog onCreateDialog(int id) {
        if (id != 999) {
            return null;
        }
        return new DatePickerDialog(this, myDateListener, year, month, day);
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
