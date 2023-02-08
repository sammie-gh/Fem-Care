package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.shawnlin.numberpicker.NumberPicker;

public class PeriodLengthActivity extends AppCompatActivity {
    int defMaxPeriod = 7;
    int defMinPeriod = 2;
    int threshold = 1;
    int activeUID;
    float averagePeriod;
    boolean changeTemp = false;
    String[] choiceDialogArray;
    JCGSQLiteHelper db;
    int id;
    ImageView imgInfo;
    String key;
    int mesiInseriti;
    NumberPicker numberPicker;
    int roundedAvgPeriod;
    Settings selectedSettings;
    Switch switchCycleUseAverage;
    TextView txtDesUseAverage;
    int uid;
    String value;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_length);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.period_lenght));




        db = new JCGSQLiteHelper(getApplicationContext());
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        txtDesUseAverage = (TextView) findViewById(R.id.txtOptionDes);
        switchCycleUseAverage = (Switch) findViewById(R.id.CycleSwitch);
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        numberPicker.setValue(Integer.parseInt(db.readKeySetting(activeUID, "n_mestrual_days")));
        String mestrualDays = db.readKeySetting(activeUID, "default_mestrual_days");
        choiceDialogArray = getResources().getStringArray(R.array.itemdialogaveragecycle);
        if (mestrualDays.equals("DEFAULT")) {
            switchCycleUseAverage.setChecked(false);
        } else {
            switchCycleUseAverage.setChecked(true);
            if (mestrualDays.equals("3M")) {
                txtDesUseAverage.setText(choiceDialogArray[0]);
            }
            if (mestrualDays.equals("6M")) {
                txtDesUseAverage.setText(choiceDialogArray[1]);
            }
            if (mestrualDays.equals("12M")) {
                txtDesUseAverage.setText(choiceDialogArray[2]);
            }
            if (mestrualDays.equals("ALL")) {
                txtDesUseAverage.setText(choiceDialogArray[3]);
            }
            if (mestrualDays.equals("SMART")) {
                txtDesUseAverage.setText(choiceDialogArray[4]);
            }
        }
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                changeTemp = true;
                switchCycleUseAverage.setChecked(false);
            }
        });
        imgInfo = (ImageView) findViewById(R.id.imageInfo);
        imgInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(PeriodLengthActivity.this).title((int) R.string.dialog_info_avg_period).iconRes(R.mipmap.ic_launcher).content((int) R.string.dialog_cycle_info).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).show();
            }
        });
        switchCycleUseAverage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    new MaterialDialog.Builder(PeriodLengthActivity.this).title((int) R.string.cycle_use_average_title_dialog).items((int) R.array.itemdialogaveragecycle).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            String outcase;
                            String[] choiceDialog = getResources().getStringArray(R.array.itemdialogaveragecycle);
                            switch (which) {
                                case 0:
                                    outcase = "3M";
                                    break;
                                case 1:
                                    outcase = "6M";
                                    break;
                                case 2:
                                    outcase = "12M";
                                    break;
                                case 3:
                                    outcase = "ALL";
                                    break;
                                case 4:
                                    outcase = "SMART";
                                    break;
                                default:
                                    outcase = "DEFAULT";
                                    break;
                            }
                            selectedSettings = db.readSettings(activeUID);
                            initializeSettings();
                            db.updateSettings(new Settings(id, activeUID, "default_mestrual_days", outcase));
                            if (which == 0) {
                                averagePeriod = db.selectAvgPeriodTime(activeUID, 0);
                                roundedAvgPeriod = Math.round(averagePeriod);
                                db.updateSettings(new Settings(id, activeUID, "n_mestrual_days", String.valueOf(roundedAvgPeriod)));
                            }
                            if (which == 1) {
                                averagePeriod = db.selectAvgPeriodTime(activeUID, 1);
                                roundedAvgPeriod = Math.round(averagePeriod);
                                db.updateSettings(new Settings(id, activeUID, "n_mestrual_days", String.valueOf(roundedAvgPeriod)));
                            }
                            if (which == 2) {
                                averagePeriod = db.selectAvgPeriodTime(activeUID, 2);
                                roundedAvgPeriod = Math.round(averagePeriod);
                                db.updateSettings(new Settings(id, activeUID, "n_mestrual_days", String.valueOf(roundedAvgPeriod)));
                            }
                            if (which == 3) {
                                averagePeriod = db.selectAvgPeriodTime(activeUID, 3);
                                roundedAvgPeriod = Math.round(averagePeriod);
                                db.updateSettings(new Settings(id, activeUID, "n_mestrual_days", String.valueOf(roundedAvgPeriod)));
                            }
                            if (which == 4) {
                                mesiInseriti = db.countRowsAssPeriod(activeUID);
                                if (mesiInseriti <= 9) {
                                    averagePeriod = db.selectSmartAvgPeriodTime(activeUID, defMinPeriod, defMaxPeriod);
                                    roundedAvgPeriod = Math.round(averagePeriod);
                                } else {
                                    averagePeriod = db.selectSuperSmartAvgPeriodTime(activeUID, defMinPeriod, defMaxPeriod, threshold);
                                    roundedAvgPeriod = Math.round(averagePeriod);
                                }
                            }
                            numberPicker.setValue(roundedAvgPeriod);
                            txtDesUseAverage.setText(choiceDialog[which]);
                            return true;
                        }
                    }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).cancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            switchCycleUseAverage.setChecked(false);
                            selectedSettings = db.readSettings(activeUID);
                            initializeSettings();
                            db.updateSettings(new Settings(id, activeUID, "default_mestrual_days", "DEFAULT"));
                            txtDesUseAverage.setText("");
                        }
                    }).show();
                    return;
                }
                selectedSettings = db.readSettings(activeUID);
                initializeSettings();
                db.updateSettings(new Settings(id, activeUID, "default_mestrual_days", "DEFAULT"));
                txtDesUseAverage.setText("");
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
        db.updateSettings(new Settings(id, uid, "n_mestrual_days", String.valueOf(numberPicker.getValue())));
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
                    int activeUIDs = db.readActiveUID();
                    selectedSettings = db.readSettings(activeUIDs);
                    initializeSettings();
                    db.updateSettings(new Settings(id, uid, "n_mestrual_days", String.valueOf(numberPicker.getValue())));
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
