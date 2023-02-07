package com.sammiegh.femcare.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

import java.util.Calendar;

public class NewAlarmActivity extends AppCompatActivity {
    int anInt = 0;
    int activeUID;
    Calendar calTODAY = Calendar.getInstance();
    Calendar dateAndTime = Calendar.getInstance();
    int dayofmonth = calTODAY.get(5);
    JCGSQLiteHelper db;
    EditText editTxtPromemoria;
    int frequency;
    int id;
    String key;
    int month = (calTODAY.get(2) + 1);
    ScrollableNumberPicker numberPickerHorizontalCustom;
    ScrollableNumberPicker numberPickerHorizontalMonth;
    RadioButton radioA1;
    RadioButton radioA2;
    RadioButton radioA3;
    RadioButton radioA4;
    RadioGroup radioGroupAlarm;
    RelativeLayout relTimeCustomlyInput;
    RelativeLayout relTimeDailyInput;
    RelativeLayout relTimeMonthlyInput;
    RelativeLayout relTimeWeeklyInput;
    String sTime;
    Settings selectedSettings;
    Spinner spinnerWeekly;
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(11, hourOfDay);
            dateAndTime.set(12, minute);
            timehour = hourOfDay;
            timemin = minute;
            updateLabel();
        }
    };
    TextView textTimeDaily;
    TextView textTimeDailyCustomly;
    TextView textTimeDailyMonthly;
    TextView textTimeWeekly;
    int thePosition;
    int timehour = 7;
    int timemin = 0;
    String todayString;
    int uid;
    String value;
    int year = calTODAY.get(1);

    public NewAlarmActivity() {
        todayString = year + "" + (month < 10 ? "0" + month : Integer.valueOf(month)) + "" + (dayofmonth < 10 ? "0" + dayofmonth : Integer.valueOf(dayofmonth));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        textTimeDaily = (TextView) findViewById(R.id.textTimeDaily);
        textTimeDailyMonthly = (TextView) findViewById(R.id.textTimeDailyMonthly);
        textTimeDailyCustomly = (TextView) findViewById(R.id.textTimeDailyCustomly);
        textTimeWeekly = (TextView) findViewById(R.id.textTimeWeekly);
        editTxtPromemoria = (EditText) findViewById(R.id.editTxtPromemoria);
        relTimeDailyInput = (RelativeLayout) findViewById(R.id.relTimeDailyInput);
        relTimeWeeklyInput = (RelativeLayout) findViewById(R.id.relTimeWeeklyInput);
        relTimeMonthlyInput = (RelativeLayout) findViewById(R.id.relTimeMonthlyInput);
        relTimeCustomlyInput = (RelativeLayout) findViewById(R.id.relTimeCustomlyInput);
        spinnerWeekly = (Spinner) findViewById(R.id.spinnerWeekly);
        numberPickerHorizontalMonth = (ScrollableNumberPicker) findViewById(R.id.number_picker_horizontalMonth);
        numberPickerHorizontalCustom = (ScrollableNumberPicker) findViewById(R.id.number_picker_horizontalCustom);
        thePosition = db.selectMAXNotification(activeUID);
        radioGroupAlarm = (RadioGroup) findViewById(R.id.radioAlarm);
        radioA1 = (RadioButton) radioGroupAlarm.findViewById(R.id.radioA1);
        radioA2 = (RadioButton) radioGroupAlarm.findViewById(R.id.radioA2);
        radioA3 = (RadioButton) radioGroupAlarm.findViewById(R.id.radioA3);
        radioA4 = (RadioButton) radioGroupAlarm.findViewById(R.id.radioA4);
        radioGroupAlarm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (radioA1.isChecked()) {
                    relTimeDailyInput.setVisibility(View.VISIBLE);
                    relTimeWeeklyInput.setVisibility(View.GONE);
                    relTimeMonthlyInput.setVisibility(View.GONE);
                    relTimeCustomlyInput.setVisibility(View.GONE);
                    anInt = 0;
                } else if (radioA2.isChecked()) {
                    relTimeDailyInput.setVisibility(View.GONE);
                    relTimeWeeklyInput.setVisibility(View.VISIBLE);
                    relTimeMonthlyInput.setVisibility(View.GONE);
                    relTimeCustomlyInput.setVisibility(View.GONE);
                    anInt = 1;
                } else if (radioA3.isChecked()) {
                    relTimeDailyInput.setVisibility(View.GONE);
                    relTimeWeeklyInput.setVisibility(View.GONE);
                    relTimeMonthlyInput.setVisibility(View.VISIBLE);
                    relTimeCustomlyInput.setVisibility(View.GONE);
                    anInt = 2;
                } else if (radioA4.isChecked()) {
                    relTimeDailyInput.setVisibility(View.GONE);
                    relTimeWeeklyInput.setVisibility(View.GONE);
                    relTimeMonthlyInput.setVisibility(View.GONE);
                    relTimeCustomlyInput.setVisibility(View.VISIBLE);
                    anInt = 3;
                }
            }
        });
        textTimeDaily.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(NewAlarmActivity.this, t, dateAndTime.get(11), dateAndTime.get(12), true).show();
            }
        });
        textTimeWeekly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(NewAlarmActivity.this, t, dateAndTime.get(11), dateAndTime.get(12), true).show();
            }
        });
        textTimeDailyMonthly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(NewAlarmActivity.this, t, dateAndTime.get(11), dateAndTime.get(12), true).show();
            }
        });
        textTimeDailyCustomly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(NewAlarmActivity.this, t, dateAndTime.get(11), dateAndTime.get(12), true).show();
            }
        });
        updateLabel();
    }


    public void updateLabel() {
        sTime = timehour + ":" + (timemin < 10 ? "0" + timemin : Integer.valueOf(timemin));
        textTimeDaily.setText(sTime);
        textTimeWeekly.setText(sTime);
        textTimeDailyMonthly.setText(sTime);
        textTimeDailyCustomly.setText(sTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_units, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        if (anInt == 0) {
            frequency = 1000;
        } else if (anInt == 1) {
            frequency = spinnerWeekly.getSelectedItemPosition() + 70;
        } else if (anInt == 2) {
            frequency = numberPickerHorizontalMonth.getValue();
        } else if (anInt == 3) {
            frequency = numberPickerHorizontalCustom.getValue() + 100;
        }
        int valType = thePosition + 1;
        db.insertNotifications(new Notifications(valType * 10, 0, valType, activeUID, editTxtPromemoria.getText().toString(), 0, 1, frequency, timehour, timemin, todayString));
        startActivity(new Intent(this, NotificationsActivity.class));
        finish();
        return true;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }
}
