package com.sammiegh.femcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.R;

public class LegendaCalendarActivity extends AppCompatActivity {
    ImageView imgDisclaimer;
    ImageView imgInfoFertile;
    ImageView imgInfoGommo;
    ImageView imgInfoOvul;
    ImageView imgSpotting;
    TextView txtDisclaimer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legenda_calendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.cal_legenda));
        txtDisclaimer = (TextView) findViewById(R.id.txtOptionTitleDisclaimer);
        imgInfoFertile = (ImageView) findViewById(R.id.imageInfoFertile);
        imgInfoOvul = (ImageView) findViewById(R.id.imageInfoOvulation);
        imgSpotting = (ImageView) findViewById(R.id.imageInfoSpotting);
        imgDisclaimer = (ImageView) findViewById(R.id.imageInfoDisclaimer);
        imgInfoGommo = (ImageView) findViewById(R.id.imageInfoGommo);
        imgInfoFertile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(LegendaCalendarActivity.this).title((int) R.string.cal_fertile).iconRes(R.mipmap.ic_fertile).content((int) R.string.cal_note_disclaimer_fertile).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorBluTiffany).show();
            }
        });
        imgInfoOvul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(LegendaCalendarActivity.this).title((int) R.string.cal_ovulation).iconRes(R.mipmap.ic_cal_ovul).content((int) R.string.cal_note_disclaimer_ovulation).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorBluTiffany).show();
            }
        });
        imgSpotting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(LegendaCalendarActivity.this).title((int) R.string.cal_spotting).iconRes(R.mipmap.ic_cal_flow_3).content((int) R.string.cal_note_disclaimer_spotting).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorBluTiffany).show();
            }
        });
        imgInfoGommo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(LegendaCalendarActivity.this).title((int) R.string.cal_intimate_protected).iconRes(R.mipmap.ic_sex_gommo).content((int) R.string.cal_note_disclaimer_gommo).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorBluTiffany).show();
            }
        });
        txtDisclaimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(LegendaCalendarActivity.this).title((int) R.string.cal_disclaimer_title).iconRes(R.mipmap.ic_info_black_48dp).content((int) R.string.cal_disclaimer).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorBluTiffany).show();
            }
        });
        imgDisclaimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(LegendaCalendarActivity.this).title((int) R.string.cal_disclaimer_title).iconRes(R.mipmap.ic_info_black_48dp).content((int) R.string.cal_disclaimer).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorBluTiffany).show();
            }
        });
    }
}
