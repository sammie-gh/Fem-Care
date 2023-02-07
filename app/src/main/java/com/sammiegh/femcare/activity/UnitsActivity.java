package com.sammiegh.femcare.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class UnitsActivity extends AppCompatActivity {
    RadioButton checkedRadioButtonH1;
    RadioButton checkedRadioButtonH2;
    RadioButton checkedRadioButtonH3;
    RadioButton checkedRadioButtonT1;
    RadioButton checkedRadioButtonT2;
    RadioButton checkedRadioButtonW1;
    RadioButton checkedRadioButtonW2;
    RadioButton checkedRadioButtonW3;
    JCGSQLiteHelper db;
    String heightSelected;
    int id;
    String key;
    RadioGroup radioGroupHeight;
    RadioGroup radioGroupTemp;
    RadioGroup radioGroupWeight;
    Settings selectedSettings;
    String tempSelected;
    int uid;
    String value;
    String weightSelected;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.units_title));

        db = new JCGSQLiteHelper(getApplicationContext());
        selectedSettings = db.readSettings(db.readActiveUID());
        initializeSettings();
        String initTempUnit = db.readKeySetting(uid, "temp_unit");
        String initWeightUnit = db.readKeySetting(uid, "weight_unit");
        String initHeightUnit = db.readKeySetting(uid, "height_unit");
        radioGroupTemp = (RadioGroup) findViewById(R.id.radioTemperature);
        checkedRadioButtonT1 = (RadioButton) radioGroupTemp.findViewById(R.id.radioC);
        checkedRadioButtonT2 = (RadioButton) radioGroupTemp.findViewById(R.id.radioF);
        radioGroupWeight = (RadioGroup) findViewById(R.id.radioWeight);
        checkedRadioButtonW1 = (RadioButton) radioGroupWeight.findViewById(R.id.radioW1);
        checkedRadioButtonW2 = (RadioButton) radioGroupWeight.findViewById(R.id.radioW2);
        checkedRadioButtonW3 = (RadioButton) radioGroupWeight.findViewById(R.id.radioW3);
        radioGroupHeight = (RadioGroup) findViewById(R.id.radioHeight);
        checkedRadioButtonH1 = (RadioButton) radioGroupHeight.findViewById(R.id.radioH1);
        checkedRadioButtonH2 = (RadioButton) radioGroupHeight.findViewById(R.id.radioH2);
        checkedRadioButtonH3 = (RadioButton) radioGroupHeight.findViewById(R.id.radioH3);
        if (initTempUnit.equals("C")) {
            checkedRadioButtonT1.setChecked(true);
        } else {
            checkedRadioButtonT2.setChecked(true);
        }
        if (initWeightUnit.equals("kg")) {
            checkedRadioButtonW1.setChecked(true);
        }
        if (initWeightUnit.equals("lb")) {
            checkedRadioButtonW2.setChecked(true);
        }
        if (initWeightUnit.equals("stone")) {
            checkedRadioButtonW3.setChecked(true);
        }
        if (initHeightUnit.equals("cm")) {
            checkedRadioButtonH1.setChecked(true);
        }
        if (initHeightUnit.equals("m")) {
            checkedRadioButtonH2.setChecked(true);
        }
        if (initHeightUnit.equals("inch")) {
            checkedRadioButtonH3.setChecked(true);
        }
        radioGroupTemp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (checkedRadioButtonT1.isChecked()) {
                    tempSelected = "C";
                } else {
                    tempSelected = "F";
                }
                int activeUID = db.readActiveUID();
                selectedSettings = db.readSettings(activeUID);
                initializeSettings();
                db.updateSettings(new Settings(id, uid, "temp_unit", tempSelected));
            }
        });
        radioGroupWeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (checkedRadioButtonW1.isChecked()) {
                    weightSelected = "kg";
                }
                if (checkedRadioButtonW2.isChecked()) {
                    weightSelected = "lb";
                }
                if (checkedRadioButtonW3.isChecked()) {
                    weightSelected = "stone";
                }
                int activeUID = db.readActiveUID();
                selectedSettings = db.readSettings(activeUID);
                initializeSettings();
                db.updateSettings(new Settings(id, uid, "weight_unit", weightSelected));
            }
        });
        radioGroupHeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (checkedRadioButtonH1.isChecked()) {
                    heightSelected = "cm";
                }
                if (checkedRadioButtonH2.isChecked()) {
                    heightSelected = "m";
                }
                if (checkedRadioButtonH3.isChecked()) {
                    heightSelected = "inch";
                }
                int activeUID = db.readActiveUID();
                selectedSettings = db.readSettings(activeUID);
                initializeSettings();
                db.updateSettings(new Settings(id, uid, "height_unit", heightSelected));
            }
        });
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
