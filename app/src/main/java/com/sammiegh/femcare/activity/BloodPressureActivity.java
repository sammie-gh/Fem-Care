package com.sammiegh.femcare.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;
import com.shawnlin.numberpicker.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BloodPressureActivity extends AppCompatActivity {
    String moodsstringdefault;
    String mucusstringdefault;
    String pillstringdefault;
    String symptomsstringdefault;
    int activeUID;
    String answer;
    int avatar;
    String dateNote;
    JCGSQLiteHelper db;
    int diastolic;
    String email;
    float fianchi;
    int gommo;
    float height;
    int id;
    int idNote;
    int intimate;
    String key;
    String moods;
    String mucus;
    String note;
    NumberPicker numberPickerDia;
    NumberPicker numberPickerPulse;
    NumberPicker numberPickerSys;
    int numorgasm;
    Date oggiDateCheck;
    int ovulationtest;
    String password;
    String pill;
    int pressure;
    String question;
    int rowsNumDateNote;
    int rowsNumMoodUid;
    int rowsNumMucusUid;
    int rowsNumPillUid;
    int rowsNumSymptomsUid;
    String sDateKey;
    String sOggiDateCheck;
    String secretnote;
    Note selectedNote;
    Settings selectedSettings;
    User selectedUser;
    float seno;
    int sextimes;
    int status;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    TextView textDate;
    int uid;
    int uidNote;
    String username;
    String value;
    float vita;
    float weight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        textDate = (TextView) findViewById(R.id.txtDate);
        sDateKey = getIntent().getStringExtra("datekey");
        oggiDateCheck = new Date();
        sOggiDateCheck = new SimpleDateFormat("yyyyMMdd").format(oggiDateCheck);
        if (!sDateKey.substring(0, 4).equals(sOggiDateCheck.substring(0, 4))) {
            textDate.setText(sDateKey.substring(6, 8) + "-" + sDateKey.substring(4, 6) + "-" + sDateKey.substring(0, 4));
        } else if (!sDateKey.substring(4, 6).equals(sOggiDateCheck.substring(4, 6))) {
            textDate.setText(sDateKey.substring(6, 8) + "-" + sDateKey.substring(4, 6) + "-" + sDateKey.substring(0, 4));
        } else if (sDateKey.substring(6, 8).equals(sOggiDateCheck.substring(6, 8))) {
            textDate.setText(getString(R.string.day_today));
        } else if (Integer.parseInt(sDateKey.substring(6, 8)) == Integer.parseInt(sOggiDateCheck.substring(6, 8)) - 1) {
            textDate.setText(getString(R.string.day_yesterday));
        } else if (Integer.parseInt(sDateKey.substring(6, 8)) == Integer.parseInt(sOggiDateCheck.substring(6, 8)) + 1) {
            textDate.setText(getString(R.string.day_tomorrow));
        } else {
            textDate.setText(sDateKey.substring(6, 8) + "-" + sDateKey.substring(4, 6) + "-" + sDateKey.substring(0, 4));
        }
        numberPickerSys = (NumberPicker) findViewById(R.id.number_picker_sys);
        numberPickerDia = (NumberPicker) findViewById(R.id.number_picker_dia);
        numberPickerPulse = (NumberPicker) findViewById(R.id.number_picker_sys);
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        selectedUser = db.readUser(activeUID);
        initializeUser();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        db.updateSettings(new Settings(id, uid, "tempdate", sDateKey));
        rowsNumDateNote = db.countRowsNote(activeUID, sDateKey);
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, sDateKey);
            initializeNote();
            if (systolic != 0 || diastolic != 0 || pressure != 0) {
                numberPickerSys.setValue(systolic);
                numberPickerDia.setValue(diastolic);
                numberPickerPulse.setValue(pressure);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bwh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        if (rowsNumDateNote == 1) {
            db.updateNote(new Note(idNote, uidNote, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, numberPickerSys.getValue(), numberPickerDia.getValue(), numberPickerPulse.getValue(), testgravidanza));
        } else {
            rowsNumMoodUid = db.countRowsMood(activeUID);
            rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
            rowsNumMucusUid = db.countRowsMucus(activeUID);
            rowsNumPillUid = db.countRowsPills(activeUID);
            moodsstringdefault = fillString(rowsNumMoodUid, '0');
            symptomsstringdefault = fillString(rowsNumSymptomsUid, '0');
            mucusstringdefault = fillString(rowsNumMucusUid, '0');
            pillstringdefault = fillString(rowsNumPillUid, '0');
            db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsstringdefault, mucusstringdefault, moodsstringdefault, 0, 0, 0, 0, pillstringdefault, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, numberPickerSys.getValue(), numberPickerDia.getValue(), numberPickerPulse.getValue(), 0));
        }
        finish();
        return true;
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

    public static String fillString(int count, char c) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
