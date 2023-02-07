package com.sammiegh.femcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class CustomCalendarActivity extends AppCompatActivity {
    int activeUID;
    String dateKey;
    String dateNote;
    JCGSQLiteHelper db;
    int diastolic;
    float fianchi;
    int gommo;
    float height;
    int id;
    int idNote;
    String initDate;
    int intimate;
    String key;
    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
    int pressure;
    String secretnote;
    Note selectedNote;
    Settings selectedSettings;
    float seno;
    int sextimes;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    TextView txtData;
    int uid;
    int uidNote;
    String value;
    float vita;
    float weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);
        getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtData = (TextView) findViewById(R.id.txtData);
        dateKey = getIntent().getStringExtra("datekey");
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initDate = db.readKeySetting(activeUID, "tempdate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.addnote) {
            return super.onOptionsItemSelected(item);
        }
        Intent openNote = new Intent(this, NoteActivity.class);
        openNote.putExtra("datekey", initDate);
        startActivity(openNote);
        finish();
        return true;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    public void initializeNote() {
        idNote = selectedNote.getId();
        uidNote = selectedNote.getUid();
        dateNote = selectedNote.getDate();
        note = selectedNote.getNotes();
        secretnote = selectedNote.getSecretNotes();
        symptoms = selectedNote.getSymptoms();
        mucus = selectedNote.getMucus();
        moods = selectedNote.getMoods();
        intimate = selectedNote.getIntimate();
        gommo = selectedNote.getGommo();
        sextimes = selectedNote.getSextimes();
        numorgasm = selectedNote.getNumorgasm();
        pill = selectedNote.getPill();
        ovulationtest = selectedNote.getOvulationtest();
        temperature = selectedNote.getTemperature();
        weight = selectedNote.getWeight();
        height = selectedNote.getHeight();
        seno = selectedNote.getSeno();
        vita = selectedNote.getVita();
        fianchi = selectedNote.getFianchi();
        systolic = selectedNote.getSystolic();
        diastolic = selectedNote.getDiastolic();
        pressure = selectedNote.getPressure();
        testgravidanza = selectedNote.getTestgravidanza();
    }
}
