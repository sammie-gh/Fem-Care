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
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.shawnlin.numberpicker.NumberPicker;

public class CycleLenghtActivity extends AppCompatActivity {
    int defaultcycledays = 28;
    int defMaxCycle = 35;
    int defMinCycle = 23;
    int threshold = 4;
    int activeUID;
    float averageCycle;
    boolean changeTemp = false;
    String[] choiceDialogArray;
    JCGSQLiteHelper db;
    int id;
    ImageView imgInfo;
    String key;
    int mesiInseriti;
    NumberPicker numberPicker;
    int roundedAvgCycle;
    Settings selectedSettings;
    Switch switchCycleUseAverage;
    TextView txtDesUseAverage;
    int uid;
    String value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_lenght);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.cycle_lenght));
        db = new JCGSQLiteHelper(getApplicationContext());
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        txtDesUseAverage = (TextView) findViewById(R.id.txtOptionDes);
        switchCycleUseAverage = (Switch) findViewById(R.id.CycleSwitch);
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        numberPicker.setValue(Integer.parseInt(db.readKeySetting(activeUID, "n_cycle_days")));
        String TypeCalc = db.readKeySetting(activeUID, "default_cycle_days");
        choiceDialogArray = getResources().getStringArray(R.array.itemdialogaveragecycle);
        if (TypeCalc.equals("DEFAULT")) {
            switchCycleUseAverage.setChecked(false);
        } else {
            switchCycleUseAverage.setChecked(true);
            if (TypeCalc.equals("3M")) {
                txtDesUseAverage.setText(choiceDialogArray[0]);
            }
            if (TypeCalc.equals("6M")) {
                txtDesUseAverage.setText(choiceDialogArray[1]);
            }
            if (TypeCalc.equals("12M")) {
                txtDesUseAverage.setText(choiceDialogArray[2]);
            }
            if (TypeCalc.equals("ALL")) {
                txtDesUseAverage.setText(choiceDialogArray[3]);
            }
            if (TypeCalc.equals("SMART")) {
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
                new MaterialDialog.Builder(CycleLenghtActivity.this).title((int) R.string.dialog_info_avg_period).iconRes(R.mipmap.ic_launcher).content((int) R.string.dialog_cycle_info).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).show();
            }
        });
        switchCycleUseAverage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    new MaterialDialog.Builder(CycleLenghtActivity.this).title((int) R.string.cycle_use_average_title_dialog).items((int) R.array.itemdialogaveragecycle).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
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
                            db.updateSettings(new Settings(id, activeUID, "default_cycle_days", outcase));
                            if (which == 0) {
                                averageCycle = db.selectAvgCycleTime(activeUID, 0);
                                roundedAvgCycle = Math.round(averageCycle);
                                db.updateSettings(new Settings(id, activeUID, "n_cycle_days", String.valueOf(roundedAvgCycle)));
                            }
                            if (which == 1) {
                                averageCycle = db.selectAvgCycleTime(activeUID, 1);
                                roundedAvgCycle = Math.round(averageCycle);
                                db.updateSettings(new Settings(id, activeUID, "n_cycle_days", String.valueOf(roundedAvgCycle)));
                            }
                            if (which == 2) {
                                averageCycle = db.selectAvgCycleTime(activeUID, 2);
                                roundedAvgCycle = Math.round(averageCycle);
                                db.updateSettings(new Settings(id, activeUID, "n_cycle_days", String.valueOf(roundedAvgCycle)));
                            }
                            if (which == 3) {
                                averageCycle = db.selectAvgCycleTime(activeUID, 3);
                                roundedAvgCycle = Math.round(averageCycle);
                                db.updateSettings(new Settings(id, activeUID, "n_cycle_days", String.valueOf(roundedAvgCycle)));
                            }
                            if (which == 4) {
                                mesiInseriti = db.countRowsAssPeriod(activeUID);
                                if (mesiInseriti <= 9) {
                                    averageCycle = db.selectSmartAvgCycleTime(activeUID, defMinCycle, defMaxCycle);
                                    roundedAvgCycle = Math.round(averageCycle);
                                } else {
                                    averageCycle = db.selectSuperSmartAvgCycleTime(activeUID, defMinCycle, defMaxCycle, threshold);
                                    roundedAvgCycle = Math.round(averageCycle);
                                }
                            }
                            numberPicker.setValue(roundedAvgCycle);
                            txtDesUseAverage.setText(choiceDialog[which]);
                            return true;
                        }
                    }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).cancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            switchCycleUseAverage.setChecked(false);
                            selectedSettings = db.readSettings(activeUID);
                            initializeSettings();
                            db.updateSettings(new Settings(id, activeUID, "default_cycle_days", "DEFAULT"));
                            txtDesUseAverage.setText("");
                        }
                    }).show();
                    return;
                }
                selectedSettings = db.readSettings(activeUID);
                initializeSettings();
                db.updateSettings(new Settings(id, activeUID, "default_cycle_days", "DEFAULT"));
                db.updateSettings(new Settings(id, activeUID, "n_cycle_days", String.valueOf(defaultcycledays)));
                numberPicker.setValue(defaultcycledays);
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
        db.updateSettings(new Settings(id, uid, "n_cycle_days", String.valueOf(numberPicker.getValue())));
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
                    int activeUIDa = db.readActiveUID();
                    selectedSettings = db.readSettings(activeUIDa);
                    initializeSettings();
                    db.updateSettings(new Settings(id, uid, "n_cycle_days", String.valueOf(numberPicker.getValue())));
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
