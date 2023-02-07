package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.shawnlin.numberpicker.NumberPicker;

public class OvulationActivity extends AppCompatActivity {
    boolean changeTemp = false;
    JCGSQLiteHelper db;
    int id;
    String key;
    NumberPicker numberPicker;
    Settings selectedSettings;
    int uid;
    String value;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovulation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.ovulation_lenght));

        db = new JCGSQLiteHelper(getApplicationContext());
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        selectedSettings = db.readSettings(db.readActiveUID());
        initializeSettings();
        numberPicker.setValue(Integer.parseInt(db.readKeySetting(uid, "n_ovulation_days")));
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                changeTemp = true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cycle_period, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        selectedSettings = db.readSettings(db.readActiveUID());
        initializeSettings();
        db.updateSettings(new Settings(id, uid, "n_ovulation_days", String.valueOf(numberPicker.getValue())));
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        return true;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    @Override
    public void onBackPressed() {
        if (changeTemp) {
            new MaterialDialog.Builder(this).content((int) R.string.dialog_question_save).positiveText((int) R.string.dialog_save).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorGreen).negativeColorRes(R.color.colorGreen).cancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }).callback(new MaterialDialog.ButtonCallback() {
                @Override
                public void onPositive(MaterialDialog dialog) {
                    int activeUID = db.readActiveUID();
                    selectedSettings = db.readSettings(activeUID);
                    initializeSettings();
                    db.updateSettings(new Settings(id, uid, "n_ovulation_days", String.valueOf(numberPicker.getValue())));
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }).show();
        } else {
            finish();
        }
    }
}
