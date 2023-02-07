package com.sammiegh.femcare.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChartsActivity extends AppCompatActivity {
    float[] PesoSaveDEF;
    int TEMP = 1;
    float[] TempSaveDEF;
    int WEIGHT = 0;
    int activeUID;
    String answer;
    int[] arrayCicli;
    String[] arrayPeriodi;
    float[] arrayTemp;
    float[] arrayWeight;
    int avatar;
    BarChart chartMestrui;
    BarChart cycleChart;
    String date_note;
    JCGSQLiteHelper db;
    int diastolic;
    String email;
    float fianchi;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatodataView;
    int gommo;
    float height;
    int id;
    int id_note;
    ImageView imgCiclo;
    ImageView imgMestruo;
    ImageView imgNoCharts;
    ImageView imgTemp;
    ImageView imgWeight;
    String initHeightUnit;
    String initLanguage;
    String initTempUnit;
    String initWeightUnit;
    int intimate;
    String key;
    String moods;
    String mucus;
    String newStringT;
    String newStringW;
    String note;
    int numorgasm;
    int ovulationtest;
    String password;
    String pill;
    int pressure;
    String question;
    RelativeLayout relLayCycle;
    RelativeLayout relLayMestrui;
    RelativeLayout relLayNoCharts;
    RelativeLayout relLayTemp;
    RelativeLayout relLayWeight;
    int rowsNumDateNote;
    int rowsNumDatePeriod;
    String[] sArrayPeriodiFormat;
    String secretnote;
    Date[] selectedDATA;
    Note selectedNote;
    Settings selectedSettings;
    User selectedUser;
    float seno;
    int sextimes;
    int status;
    String symptoms;
    int systolic;
    LineChart tempChart;
    float temperature;
    int testgravidanza;
    TextView txtNoCharts;
    TextView typeChart;
    int uid;
    int uidNote;
    String username;
    String value;
    float vita;
    float weight;
    LineChart weightChart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        typeChart = (TextView) findViewById(R.id.typeChart);
        txtNoCharts = (TextView) findViewById(R.id.txtNoCharts);
        imgNoCharts = (ImageView) findViewById(R.id.imgNoCharts);
        imgCiclo = (ImageView) findViewById(R.id.imgCiclo);
        imgMestruo = (ImageView) findViewById(R.id.imgMestruo);
        imgWeight = (ImageView) findViewById(R.id.imgWeight);
        imgTemp = (ImageView) findViewById(R.id.imgTemp);
        relLayMestrui = (RelativeLayout) findViewById(R.id.relLayMestrui);
        relLayCycle = (RelativeLayout) findViewById(R.id.relLayCycle);
        relLayWeight = (RelativeLayout) findViewById(R.id.relLayWeight);
        relLayTemp = (RelativeLayout) findViewById(R.id.relLayTemp);
        relLayNoCharts = (RelativeLayout) findViewById(R.id.relLayNoCharts);
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
        initTempUnit = db.readKeySetting(uid, "temp_unit");
        initWeightUnit = db.readKeySetting(uid, "weight_unit");
        initHeightUnit = db.readKeySetting(uid, "height_unit");
        rowsNumDatePeriod = db.countRowsAssPeriod(activeUID);
        rowsNumDateNote = db.countRowsAssNote(activeUID);
        if (rowsNumDatePeriod < 2) {
            relLayCycle.setVisibility(View.GONE);
            relLayMestrui.setVisibility(View.GONE);
            relLayWeight.setVisibility(View.GONE);
            typeChart.setVisibility(View.GONE);
            relLayTemp.setVisibility(View.GONE);
            relLayNoCharts.setVisibility(View.VISIBLE);
            imgCiclo.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
            imgMestruo.setBackgroundColor(0);
            imgWeight.setBackgroundColor(0);
            imgTemp.setBackgroundColor(0);
        } else {
            relLayNoCharts.setVisibility(View.GONE);
            relLayCycle.setVisibility(View.VISIBLE);
            typeChart.setVisibility(View.VISIBLE);
            relLayMestrui.setVisibility(View.GONE);
            relLayWeight.setVisibility(View.GONE);
            relLayTemp.setVisibility(View.GONE);
            imgCiclo.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
            imgMestruo.setBackgroundColor(0);
            imgWeight.setBackgroundColor(0);
            imgTemp.setBackgroundColor(0);
            caricaCicli();
        }
        imgCiclo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (db.countRowsAssPeriodChart(activeUID) < 2) {
                    relLayCycle.setVisibility(View.GONE);
                    relLayMestrui.setVisibility(View.GONE);
                    relLayWeight.setVisibility(View.GONE);
                    typeChart.setVisibility(View.GONE);
                    relLayTemp.setVisibility(View.GONE);
                    relLayNoCharts.setVisibility(View.VISIBLE);
                    imgCiclo.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                    imgMestruo.setBackgroundColor(0);
                    imgWeight.setBackgroundColor(0);
                    imgTemp.setBackgroundColor(0);
                    return;
                }
                relLayNoCharts.setVisibility(View.GONE);
                relLayCycle.setVisibility(View.VISIBLE);
                typeChart.setVisibility(View.VISIBLE);
                relLayMestrui.setVisibility(View.GONE);
                relLayWeight.setVisibility(View.GONE);
                relLayTemp.setVisibility(View.GONE);
                imgCiclo.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                imgMestruo.setBackgroundColor(0);
                imgWeight.setBackgroundColor(0);
                imgTemp.setBackgroundColor(0);
                caricaCicli();
            }
        });
        imgMestruo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (db.countRowsAssPeriodChart(activeUID) < 2) {
                    relLayCycle.setVisibility(View.GONE);
                    relLayMestrui.setVisibility(View.GONE);
                    relLayWeight.setVisibility(View.GONE);
                    typeChart.setVisibility(View.GONE);
                    relLayTemp.setVisibility(View.GONE);
                    relLayNoCharts.setVisibility(View.VISIBLE);
                    imgMestruo.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                    imgCiclo.setBackgroundColor(0);
                    imgWeight.setBackgroundColor(0);
                    imgTemp.setBackgroundColor(0);
                    return;
                }
                relLayNoCharts.setVisibility(View.GONE);
                relLayCycle.setVisibility(View.GONE);
                typeChart.setVisibility(View.VISIBLE);
                relLayMestrui.setVisibility(View.VISIBLE);
                relLayWeight.setVisibility(View.GONE);
                relLayTemp.setVisibility(View.GONE);
                imgMestruo.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                imgCiclo.setBackgroundColor(0);
                imgWeight.setBackgroundColor(0);
                imgTemp.setBackgroundColor(0);
                caricaMestrui();
            }
        });
        imgWeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (db.countDateBuoneNota(activeUID, WEIGHT) < 2) {
                    relLayCycle.setVisibility(View.GONE);
                    relLayMestrui.setVisibility(View.GONE);
                    relLayWeight.setVisibility(View.GONE);
                    typeChart.setVisibility(View.GONE);
                    relLayTemp.setVisibility(View.GONE);
                    relLayNoCharts.setVisibility(View.VISIBLE);
                    imgMestruo.setBackgroundColor(0);
                    imgCiclo.setBackgroundColor(0);
                    imgWeight.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                    imgTemp.setBackgroundColor(0);
                    return;
                }
                relLayNoCharts.setVisibility(View.GONE);
                relLayCycle.setVisibility(View.GONE);
                relLayMestrui.setVisibility(View.GONE);
                relLayWeight.setVisibility(View.VISIBLE);
                typeChart.setVisibility(View.VISIBLE);
                relLayTemp.setVisibility(View.GONE);
                imgMestruo.setBackgroundColor(0);
                imgCiclo.setBackgroundColor(0);
                imgWeight.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                imgTemp.setBackgroundColor(0);
                caricaWeight();
            }
        });
        imgTemp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (db.countDateBuoneNota(activeUID, TEMP) < 2) {
                    relLayCycle.setVisibility(View.GONE);
                    relLayMestrui.setVisibility(View.GONE);
                    relLayWeight.setVisibility(View.GONE);
                    typeChart.setVisibility(View.GONE);
                    relLayTemp.setVisibility(View.GONE);
                    relLayNoCharts.setVisibility(View.VISIBLE);
                    imgMestruo.setBackgroundColor(0);
                    imgCiclo.setBackgroundColor(0);
                    imgWeight.setBackgroundColor(0);
                    imgTemp.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));
                    return;
                }
                relLayNoCharts.setVisibility(View.GONE);
                relLayCycle.setVisibility(View.GONE);
                relLayMestrui.setVisibility(View.GONE);
                relLayWeight.setVisibility(View.GONE);
                relLayTemp.setVisibility(View.VISIBLE);
                typeChart.setVisibility(View.VISIBLE);
                imgMestruo.setBackgroundColor(0);
                imgCiclo.setBackgroundColor(0);
                imgWeight.setBackgroundColor(0);
                imgTemp.setBackgroundColor(Color.parseColor("#" + getString(R.string.colorDisplay)));

                caricaTemp();
            }
        });


    }

    public void caricaCicli() {
        cycleChart = (BarChart) findViewById(R.id.chartCycle);
        cycleChart.setDrawBarShadow(false);
        cycleChart.setDrawValueAboveBar(true);
        cycleChart.getDescription().setEnabled(false);
        rowsNumDatePeriod = db.countRowsAssPeriodChart(activeUID);
        arrayPeriodi = new String[rowsNumDatePeriod];
        selectedDATA = new Date[rowsNumDatePeriod];
        sArrayPeriodiFormat = new String[rowsNumDatePeriod];
        arrayPeriodi = db.selectALLPeriodsCharts(activeUID);
        arrayCicli = db.selectALLCyclesCharts(activeUID);
        List<BarEntry> entries2 = new ArrayList<>();
        for (int l = 0; l < rowsNumDatePeriod; l++) {
            entries2.add(new BarEntry((float) l, (float) arrayCicli[l]));
            try {
                selectedDATA[l] = formatodata.parse(arrayPeriodi[l]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sArrayPeriodiFormat[l] = formatodataView.format(selectedDATA[l]);
        }
        BarDataSet set2 = new BarDataSet(entries2, getString(R.string.chart_cycles));
        set2.setColors(getResources().getColor(R.color.colorAccent));
        typeChart.setText(getString(R.string.chart_cycles));
        cycleChart.setData(new BarData(set2));
        cycleChart.setFitBars(true);
        cycleChart.invalidate();
        cycleChart.setScaleEnabled(true);
        cycleChart.setScaleXEnabled(true);
        cycleChart.setScaleYEnabled(true);
        cycleChart.setDoubleTapToZoomEnabled(true);
        Legend l2 = cycleChart.getLegend();
        l2.setFormSize(10.0f);
        l2.setForm(Legend.LegendForm.CIRCLE);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sArrayPeriodiFormat[(int) value];
            }
        };
        XAxis xAxis = cycleChart.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    public void caricaMestrui() {
        chartMestrui = (BarChart) findViewById(R.id.chartMestrui);
        chartMestrui.setDrawBarShadow(false);
        chartMestrui.setDrawValueAboveBar(true);
        chartMestrui.getDescription().setEnabled(false);
        rowsNumDatePeriod = db.countRowsAssPeriodChart(activeUID);
        arrayPeriodi = new String[rowsNumDatePeriod];
        selectedDATA = new Date[rowsNumDatePeriod];
        sArrayPeriodiFormat = new String[rowsNumDatePeriod];
        arrayPeriodi = db.selectALLPeriodsCharts(activeUID);
        arrayCicli = db.selectALLMestruiCharts(activeUID);
        List<BarEntry> entries2 = new ArrayList<>();
        for (int l = 0; l < rowsNumDatePeriod; l++) {
            entries2.add(new BarEntry((float) l, (float) arrayCicli[l]));
            try {
                selectedDATA[l] = formatodata.parse(arrayPeriodi[l]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sArrayPeriodiFormat[l] = formatodataView.format(selectedDATA[l]);
        }
        BarDataSet set2 = new BarDataSet(entries2, getString(R.string.period_lenght));
        set2.setColors(getResources().getColor(R.color.colorPink1));
        typeChart.setText(getString(R.string.chart_periods));
        chartMestrui.setData(new BarData(set2));
        chartMestrui.setFitBars(true);
        chartMestrui.invalidate();
        chartMestrui.setScaleEnabled(true);
        chartMestrui.setScaleXEnabled(true);
        chartMestrui.setScaleYEnabled(true);
        chartMestrui.setDoubleTapToZoomEnabled(true);
        Legend l2 = chartMestrui.getLegend();
        l2.setFormSize(10.0f);
        l2.setForm(Legend.LegendForm.CIRCLE);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sArrayPeriodiFormat[(int) value];
            }
        };
        XAxis xAxis = chartMestrui.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    public void caricaWeight() {
        weightChart = (LineChart) findViewById(R.id.chartWeight);
        weightChart.getDescription().setEnabled(false);
        rowsNumDatePeriod = db.countDateBuoneNota(activeUID, WEIGHT);
        arrayPeriodi = new String[rowsNumDatePeriod];
        selectedDATA = new Date[rowsNumDatePeriod];
        PesoSaveDEF = new float[rowsNumDatePeriod];
        sArrayPeriodiFormat = new String[rowsNumDatePeriod];
        arrayPeriodi = db.selectDateBuoneNota(activeUID, WEIGHT);
        arrayWeight = db.selectWeight(activeUID);
        List<Entry> entries2 = new ArrayList<>();
        for (int l = 0; l < rowsNumDatePeriod; l++) {
            if (initWeightUnit.equals("lb")) {
                PesoSaveDEF[l] = kgtolb(arrayWeight[l]);
            } else if (initWeightUnit.equals("stone")) {
                PesoSaveDEF[l] = kgtostone(arrayWeight[l]);
            } else {
                PesoSaveDEF[l] = arrayWeight[l];
            }
            entries2.add(new Entry((float) l, PesoSaveDEF[l]));
            try {
                selectedDATA[l] = formatodata.parse(arrayPeriodi[l]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sArrayPeriodiFormat[l] = formatodataView.format(selectedDATA[l]);
        }
        if (initWeightUnit.equals("kg")) {
            newStringW = getString(R.string.other_weight_title) + " (" + getString(R.string.units_weight_kg) + ")";
        }
        if (initWeightUnit.equals("lb")) {
            newStringW = getString(R.string.other_weight_title) + " (" + getString(R.string.units_weight_lb) + ")";
        }
        if (initWeightUnit.equals("stone")) {
            newStringW = getString(R.string.other_weight_title) + " (" + getString(R.string.units_weight_stone) + ")";
        }
        LineDataSet set2 = new LineDataSet(entries2, newStringW);
        set2.setColors(getResources().getColor(R.color.colorPink1));
        set2.setLineWidth(1.0f);
        set2.setCircleRadius(3.0f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(9.0f);
        set2.setDrawFilled(true);
        set2.setFormLineWidth(1.0f);
        typeChart.setText(getString(R.string.other_weight_title));
        weightChart.setData(new LineData(set2));
        weightChart.invalidate();
        weightChart.setScaleEnabled(true);
        weightChart.setScaleXEnabled(true);
        weightChart.setScaleYEnabled(true);
        weightChart.setDoubleTapToZoomEnabled(true);
        Legend l2 = weightChart.getLegend();
        l2.setFormSize(10.0f);
        l2.setForm(Legend.LegendForm.CIRCLE);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sArrayPeriodiFormat[(int) value];
            }
        };
        XAxis xAxis = weightChart.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    public void caricaTemp() {
        tempChart = (LineChart) findViewById(R.id.chartTemp);
        tempChart.getDescription().setEnabled(false);
        rowsNumDatePeriod = db.countDateBuoneNota(activeUID, TEMP);
        arrayPeriodi = new String[rowsNumDatePeriod];
        selectedDATA = new Date[rowsNumDatePeriod];
        TempSaveDEF = new float[rowsNumDatePeriod];
        sArrayPeriodiFormat = new String[rowsNumDatePeriod];
        arrayPeriodi = db.selectDateBuoneNota(activeUID, TEMP);
        arrayTemp = db.selectTemp(activeUID);
        List<Entry> entries2 = new ArrayList<>();
        for (int l = 0; l < rowsNumDatePeriod; l++) {
            if (initTempUnit.equals("F")) {
                TempSaveDEF[l] = fahrenheittocelsius(arrayTemp[l]);
            } else {
                TempSaveDEF[l] = arrayTemp[l];
            }
            entries2.add(new Entry((float) l, TempSaveDEF[l]));
            try {
                selectedDATA[l] = formatodata.parse(arrayPeriodi[l]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sArrayPeriodiFormat[l] = formatodataView.format(selectedDATA[l]);
        }
        if (initTempUnit.equals("C")) {
            newStringT = getString(R.string.other_temperature_title) + " (" + getString(R.string.units_temperature_C) + ")";
        } else {
            newStringT = getString(R.string.other_temperature_title) + " (" + getString(R.string.units_temperature_F) + ")";
        }
        LineDataSet set2 = new LineDataSet(entries2, newStringT);
        set2.setColors(getResources().getColor(R.color.colorPink1));
        set2.setColor(ViewCompat.MEASURED_STATE_MASK);
        set2.setCircleColor(-16711936);
        set2.setLineWidth(1.0f);
        set2.setCircleRadius(3.0f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(9.0f);
        set2.setDrawFilled(true);
        set2.setFormLineWidth(1.0f);
        typeChart.setText(getString(R.string.other_temperature_title));
        tempChart.setData(new LineData(set2));
        tempChart.invalidate();
        tempChart.setScaleEnabled(true);
        tempChart.setScaleXEnabled(true);
        tempChart.setScaleYEnabled(true);
        tempChart.setDoubleTapToZoomEnabled(true);
        Legend l2 = tempChart.getLegend();
        l2.setFormSize(10.0f);
        l2.setForm(Legend.LegendForm.CIRCLE);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sArrayPeriodiFormat[(int) value];
            }
        };
        XAxis xAxis = tempChart.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
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
        avatar = selectedUser.getAvatar();
    }

    public void initializeNote() {
        id_note = selectedNote.getId();
        uidNote = selectedNote.getUid();
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

    public static float fahrenheittocelsius(float input) {
        return ((input - 32.0f) * 5.0f) / 9.0f;
    }

    public static float kgtolb(float input) {
        return input * 2.2046f;
    }

    public static float kgtostone(float input) {
        return input * 0.157473f;
    }

    public static String fillString(int count, char c) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
