package com.sammiegh.femcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Period;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProgressActivity extends AppCompatActivity {
    String numdayscycle;
    String numdaysmestruo;
    String numdaysovul;
    int activeUID;
    int completo;
    String date;
    Date dateActualDATA;
    String dateBefore;
    Date dateBeforeDATA;
    int daysCiclo;
    int daysMestruo;
    int daysOvul;
    int daysciclo;
    int daysmestruazioni;
    long daysokFINAL;
    int daysovulation;
    JCGSQLiteHelper db;
    long diff;
    int diffDAYSIntFINAL;
    DateFormat formatodata = new SimpleDateFormat("yyyyMMdd");
    int id;
    int idPer;
    ImageView imgPreg;
    ImageView imgQA;
    String key;
    Date oggiDate;
    int pregnancy;
    RelativeLayout relLegenda;
    String sOggiDate;
    Period selectedPeriod;
    Settings selectedSettings;
    TextView textDes;
    TextView txtGiornoFuck;
    TextView txtGiornoFuckTOT;
    TextView txtTitleCharts;
    int type;
    int uid;
    int uidPer;
    String value;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtGiornoFuck = (TextView) findViewById(R.id.txtGiornoFuck);
        txtGiornoFuckTOT = (TextView) findViewById(R.id.txtCiclo);
        relLegenda = (RelativeLayout) findViewById(R.id.relLegenda);
        textDes = (TextView) findViewById(R.id.textDes);
        txtTitleCharts = (TextView) findViewById(R.id.txtTitleCharts);
        imgQA = (ImageView) findViewById(R.id.imgQA);
        imgPreg = (ImageView) findViewById(R.id.imgPreg);
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        oggiDate = new Date();
        sOggiDate = new SimpleDateFormat("yyyyMMdd").format(oggiDate);
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        numdayscycle = db.readKeySetting(activeUID, "n_cycle_days");
        daysCiclo = Integer.parseInt(numdayscycle);
        numdaysmestruo = db.readKeySetting(activeUID, "n_mestrual_days");
        daysMestruo = Integer.parseInt(numdaysmestruo);
        numdaysovul = db.readKeySetting(activeUID, "n_ovulation_days");
        daysOvul = Integer.parseInt(numdaysovul);
        dateBefore = db.selectBeforePeriodRow(activeUID, sOggiDate);
        selectedPeriod = db.readPeriod(activeUID, dateBefore);
        initializePeriod();
        try {
            dateBeforeDATA = formatodata.parse(dateBefore);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dateActualDATA = formatodata.parse(sOggiDate);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        diff = dateActualDATA.getTime() - dateBeforeDATA.getTime();
        daysokFINAL = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        diffDAYSIntFINAL = ((int) daysokFINAL) + 1;
        if (pregnancy == 1) {
            txtTitleCharts.setText(getString(R.string.pregnancy_title));
            textDes.setVisibility(View.INVISIBLE);
            relLegenda.setVisibility(View.INVISIBLE);
            txtGiornoFuck.setText(String.valueOf(diffDAYSIntFINAL));
            txtGiornoFuckTOT.setText(getString(R.string.dialog_main_ciclo_of) + " " + daysciclo);
            imgPreg.setVisibility(View.VISIBLE);
            WheelIndicatorView wheelIndicatorViewGiorni = (WheelIndicatorView) findViewById(R.id.wheelIndicatorViewGiorni);
            wheelIndicatorViewGiorni.setFilledPercent(Math.round((((float) diffDAYSIntFINAL) / ((float) daysciclo)) * 100.0f));
            wheelIndicatorViewGiorni.addWheelIndicatorItem(new WheelIndicatorItem((float) daysCiclo, getResources().getColor(R.color.colorBluTiffany)));
        } else {
            txtGiornoFuck.setText(String.valueOf(diffDAYSIntFINAL));
            txtGiornoFuckTOT.setText(getString(R.string.dialog_main_ciclo_of) + " " + daysCiclo);
            WheelIndicatorView wheelIndicatorView = (WheelIndicatorView) findViewById(R.id.wheel_indicator_view);
            WheelIndicatorView wheelIndicatorViewGiorni2 = (WheelIndicatorView) findViewById(R.id.wheelIndicatorViewGiorni);
            wheelIndicatorView.setFilledPercent(100);
            int inizioFertile = (daysCiclo - daysOvul) - 4;
            int fineFertile = (daysCiclo - daysOvul) + 2;
            int deltaCicloFertile = daysCiclo - fineFertile;
            int deltainizioFMestruo = inizioFertile - daysMestruo;
            WheelIndicatorItem wheelIndicatorItem = new WheelIndicatorItem((float) daysMestruo, getResources().getColor(R.color.colorPulse));
            WheelIndicatorItem inizioFertileIndicatorItem = new WheelIndicatorItem((float) deltainizioFMestruo, getResources().getColor(R.color.colorAccent));
            WheelIndicatorItem mestruoIndicatorItem = new WheelIndicatorItem((float) (fineFertile - inizioFertile), getResources().getColor(R.color.colorGrey));
            WheelIndicatorItem fineFertileIndicatorItem = new WheelIndicatorItem((float) deltaCicloFertile, getResources().getColor(R.color.colorGrey));
            wheelIndicatorView.addWheelIndicatorItem(wheelIndicatorItem);
            wheelIndicatorView.addWheelIndicatorItem(mestruoIndicatorItem);
            wheelIndicatorView.addWheelIndicatorItem(inizioFertileIndicatorItem);
            wheelIndicatorView.addWheelIndicatorItem(fineFertileIndicatorItem);
            wheelIndicatorViewGiorni2.setFilledPercent(Math.round((((float) diffDAYSIntFINAL) / ((float) daysCiclo)) * 100.0f));
            wheelIndicatorViewGiorni2.addWheelIndicatorItem(new WheelIndicatorItem((float) daysCiclo, getResources().getColor(R.color.colorBluTiffany)));
            wheelIndicatorView.startItemsAnimation();
            wheelIndicatorViewGiorni2.startItemsAnimation();
        }
        imgQA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ProgressActivity.this, CycleWikiActivity.class));
            }
        });
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
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
