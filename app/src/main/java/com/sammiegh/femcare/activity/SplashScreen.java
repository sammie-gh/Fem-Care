package com.sammiegh.femcare.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Moods;
import com.sammiegh.femcare.model.Mucus;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.model.Pills;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.Symptoms;
import com.sammiegh.femcare.model.User;
import com.comix.overwatch.HiveProgressView;

import java.io.File;
import java.util.Locale;

public class SplashScreen extends Activity {
    boolean fr;
    String answer;
    int avatar;
    int boot;
    JCGSQLiteHelper db;
    String email;
    int id;
    int idSet;
    String key;
    String password;
    HiveProgressView progBG;
    String question;
    Settings selectedSettings;
    User selectedUser;
    int status;
    TextView txtInstall;
    int uid;
    int uidSet;
    String username;
    String value;

    @Override
    public void onStart() {
        super.onStart();
        db = new JCGSQLiteHelper(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("localPreferences", 0);
        if (settings.getBoolean("isFirstRun", true)) {
            fr = settings.getBoolean("isFirstRun", true);
            if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 23);
            }
            new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EvaPeriodOvulTracker/").mkdirs();
            settings.edit().putBoolean("isFirstRun", false).commit();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progBG = (HiveProgressView) findViewById(R.id.progresCC);
        txtInstall = (TextView) findViewById(R.id.txtInstall);
        fr = getSharedPreferences("localPreferences", 0).getBoolean("isFirstRun", true);
        db = new JCGSQLiteHelper(this);
        if (fr) {
            progBG.setVisibility(View.VISIBLE);
            txtInstall.setVisibility(View.VISIBLE);
            boot = 0;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        db.onUpgrade(db.getWritableDatabase(), 1, 2);
                        db.insertUser(new User(0, 0, 1, "Default User", "", "", "", "", 0, 0));
                        db.insertSettings(new Settings(0, 0, "n_mestrual_days", "4"));
                        db.insertSettings(new Settings(0, 0, "n_cycle_days", "28"));
                        db.insertSettings(new Settings(0, 0, "n_ovulation_days", "14"));
                        db.insertSettings(new Settings(0, 0, "first_launch", "0"));
                        db.insertSettings(new Settings(0, 0, "pregnancy", "0"));
                        db.insertSettings(new Settings(0, 0, "default_mestrual_days", "DEFAULT"));
                        db.insertSettings(new Settings(0, 0, "default_cycle_days", "DEFAULT"));
                        db.insertSettings(new Settings(0, 0, "default_ovulation_days", "DEFAULT"));
                        db.insertSettings(new Settings(0, 0, "temp_unit", "C"));
                        db.insertSettings(new Settings(0, 0, "weight_unit", "kg"));
                        db.insertSettings(new Settings(0, 0, "height_unit", "cm"));
                        db.insertSettings(new Settings(0, 0, "auto_backup", "0"));
                        db.insertSettings(new Settings(0, 0, "locale", Locale.getDefault().getLanguage()));
                        db.insertSettings(new Settings(0, 0, "security_level", "normal"));
                        db.insertSettings(new Settings(0, 0, "driveid", ""));
                        db.insertSettings(new Settings(0, 0, "lastbackup", ""));
                        db.insertSettings(new Settings(0, 0, "tempdate", ""));
                        db.insertSettings(new Settings(0, 0, "olddate", ""));
                        db.insertSettings(new Settings(0, 0, "showdayscycle", "1"));
                        db.insertSettings(new Settings(0, 0, "showmoods", "1"));
                        db.insertSettings(new Settings(0, 0, "showsymptoms", "1"));
                        db.insertSettings(new Settings(0, 0, "showmedicines", "1"));
                        db.insertSettings(new Settings(0, 0, "showovulfertile", "1"));
                        db.insertSettings(new Settings(0, 0, "showpregnant", "1"));
                        db.insertSettings(new Settings(0, 0, "showmintimate", "1"));
                        db.insertSettings(new Settings(0, 0, "showfutperiod", "1"));
                        db.insertSettings(new Settings(0, 0, "dmestruofinemese", ""));
                        db.insertSettings(new Settings(0, 0, "dmestruoiniziomese", ""));
                        db.insertSettings(new Settings(0, 0, "showmoonphases", "0"));
                        db.insertSettings(new Settings(0, 0, "startdaycaldomlun", "1"));
                        db.insertSettings(new Settings(0, 0, "firstround", "1"));
                        db.insertSettings(new Settings(0, 0, "ov1min", ""));
                        db.insertSettings(new Settings(0, 0, "ov1max", ""));
                        db.insertSettings(new Settings(0, 0, "ov2min", ""));
                        db.insertSettings(new Settings(0, 0, "ov2max", ""));
                        db.insertSettings(new Settings(0, 0, "tempalarm", "0"));
                        db.insertSettings(new Settings(0, 0, "used", "1"));
                        db.insertSettings(new Settings(0, 0, "answeruse", "L"));
                        db.insertSettings(new Settings(0, 0, "temp1", ""));
                        db.insertSettings(new Settings(0, 0, "temp2", ""));
                        db.insertSettings(new Settings(0, 0, "temp3", ""));
                        db.insertSettings(new Settings(0, 0, "temp4", ""));
                        db.insertSettings(new Settings(0, 0, "temp5", ""));
                        int activeUID = db.readActiveUID();
                        int moodsID = activeUID;
                        db.insertMoods(new Moods(moodsID, 0, activeUID, "mood_default_name", "", "moodimg0", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 1, activeUID, "mood_default_name", "", "moodimg1", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 2, activeUID, "mood_default_name", "", "moodimg2", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 3, activeUID, "mood_default_name", "", "moodimg3", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 4, activeUID, "mood_default_name", "", "moodimg4", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 5, activeUID, "mood_default_name", "", "moodimg5", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 6, activeUID, "mood_default_name", "", "moodimg6", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 7, activeUID, "mood_default_name", "", "moodimg7", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 8, activeUID, "mood_default_name", "", "moodimg8", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 9, activeUID, "mood_default_name", "", "moodimg9", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 10, activeUID, "mood_default_name", "", "moodimg10", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 11, activeUID, "mood_default_name", "", "moodimg11", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 12, activeUID, "mood_default_name", "", "moodimg12", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 13, activeUID, "mood_default_name", "", "moodimg13", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 14, activeUID, "mood_default_name", "", "moodimg14", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 15, activeUID, "mood_default_name", "", "moodimg15", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 16, activeUID, "mood_default_name", "", "moodimg16", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 17, activeUID, "mood_default_name", "", "moodimg17", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 18, activeUID, "mood_default_name", "", "moodimg18", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 19, activeUID, "mood_default_name", "", "moodimg19", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 20, activeUID, "mood_default_name", "", "moodimg20", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 21, activeUID, "mood_default_name", "", "moodimg21", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 22, activeUID, "mood_default_name", "", "moodimg22", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 23, activeUID, "mood_default_name", "", "moodimg23", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 24, activeUID, "mood_default_name", "", "moodimg24", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 25, activeUID, "mood_default_name", "", "moodimg25", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 26, activeUID, "mood_default_name", "", "moodimg26", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 27, activeUID, "mood_default_name", "", "moodimg27", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 28, activeUID, "mood_default_name", "", "moodimg28", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 29, activeUID, "mood_default_name", "", "moodimg29", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 30, activeUID, "mood_default_name", "", "moodimg30", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 31, activeUID, "mood_default_name", "", "moodimg31", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 32, activeUID, "mood_default_name", "", "moodimg32", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 33, activeUID, "mood_default_name", "", "moodimg33", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 34, activeUID, "mood_default_name", "", "moodimg34", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 35, activeUID, "mood_default_name", "", "moodimg35", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 36, activeUID, "mood_default_name", "", "moodimg36", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 37, activeUID, "mood_default_name", "", "moodimg37", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 38, activeUID, "mood_default_name", "", "moodimg38", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 39, activeUID, "mood_default_name", "", "moodimg39", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 40, activeUID, "mood_default_name", "", "moodimg40", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 41, activeUID, "mood_default_name", "", "moodimg41", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 42, activeUID, "mood_default_name", "", "moodimg42", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 43, activeUID, "mood_default_name", "", "moodimg43", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 44, activeUID, "mood_default_name", "", "moodimg44", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 45, activeUID, "mood_default_name", "", "moodimg45", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 46, activeUID, "mood_default_name", "", "moodimg46", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 47, activeUID, "mood_default_name", "", "moodimg47", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 48, activeUID, "mood_default_name", "", "moodimg48", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 49, activeUID, "mood_default_name", "", "moodimg49", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 50, activeUID, "mood_default_name", "", "moodimg50", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 51, activeUID, "mood_default_name", "", "moodimg51", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 52, activeUID, "mood_default_name", "", "moodimg52", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 53, activeUID, "mood_default_name", "", "moodimg53", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 54, activeUID, "mood_default_name", "", "moodimg54", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 55, activeUID, "mood_default_name", "", "moodimg55", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 56, activeUID, "mood_default_name", "", "moodimg56", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 57, activeUID, "mood_default_name", "", "moodimg57", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 58, activeUID, "mood_default_name", "", "moodimg58", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 59, activeUID, "mood_default_name", "", "moodimg59", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 60, activeUID, "mood_default_name", "", "moodimg60", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 61, activeUID, "mood_default_name", "", "moodimg61", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 62, activeUID, "mood_default_name", "", "moodimg62", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 63, activeUID, "mood_default_name", "", "moodimg63", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 64, activeUID, "mood_default_name", "", "moodimg64", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 65, activeUID, "mood_default_name", "", "moodimg65", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 66, activeUID, "mood_default_name", "", "moodimg66", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 67, activeUID, "mood_default_name", "", "moodimg67", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 68, activeUID, "mood_default_name", "", "moodimg68", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 69, activeUID, "mood_default_name", "", "moodimg69", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 70, activeUID, "mood_default_name", "", "moodimg70", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 71, activeUID, "mood_default_name", "", "moodimg71", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 72, activeUID, "mood_default_name", "", "moodimg72", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 73, activeUID, "mood_default_name", "", "moodimg73", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 74, activeUID, "mood_default_name", "", "moodimg74", 0, 1, 0));
                        db.insertMoods(new Moods(moodsID, 75, activeUID, "mood_default_name", "", "moodimg75", 0, 1, 0));
                        int symptomID = activeUID;
                        db.insertSymptoms(new Symptoms(symptomID, 0, activeUID, "symptom_default_name", "", "symptomimg0", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 1, activeUID, "symptom_default_name", "", "symptomimg1", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 2, activeUID, "symptom_default_name", "", "symptomimg2", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 3, activeUID, "symptom_default_name", "", "symptomimg3", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 4, activeUID, "symptom_default_name", "", "symptomimg4", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 5, activeUID, "symptom_default_name", "", "symptomimg5", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 6, activeUID, "symptom_default_name", "", "symptomimg6", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 7, activeUID, "symptom_default_name", "", "symptomimg7", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 8, activeUID, "symptom_default_name", "", "symptomimg8", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 9, activeUID, "symptom_default_name", "", "symptomimg9", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 10, activeUID, "symptom_default_name", "", "symptomimg10", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 11, activeUID, "symptom_default_name", "", "symptomimg11", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 12, activeUID, "symptom_default_name", "", "symptomimg12", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 13, activeUID, "symptom_default_name", "", "symptomimg13", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 14, activeUID, "symptom_default_name", "", "symptomimg14", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 15, activeUID, "symptom_default_name", "", "symptomimg15", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 16, activeUID, "symptom_default_name", "", "symptomimg16", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 17, activeUID, "symptom_default_name", "", "symptomimg17", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 18, activeUID, "symptom_default_name", "", "symptomimg18", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 19, activeUID, "symptom_default_name", "", "symptomimg19", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 20, activeUID, "symptom_default_name", "", "symptomimg20", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 21, activeUID, "symptom_default_name", "", "symptomimg21", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 22, activeUID, "symptom_default_name", "", "symptomimg22", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 23, activeUID, "symptom_default_name", "", "symptomimg23", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 24, activeUID, "symptom_default_name", "", "symptomimg24", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 25, activeUID, "symptom_default_name", "", "symptomimg25", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 26, activeUID, "symptom_default_name", "", "symptomimg26", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 27, activeUID, "symptom_default_name", "", "symptomimg27", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 28, activeUID, "symptom_default_name", "", "symptomimg28", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 29, activeUID, "symptom_default_name", "", "symptomimg29", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 30, activeUID, "symptom_default_name", "", "symptomimg30", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 31, activeUID, "symptom_default_name", "", "symptomimg31", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 32, activeUID, "symptom_default_name", "", "symptomimg32", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 33, activeUID, "symptom_default_name", "", "symptomimg33", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 34, activeUID, "symptom_default_name", "", "symptomimg34", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 35, activeUID, "symptom_default_name", "", "symptomimg35", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 36, activeUID, "symptom_default_name", "", "symptomimg36", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 37, activeUID, "symptom_default_name", "", "symptomimg37", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 38, activeUID, "symptom_default_name", "", "symptomimg38", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 39, activeUID, "symptom_default_name", "", "symptomimg39", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 40, activeUID, "symptom_default_name", "", "symptomimg40", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 41, activeUID, "symptom_default_name", "", "symptomimg41", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 42, activeUID, "symptom_default_name", "", "symptomimg42", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 43, activeUID, "symptom_default_name", "", "symptomimg43", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 44, activeUID, "symptom_default_name", "", "symptomimg44", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 45, activeUID, "symptom_default_name", "", "symptomimg45", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 46, activeUID, "symptom_default_name", "", "symptomimg46", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 47, activeUID, "symptom_default_name", "", "symptomimg47", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 48, activeUID, "symptom_default_name", "", "symptomimg48", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 49, activeUID, "symptom_default_name", "", "symptomimg49", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 50, activeUID, "symptom_default_name", "", "symptomimg50", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 51, activeUID, "symptom_default_name", "", "symptomimg51", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 52, activeUID, "symptom_default_name", "", "symptomimg52", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 53, activeUID, "symptom_default_name", "", "symptomimg53", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 54, activeUID, "symptom_default_name", "", "symptomimg54", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 55, activeUID, "symptom_default_name", "", "symptomimg55", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 56, activeUID, "symptom_default_name", "", "symptomimg56", 0, 1, 0));
                        db.insertSymptoms(new Symptoms(symptomID, 57, activeUID, "symptom_default_name", "", "symptomimg57", 0, 1, 0));
                        int mucusID = activeUID;
                        db.insertMucus(new Mucus(mucusID, 0, mucusID, "mucus_default_name", "", "mucusimg0", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 1, mucusID, "mucus_default_name", "", "mucusimg1", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 2, mucusID, "mucus_default_name", "", "mucusimg2", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 3, mucusID, "mucus_default_name", "", "mucusimg3", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 4, mucusID, "mucus_default_name", "", "mucusimg4", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 5, mucusID, "mucus_default_name", "", "mucusimg5", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 6, mucusID, "mucus_default_name", "", "mucusimg6", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 7, mucusID, "mucus_default_name", "", "mucusimg7", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 8, mucusID, "mucus_default_name", "", "mucusimg8", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 9, mucusID, "mucus_default_name", "", "mucusimg9", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 10, mucusID, "mucus_default_name", "", "mucusimg10", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 11, mucusID, "mucus_default_name", "", "mucusimg11", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 12, mucusID, "mucus_default_name", "", "mucusimg12", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 13, mucusID, "mucus_default_name", "", "mucusimg13", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 14, mucusID, "mucus_default_name", "", "mucusimg14", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 15, mucusID, "mucus_default_name", "", "mucusimg15", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 16, mucusID, "mucus_default_name", "", "mucusimg16", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 17, mucusID, "mucus_default_name", "", "mucusimg17", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 18, mucusID, "mucus_default_name", "", "mucusimg18", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 19, mucusID, "mucus_default_name", "", "mucusimg19", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 20, mucusID, "mucus_default_name", "", "mucusimg20", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 21, mucusID, "mucus_default_name", "", "mucusimg21", 0, 1, 0));
                        db.insertMucus(new Mucus(mucusID, 22, mucusID, "mucus_default_name", "", "mucusimg22", 0, 1, 0));
                        int pillID = activeUID;
                        db.insertPills(new Pills(pillID, 0, pillID, "pill_default_name", "", "pillimg0", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 1, pillID, "pill_default_name", "", "pillimg1", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 2, pillID, "pill_default_name", "", "pillimg2", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 3, pillID, "pill_default_name", "", "pillimg3", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 4, pillID, "pill_default_name", "", "pillimg4", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 5, pillID, "pill_default_name", "", "pillimg5", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 6, pillID, "pill_default_name", "", "pillimg6", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 7, pillID, "pill_default_name", "", "pillimg7", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 8, pillID, "pill_default_name", "", "pillimg8", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 9, pillID, "pill_default_name", "", "pillimg9", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 10, pillID, "pill_default_name", "", "pillimg10", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 11, pillID, "pill_default_name", "", "pillimg11", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 12, pillID, "pill_default_name", "", "pillimg12", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 13, pillID, "pill_default_name", "", "pillimg13", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 14, pillID, "pill_default_name", "", "pillimg14", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 15, pillID, "pill_default_name", "", "pillimg15", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 16, pillID, "pill_default_name", "", "pillimg16", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 17, pillID, "pill_default_name", "", "pillimg17", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 18, pillID, "pill_default_name", "", "pillimg18", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 19, pillID, "pill_default_name", "", "pillimg19", 0, 1, 0));
                        db.insertPills(new Pills(pillID, 20, pillID, "pill_default_name", "", "pillimg20", 0, 1, 0));
                        int notID = activeUID;
                        db.insertNotifications(new Notifications(0, 0, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(1, 1, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(2, 2, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(3, 3, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(4, 4, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(5, 5, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(6, 6, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(7, 7, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(10, 0, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(11, 1, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(12, 2, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(13, 3, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(14, 4, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(15, 5, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(16, 6, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(17, 7, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(20, 0, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(21, 1, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(22, 2, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(23, 3, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(24, 4, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(25, 5, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(26, 6, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db.insertNotifications(new Notifications(27, 7, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
                        db = new JCGSQLiteHelper(getApplicationContext());
                        int activeUID2 = db.readActiveUID();
                        selectedUser = db.readUser(activeUID2);
                        initializeUser();
                        setLocale(db.readKeySetting(activeUID2, "locale"));
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(5000);
                                    if (password.equals("")) {
                                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                        finish();
                                        return;
                                    }
                                    startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    if (password.equals("")) {
                                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                        finish();
                                        return;
                                    }
                                    startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                                    finish();
                                } catch (Throwable th) {
                                    if (password.equals("")) {
                                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                                        finish();
                                    }
                                    throw th;
                                }
                            }
                        }).start();
                    } catch (Throwable th) {
                        db = new JCGSQLiteHelper(getApplicationContext());
                        int activeUID3 = db.readActiveUID();
                        selectedUser = db.readUser(activeUID3);
                        initializeUser();
                        setLocale(db.readKeySetting(activeUID3, "locale"));
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(5000);
                                    if (password.equals("")) {
                                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                        finish();
                                        return;
                                    }
                                    startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    if (password.equals("")) {
                                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                        finish();
                                        return;
                                    }
                                    startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                                    finish();
                                } catch (Throwable th) {
                                    if (password.equals("")) {
                                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                                        finish();
                                    }
                                    throw th;
                                }
                            }
                        }).start();
                        throw th;
                    }
                }
            }).start();
            return;
        }
        db = new JCGSQLiteHelper(getApplicationContext());
        int activeUID = db.readActiveUID();
        selectedUser = db.readUser(activeUID);
        initializeUser();
        setLocale(db.readKeySetting(activeUID, "locale"));
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    if (password.equals("")) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                        return;
                    }
                    startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    if (password.equals("")) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                        return;
                    }
                    startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                    finish();
                } catch (Throwable th) {
                    if (password.equals("")) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreen.this, LoginPinActivity.class));
                        finish();
                    }
                    throw th;
                }
            }
        }).start();
    }

    public void initializeSettings() {
        idSet = selectedSettings.getId();
        uidSet = selectedSettings.getUid();
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

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
