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

import java.text.SimpleDateFormat;
import java.util.Date;

import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;

public class ExtraDetailsActivity extends AppCompatActivity implements OnValueChangeListener {
    float bustDef;
    float hipdef;
    String moodsstringdefault;
    String mucusstringdefault;
    String pillstringdefault;
    String symptomsstringdefault;
    TextView txtbwhtitle;
    float wispDef;
    int activeUID;
    String answer;
    int avatar;
    TextView bSubTitle;
    String dateNote;
    JCGSQLiteHelper db;
    int diastolic;
    String email;
    float fianchi;
    int gommo;
    TextView hSubTitle;
    float height;
    int id;
    int idNote;
    String initHeightUnit;
    int intimate;
    String key;
    ActualNumberPicker mactualPickerB;
    ActualNumberPicker mactualPickerH;
    ActualNumberPicker mactualPickerW;
    String moods;
    String mucus;
    String newStringH;
    String newStringSUBB;
    String newStringSUBH;
    String newStringSUBW;
    String note;
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
    String unita;
    String username;
    String value;
    float vita;
    TextView wSubTitle;
    float weight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_details);
        textDate = (TextView) findViewById(R.id.txtDate);
        mactualPickerB = (ActualNumberPicker) findViewById(R.id.actual_pickerB);
        mactualPickerH = (ActualNumberPicker) findViewById(R.id.actual_pickerH);
        mactualPickerW = (ActualNumberPicker) findViewById(R.id.actual_pickerW);
        mactualPickerB.setListener(this);
        mactualPickerH.setListener(this);
        mactualPickerW.setListener(this);
        txtbwhtitle = (TextView) findViewById(R.id.txtTitleBlood);
        bSubTitle = (TextView) findViewById(R.id.subTxtBust);
        hSubTitle = (TextView) findViewById(R.id.subTxtHip);
        wSubTitle = (TextView) findViewById(R.id.subTxtWaist);
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
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        selectedUser = db.readUser(activeUID);
        initializeUser();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        db.updateSettings(new Settings(id, uid, "tempdate", sDateKey));
        initHeightUnit = db.readKeySetting(uid, "height_unit");
        if (initHeightUnit.equals("cm")) {
            newStringH = getString(R.string.bust_waist_hip_title) + " (" + getString(R.string.units_height_cm) + ")";
        }
        if (initHeightUnit.equals("m")) {
            newStringH = getString(R.string.bust_waist_hip_title) + " (" + getString(R.string.units_height_m) + ")";
        }
        if (initHeightUnit.equals("inch")) {
            newStringH = getString(R.string.bust_waist_hip_title) + " (" + getString(R.string.units_height_inch) + ")";
        }
        txtbwhtitle.setText(newStringH);
        rowsNumDateNote = db.countRowsNote(activeUID, sDateKey);
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, sOggiDateCheck);
            initializeNote();
            mactualPickerB.setValue((int) seno);
            mactualPickerW.setValue((int) vita);
            mactualPickerH.setValue((int) fianchi);
        }
    }

    public void onValueChanged(int oldValue, int newValue) {
        if (initHeightUnit.equals("cm")) {
            bustDef = (float) mactualPickerB.getValue();
            wispDef = (float) mactualPickerW.getValue();
            hipdef = (float) mactualPickerH.getValue();
            unita = getString(R.string.units_height_cm);
        }
        if (initHeightUnit.equals("m")) {
            bustDef = cmtoother((float) mactualPickerB.getValue(), 1);
            wispDef = cmtoother((float) mactualPickerW.getValue(), 1);
            hipdef = cmtoother((float) mactualPickerH.getValue(), 1);
            unita = getString(R.string.units_height_m);
        }
        if (initHeightUnit.equals("inch")) {
            bustDef = cmtoother((float) mactualPickerB.getValue(), 2);
            wispDef = cmtoother((float) mactualPickerW.getValue(), 2);
            hipdef = cmtoother((float) mactualPickerH.getValue(), 2);
            unita = getString(R.string.units_height_inch);
        }
        newStringSUBB = "(" + bustDef + " " + unita + ")";
        bSubTitle.setText(newStringSUBB);
        newStringSUBW = "(" + wispDef + " " + unita + ")";
        wSubTitle.setText(newStringSUBW);
        newStringSUBH = "(" + hipdef + " " + unita + ")";
        hSubTitle.setText(newStringSUBH);
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
            db.updateNote(new Note(idNote, uidNote, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, weight, height, (float) mactualPickerB.getValue(), (float) mactualPickerW.getValue(), (float) mactualPickerH.getValue(), systolic, diastolic, pressure, testgravidanza));
        } else {
            rowsNumMoodUid = db.countRowsMood(activeUID);
            rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
            rowsNumMucusUid = db.countRowsMucus(activeUID);
            rowsNumPillUid = db.countRowsPills(activeUID);
            moodsstringdefault = fillString(rowsNumMoodUid, '0');
            symptomsstringdefault = fillString(rowsNumSymptomsUid, '0');
            mucusstringdefault = fillString(rowsNumMucusUid, '0');
            pillstringdefault = fillString(rowsNumPillUid, '0');
            db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsstringdefault, mucusstringdefault, moodsstringdefault, 0, 0, 0, 0, pillstringdefault, 0, 0.0f, 0.0f, 0.0f, (float) mactualPickerB.getValue(), (float) mactualPickerW.getValue(), (float) mactualPickerH.getValue(), 0, 0, 0, 0));
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

    public static float cmtoother(float input, int type) {
        float output = 0.0f;
        if (type == 1) {
            output = input / 100.0f;
        }
        if (type == 2) {
            return input * 0.3937f;
        }
        return output;
    }

    @Override
    public void onBackPressed() {
        if (rowsNumDateNote == 1) {
            db.updateNote(new Note(idNote, uidNote, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, weight, height, (float) mactualPickerB.getValue(), (float) mactualPickerW.getValue(), (float) mactualPickerH.getValue(), systolic, diastolic, pressure, testgravidanza));
        } else {
            rowsNumMoodUid = db.countRowsMood(activeUID);
            rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
            rowsNumMucusUid = db.countRowsMucus(activeUID);
            rowsNumPillUid = db.countRowsPills(activeUID);
            moodsstringdefault = fillString(rowsNumMoodUid, '0');
            symptomsstringdefault = fillString(rowsNumSymptomsUid, '0');
            mucusstringdefault = fillString(rowsNumMucusUid, '0');
            pillstringdefault = fillString(rowsNumPillUid, '0');
            db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsstringdefault, mucusstringdefault, moodsstringdefault, 0, 0, 0, 0, pillstringdefault, 0, 0.0f, 0.0f, 0.0f, (float) mactualPickerB.getValue(), (float) mactualPickerW.getValue(), (float) mactualPickerH.getValue(), 0, 0, 0, 0));
        }
        finish();
    }
}
