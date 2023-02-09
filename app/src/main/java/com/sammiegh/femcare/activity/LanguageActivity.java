package com.sammiegh.femcare.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.adapter.LanguageAdapter;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    int activeUID;
    JCGSQLiteHelper db;
    Integer[] imgid = {Integer.valueOf(R.mipmap.ic_en_flag), Integer.valueOf(R.mipmap.ic_it_flag), Integer.valueOf(R.mipmap.ic_fr_flag), Integer.valueOf(R.mipmap.ic_es_flag), Integer.valueOf(R.mipmap.ic_de_flag), Integer.valueOf(R.mipmap.ic_ru_flag), Integer.valueOf(R.mipmap.ic_pt_flag)};
    String[] itemname = {"English", "Italiano", "Français", "Español", "Deutsch", "русский", "Português"};
    GridView listLanguages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.languages_title));

        LanguageAdapter adapter = new LanguageAdapter(this, itemname, imgid);
        listLanguages = (GridView) findViewById(R.id.listLanguages);
        listLanguages.setAdapter(adapter);
        listLanguages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                db = new JCGSQLiteHelper(getApplicationContext());
                activeUID = db.readActiveUID();
                if (position == 0) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "en"));
                    setLocale("en");
                } else if (position == 1) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "it"));
                    setLocale("it");
                } else if (position == 2) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "fr"));
                    setLocale("fr");
                } else if (position == 3) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "es"));
                    setLocale("es");
                } else if (position == 4) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "de"));
                    setLocale("de");
                } else if (position == 5) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "ru"));
                    setLocale("ru");
                } else if (position == 6) {
                    db.updateSettings(new Settings(activeUID, activeUID, "locale", "pt"));
                    setLocale("pt");
                }
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        startActivity(new Intent(this, LanguageActivity.class));
        finish();
    }
}
