package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class ShowHideActivity extends AppCompatActivity {
    int activeUID;
    boolean changeTemp = false;
    String[] choiceDialogArray;
    JCGSQLiteHelper db;
    int id;
    String key;
    RelativeLayout relFirstDayWeek;
    Settings selectedSettings;
    Switch switchCycleDays;
    Switch switchFutPer;
    Switch switchIntercourse;
    Switch switchMedicines;
    Switch switchOvulFertile;
    Switch switchPregnant;
    TextView txtFirstDayWeek;
    int uid;
    String value;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hide);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Calendar Options");

        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        switchCycleDays = (Switch) findViewById(R.id.CycleDaysSwitch);
        switchIntercourse = (Switch) findViewById(R.id.IntimateSwitch);
        switchPregnant = (Switch) findViewById(R.id.PregnancySwitch);
        switchMedicines = (Switch) findViewById(R.id.MedicinesSwitch);
        switchOvulFertile = (Switch) findViewById(R.id.OvulFertileSwitch);
        switchFutPer = (Switch) findViewById(R.id.FuturePeriodSwitch);
        txtFirstDayWeek = (TextView) findViewById(R.id.txtFirstDayWeek);
        relFirstDayWeek = (RelativeLayout) findViewById(R.id.RelLayFirstDayWeek);
        String primoGiorno = db.readKeySetting(activeUID, "startdaycaldomlun");
        choiceDialogArray = getResources().getStringArray(R.array.itemdialogweekdays);
        txtFirstDayWeek.setText(choiceDialogArray[Integer.valueOf(primoGiorno).intValue()]);

        relFirstDayWeek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(ShowHideActivity.this).title((int) R.string.option_calendar).items((int) R.array.itemdialogweekdays).iconRes(R.mipmap.ic_date_range_black_48dp).itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        String outcase;
                        switch (which) {
                            case 0:
                                outcase = "0";
                                break;
                            case 1:
                                outcase = "1";
                                break;
                            case 2:
                                outcase = "2";
                                break;
                            default:
                                outcase = "1";
                                break;
                        }
                        selectedSettings = db.readSettings(activeUID);
                        initializeSettings();
                        db.updateSettings(new Settings(id, activeUID, "startdaycaldomlun", outcase));
                        Intent intent = new Intent(getApplicationContext(), ShowHideActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        return true;
                    }
                }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                    }
                }).show();
            }
        });
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        String switchShoDays = db.readKeySetting(uid, "showdayscycle");
        String switchShowMed = db.readKeySetting(uid, "showmedicines");
        String switchShoOvul = db.readKeySetting(uid, "showovulfertile");
        String switchShoPreg = db.readKeySetting(uid, "showpregnant");
        String switchShoInt = db.readKeySetting(uid, "showmintimate");
        String switchShoFutP = db.readKeySetting(uid, "showfutperiod");

        if (switchShoDays.equals("1")) {
            switchCycleDays.setChecked(true);
        } else {
            switchCycleDays.setChecked(false);
        }
        if (switchShowMed.equals("1")) {
            switchMedicines.setChecked(true);
        } else {
            switchMedicines.setChecked(false);
        }
        if (switchShoOvul.equals("1")) {
            switchOvulFertile.setChecked(true);
        } else {
            switchOvulFertile.setChecked(false);
        }
        if (switchShoPreg.equals("1")) {
            switchPregnant.setChecked(true);
        } else {
            switchPregnant.setChecked(false);
        }
        if (switchShoInt.equals("1")) {
            switchIntercourse.setChecked(true);
        } else {
            switchIntercourse.setChecked(false);
        }
        if (switchShoFutP.equals("1")) {
            switchFutPer.setChecked(true);
        } else {
            switchFutPer.setChecked(false);
        }
        switchCycleDays.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    db.updateSettings(new Settings(id, activeUID, "showdayscycle", "1"));
                } else {
                    db.updateSettings(new Settings(id, activeUID, "showdayscycle", "0"));
                }
            }
        });
        switchMedicines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    db.updateSettings(new Settings(id, activeUID, "showmedicines", "1"));
                } else {
                    db.updateSettings(new Settings(id, activeUID, "showmedicines", "0"));
                }
            }
        });
        switchOvulFertile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    db.updateSettings(new Settings(id, activeUID, "showovulfertile", "1"));
                } else {
                    db.updateSettings(new Settings(id, activeUID, "showovulfertile", "0"));
                }
            }
        });
        switchPregnant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    db.updateSettings(new Settings(id, activeUID, "showpregnant", "1"));
                } else {
                    db.updateSettings(new Settings(id, activeUID, "showpregnant", "0"));
                }
            }
        });
        switchIntercourse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    db.updateSettings(new Settings(id, activeUID, "showmintimate", "1"));
                } else {
                    db.updateSettings(new Settings(id, activeUID, "showmintimate", "0"));
                }
            }
        });
        switchFutPer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                changeTemp = true;
                if (bChecked) {
                    db.updateSettings(new Settings(id, activeUID, "showfutperiod", "1"));
                } else {
                    db.updateSettings(new Settings(id, activeUID, "showfutperiod", "0"));
                }
            }
        });
    }

   @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cycle_period, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
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
}
