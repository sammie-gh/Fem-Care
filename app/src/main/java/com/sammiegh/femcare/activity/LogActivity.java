package com.sammiegh.femcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdView;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LogActivity extends AppCompatActivity {
    String NumDaysCycle;
    TextView aML;
    float aMLs;
    int activeUID;
    TextView avgLast12m;
    float avgLast12ms;
    TextView avgLast3m;
    float avgLast3ms;
    TextView avgLast6m;
    float avgLast6ms;
    TextView avgLastAll;
    TextView avgLastAllData;
    float avgLastAllDatas;
    int avgLastAlln;
    TextView avgLastOrgAll;
    int avgLastOrgAlln;
    TextView avgOrgLastMonth;
    TextView avgOrgLastMonthA;
    float avgOrgLastMonthAn;
    float avgOrgLastMonthn;
    TextView avgOrgLastWeek;
    TextView avgOrgLastWeekA;
    float avgOrgLastWeekAn;
    float avgOrgLastWeekn;
    TextView avgSexLastMonth;
    TextView avgSexLastMonthA;
    float avgSexLastMonthAn;
    float avgSexLastMonthn;
    TextView avgSexLastWeek;
    TextView avgSexLastWeekA;
    float avgSexLastWeekAn;
    float avgSexLastWeekn;
    TextView dFC;
    TextView dLC;
    TextView dN1;
    TextView dN2;
    TextView dN3;
    TextView dN4;
    TextView dP1;
    TextView dP2;
    TextView dP3;
    TextView dP4;
    TextView dSC;
    int dSCn;
    TextView dSL;
    int dSLn;
    String datePrimoMestruo = "";
    Date datePrimoMestruoDATA;
    String dateUltimoMestruo = "";
    Date dateUltimoMestruoDATA;
    String date_note;
    int daysCiclo;
    JCGSQLiteHelper db;
    int diastolic;
    float fianchi;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatodataView;
    int gommo;
    float height;
    int id;
    int id_note;
    String initLanguage;
    int intimate;
    String key;
    String[] lastFourPeriodOut = {"", "", "", ""};
    int[] lastFourPeriodOutGiorniMEstruo = {0, 0, 0, 0};

    AdView mAdView;
    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
    int pressure;
    TextView probOrg;
    float probOrgn;
    String sPrimoMestruo;
    String sUltimoMestruo;
    String secretnote;
    Note selectedNote;
    Settings selectedSettings;
    float seno;
    int sextimes;
    String symptoms;
    int systolic;
    Date tempDate;
    float temperature;
    int testgravidanza;
    int totRowNote;
    int totRowPeriod;
    TextView txtNextP1;
    TextView txtNextP2;
    TextView txtNextP3;
    TextView txtNextP4;
    TextView txtPrevP1;
    TextView txtPrevP2;
    TextView txtPrevP3;
    TextView txtPrevP4;
    int uid;
    int uid_note;
    String value;
    float vita;
    float weight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        String tempGiorniS;
        String tempGiorniS2;
        String tempGiorniS3;
        String tempGiorniS4;
        String tempGiorniS5;
        String tempGiorniS6;
        String tempGiorniS7;
        String tempGiorniS8;
        String tempGiorniS9;
        String tempGiorniS10;
        String tempGiorniS11;
        String tempGiorniS12;
        String tempGiorniS13;
        String tempGiorniS14;
        String tempGiorniS15;
        String tempGiorniS16;
        String tempGiorniS17;
        String tempGiorniS18;
        String tempGiorniS19;
        String tempGiorniS20;
        String tempGiorniS21;
        String tempGiorniS22;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.note_title));

        txtNextP1 = (TextView) findViewById(R.id.txtNextP1);
        txtNextP2 = (TextView) findViewById(R.id.txtNextP2);
        txtNextP3 = (TextView) findViewById(R.id.txtNextP3);
        txtNextP4 = (TextView) findViewById(R.id.txtNextP4);
        txtPrevP1 = (TextView) findViewById(R.id.txtPrevP1);
        txtPrevP2 = (TextView) findViewById(R.id.txtPrevP2);
        txtPrevP3 = (TextView) findViewById(R.id.txtPrevP3);
        txtPrevP4 = (TextView) findViewById(R.id.txtPrevP4);
        dP1 = (TextView) findViewById(R.id.dP1);
        dP2 = (TextView) findViewById(R.id.dP2);
        dP3 = (TextView) findViewById(R.id.dP3);
        dP4 = (TextView) findViewById(R.id.dP4);
        dN1 = (TextView) findViewById(R.id.dN1);
        dN2 = (TextView) findViewById(R.id.dN2);
        dN3 = (TextView) findViewById(R.id.dN3);
        dN4 = (TextView) findViewById(R.id.dN4);
        dFC = (TextView) findViewById(R.id.dFC);
        dLC = (TextView) findViewById(R.id.dLC);
        avgLast3m = (TextView) findViewById(R.id.avgLast3m);
        avgLast6m = (TextView) findViewById(R.id.avgLast6m);
        avgLast12m = (TextView) findViewById(R.id.avgLast12m);
        avgLastAllData = (TextView) findViewById(R.id.avgLastAllData);
        dSC = (TextView) findViewById(R.id.dSC);
        dSL = (TextView) findViewById(R.id.dSL);
        aML = (TextView) findViewById(R.id.aML);
        avgSexLastWeek = (TextView) findViewById(R.id.avgSexLastWeek);
        avgSexLastMonth = (TextView) findViewById(R.id.avgSexLastMonth);
        avgLastAll = (TextView) findViewById(R.id.avgLastAll);
        avgOrgLastWeek = (TextView) findViewById(R.id.avgOrgLastWeek);
        avgOrgLastMonth = (TextView) findViewById(R.id.avgOrgLastMonth);
        avgLastOrgAll = (TextView) findViewById(R.id.avgLastOrgAll);
        avgSexLastWeekA = (TextView) findViewById(R.id.avgSexLastWeekActive);
        avgSexLastMonthA = (TextView) findViewById(R.id.avgSexLastMonthActive);
        avgOrgLastWeekA = (TextView) findViewById(R.id.avgOrgLastWeekActive);
        avgOrgLastMonthA = (TextView) findViewById(R.id.avgOrgLastMonthActive);
        probOrg = (TextView) findViewById(R.id.probOrg);
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
        totRowPeriod = db.countRowsAssPeriod(activeUID);
        if (totRowPeriod > 0) {
            datePrimoMestruo = db.selectFirstStartPeriod(activeUID);
            dateUltimoMestruo = db.selectLastStartPeriod(activeUID);
            try {
                datePrimoMestruoDATA = formatodata.parse(datePrimoMestruo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                dateUltimoMestruoDATA = formatodata.parse(dateUltimoMestruo);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            sPrimoMestruo = formatodataView.format(datePrimoMestruoDATA);
            sUltimoMestruo = formatodataView.format(dateUltimoMestruoDATA);
            dFC.setText(sPrimoMestruo);
            dLC.setText(sUltimoMestruo);
            NumDaysCycle = db.readKeySetting(activeUID, "n_cycle_days");
            daysCiclo = Integer.parseInt(NumDaysCycle);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateUltimoMestruoDATA);
            cal.add(5, daysCiclo);
            String tempDatt1 = formatodataView.format(cal.getTime());
            cal.add(5, daysCiclo);
            String tempDatt2 = formatodataView.format(cal.getTime());
            cal.add(5, daysCiclo);
            String tempDatt3 = formatodataView.format(cal.getTime());
            cal.add(5, daysCiclo);
            String tempDatt4 = formatodataView.format(cal.getTime());
            txtNextP1.setText(tempDatt1);
            txtNextP2.setText(tempDatt2);
            txtNextP3.setText(tempDatt3);
            txtNextP4.setText(tempDatt4);
            if (daysCiclo == 1) {
                tempGiorniS15 = getString(R.string.day);
            } else {
                tempGiorniS15 = getString(R.string.days);
            }
            dP1.setText(String.valueOf(daysCiclo + " " + tempGiorniS15));
            dP2.setText(String.valueOf(daysCiclo + " " + tempGiorniS15));
            dP3.setText(String.valueOf(daysCiclo + " " + tempGiorniS15));
            dP4.setText(String.valueOf(daysCiclo + " " + tempGiorniS15));
            avgLast3ms = db.selectAvgFloatCycleTime(activeUID, 0);
            avgLast6ms = db.selectAvgFloatCycleTime(activeUID, 1);
            avgLast12ms = db.selectAvgFloatCycleTime(activeUID, 2);
            avgLastAllDatas = db.selectAvgFloatCycleTime(activeUID, 3);
            if (avgLast3ms == 1.0f) {
                tempGiorniS16 = getString(R.string.day);
            } else {
                tempGiorniS16 = getString(R.string.days);
            }
            avgLast3m.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgLast3ms)}) + " " + tempGiorniS16));
            if (avgLast6ms == 1.0f) {
                tempGiorniS17 = getString(R.string.day);
            } else {
                tempGiorniS17 = getString(R.string.days);
            }
            avgLast6m.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgLast6ms)}) + " " + tempGiorniS17));
            if (avgLast12ms == 1.0f) {
                tempGiorniS18 = getString(R.string.day);
            } else {
                tempGiorniS18 = getString(R.string.days);
            }
            avgLast12m.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgLast12ms)}) + " " + tempGiorniS18));
            if (avgLastAllDatas == 1.0f) {
                tempGiorniS19 = getString(R.string.day);
            } else {
                tempGiorniS19 = getString(R.string.days);
            }
            avgLastAllData.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgLastAllDatas)}) + " " + tempGiorniS19));
            dSCn = db.selectMinMaxStartPeriod(activeUID, 0);
            dSLn = db.selectMinMaxStartPeriod(activeUID, 1);
            if (dSCn == 1) {
                tempGiorniS20 = getString(R.string.day);
            } else {
                tempGiorniS20 = getString(R.string.days);
            }
            dSC.setText(String.valueOf(dSCn) + " " + tempGiorniS20);
            if (dSLn == 1) {
                tempGiorniS21 = getString(R.string.day);
            } else {
                tempGiorniS21 = getString(R.string.days);
            }
            dSL.setText(String.valueOf(dSLn) + " " + tempGiorniS21);
            aMLs = db.selectMax12AvgPeriodTime(activeUID);
            if (aMLs == 1.0f) {
                tempGiorniS22 = getString(R.string.day);
            } else {
                tempGiorniS22 = getString(R.string.days);
            }
            aML.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(aMLs)}) + " " + tempGiorniS22));
        }
        totRowNote = db.countRowsAssNote(activeUID);
        if (totRowNote > 0) {
            avgSexLastWeekn = db.avgRapportibyWeekLast12M(activeUID, 1);
            if (avgSexLastWeekn == 1.0f) {
                tempGiorniS5 = getString(R.string.volta);
            } else {
                tempGiorniS5 = getString(R.string.volte);
            }
            avgSexLastWeek.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgSexLastWeekn)})) + " " + tempGiorniS5);
            avgSexLastWeekAn = db.avgRapportibyWeekLast12M(activeUID, 0);
            if (avgSexLastWeekAn == 1.0f) {
                tempGiorniS6 = getString(R.string.volta);
            } else {
                tempGiorniS6 = getString(R.string.volte);
            }
            avgSexLastWeekA.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgSexLastWeekAn)})) + " " + tempGiorniS6);
            avgSexLastMonthn = db.avgRapportibyMonthLast12M(activeUID, 1);
            if (avgSexLastMonthn == 1.0f) {
                tempGiorniS7 = getString(R.string.volta);
            } else {
                tempGiorniS7 = getString(R.string.volte);
            }
            avgSexLastMonth.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgSexLastMonthn)})) + " " + tempGiorniS7);
            avgSexLastMonthAn = db.avgRapportibyWeekLast12M(activeUID, 0);
            if (avgSexLastMonthAn == 1.0f) {
                tempGiorniS8 = getString(R.string.volta);
            } else {
                tempGiorniS8 = getString(R.string.volte);
            }
            avgSexLastMonthA.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgSexLastMonthAn)})) + " " + tempGiorniS8);
            avgOrgLastWeekn = db.avgOrgasmibyWeekLast12M(activeUID, 1);
            if (avgOrgLastWeekn == 1.0f) {
                tempGiorniS9 = getString(R.string.volta);
            } else {
                tempGiorniS9 = getString(R.string.volte);
            }
            avgOrgLastWeek.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgOrgLastWeekn)})) + " " + tempGiorniS9);
            avgOrgLastWeekAn = db.avgOrgasmibyWeekLast12M(activeUID, 0);
            if (avgOrgLastWeekAn == 1.0f) {
                tempGiorniS10 = getString(R.string.volta);
            } else {
                tempGiorniS10 = getString(R.string.volte);
            }
            avgOrgLastWeekA.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgOrgLastWeekAn)})) + " " + tempGiorniS10);
            avgOrgLastMonthn = db.avgOrgasmibyMonthLast12M(activeUID, 1);
            if (avgOrgLastMonthn == 1.0f) {
                tempGiorniS11 = getString(R.string.volta);
            } else {
                tempGiorniS11 = getString(R.string.volte);
            }
            avgOrgLastMonth.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgOrgLastMonthn)})) + " " + tempGiorniS11);
            avgOrgLastMonthAn = db.avgOrgasmibyMonthLast12M(activeUID, 0);
            if (avgOrgLastMonthAn == 1.0f) {
                tempGiorniS12 = getString(R.string.volta);
            } else {
                tempGiorniS12 = getString(R.string.volte);
            }
            avgOrgLastMonthA.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(avgOrgLastMonthAn)})) + " " + tempGiorniS12);
            avgLastAlln = db.totRapportiLASTYEAR(activeUID);
            if (avgLastAlln == 1) {
                tempGiorniS13 = getString(R.string.volta);
            } else {
                tempGiorniS13 = getString(R.string.volte);
            }
            avgLastAll.setText(String.valueOf(avgLastAlln) + " " + tempGiorniS13);
            avgLastOrgAlln = db.totOrgasmiLASTYEAR(activeUID);
            if (avgLastOrgAlln == 1) {
                tempGiorniS14 = getString(R.string.volta);
            } else {
                tempGiorniS14 = getString(R.string.volte);
            }
            avgLastOrgAll.setText(String.valueOf(avgLastOrgAlln) + " " + tempGiorniS14);
            probOrgn = db.probabilitaORGASMO(activeUID);
            probOrg.setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(probOrgn)})) + "%");
        }
        lastFourPeriodOut = db.selectLastFourStartPeriod(activeUID);
        lastFourPeriodOutGiorniMEstruo = db.selectLastFourStartGiorniMestruo(activeUID);
        if (lastFourPeriodOutGiorniMEstruo[0] != 0) {
            txtPrevP1.setVisibility(View.VISIBLE);
            dN1.setVisibility(View.VISIBLE);
            try {
                tempDate = formatodata.parse(lastFourPeriodOut[0]);
            } catch (ParseException e3) {
                e3.printStackTrace();
            }
            String tempDatt = formatodataView.format(tempDate);
            if (lastFourPeriodOutGiorniMEstruo[0] == 1) {
                tempGiorniS4 = getString(R.string.day);
            } else {
                tempGiorniS4 = getString(R.string.days);
            }
            txtPrevP1.setText(tempDatt);
            dN1.setText(String.valueOf(lastFourPeriodOutGiorniMEstruo[0] + " " + tempGiorniS4));
        }
        if (lastFourPeriodOutGiorniMEstruo[1] != 0) {
            txtPrevP2.setVisibility(View.VISIBLE);
            dN2.setVisibility(View.VISIBLE);
            try {
                tempDate = formatodata.parse(lastFourPeriodOut[1]);
            } catch (ParseException e4) {
                e4.printStackTrace();
            }
            String tempDatt5 = formatodataView.format(tempDate);
            if (lastFourPeriodOutGiorniMEstruo[1] == 1) {
                tempGiorniS3 = getString(R.string.day);
            } else {
                tempGiorniS3 = getString(R.string.days);
            }
            txtPrevP2.setText(tempDatt5);
            dN2.setText(String.valueOf(lastFourPeriodOutGiorniMEstruo[1] + " " + tempGiorniS3));
        }
        if (lastFourPeriodOutGiorniMEstruo[2] != 0) {
            txtPrevP3.setVisibility(View.VISIBLE);
            dN3.setVisibility(View.VISIBLE);
            try {
                tempDate = formatodata.parse(lastFourPeriodOut[2]);
            } catch (ParseException e5) {
                e5.printStackTrace();
            }
            String tempDatt6 = formatodataView.format(tempDate);
            if (lastFourPeriodOutGiorniMEstruo[2] == 1) {
                tempGiorniS2 = getString(R.string.day);
            } else {
                tempGiorniS2 = getString(R.string.days);
            }
            txtPrevP3.setText(tempDatt6);
            dN3.setText(String.valueOf(lastFourPeriodOutGiorniMEstruo[2] + " " + tempGiorniS2));
        }
        if (lastFourPeriodOutGiorniMEstruo[3] != 0) {
            txtPrevP4.setVisibility(View.VISIBLE);
            dN4.setVisibility(View.VISIBLE);
            try {
                tempDate = formatodata.parse(lastFourPeriodOut[3]);
            } catch (ParseException e6) {
                e6.printStackTrace();
            }
            String tempDatt7 = formatodataView.format(tempDate);
            if (lastFourPeriodOutGiorniMEstruo[3] == 1) {
                tempGiorniS = getString(R.string.day);
            } else {
                tempGiorniS = getString(R.string.days);
            }
            txtPrevP4.setText(tempDatt7);
            dN4.setText(String.valueOf(lastFourPeriodOutGiorniMEstruo[3] + " " + tempGiorniS));
        }
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    public void initializeNote() {
        id_note = selectedNote.getId();
        uid_note = selectedNote.getUid();
        date_note = selectedNote.getDate();
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
}
