package com.sammiegh.femcare.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.shawnlin.numberpicker.NumberPicker;

public class GommoActivity extends AppCompatActivity {
    int intimateyes = 1;
    RadioButton checkedRadioButtonNO;
    RadioButton checkedRadioButtonYES;
    String dateKey;
    String dateNote;
    JCGSQLiteHelper db;
    int diastolic;
    float fianchi;
    int gommo;
    int gommoBox = 0;
    float height;
    int id;
    int idNote;
    ImageView imageGommo;
    ImageView imgOrgasm;
    int intimate;
    String key;
    String moods;
    String mucus;
    EditText namePartner;
    String note;
    NumberPicker numberPickerOrgasm;
    NumberPicker numberPickerTimes;
    int numorgasm;
    int ovulationtest;
    String pill;
    int pressure;
    RadioGroup radioGroupGommo;
    String secretnote;
    Note selectedNote;
    Settings selectedSettings;
    float seno;
    int sextimes;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    int uid;
    int uidNote;
    String value;
    float vita;
    float weight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateKey = getIntent().getStringExtra("datekey");
        setContentView(R.layout.activity_gommo);
        db = new JCGSQLiteHelper(getApplicationContext());
        selectedNote = db.readNote(db.readActiveUID(), dateKey);
        initializeNote();
        radioGroupGommo = (RadioGroup) findViewById(R.id.radioGommo);
        checkedRadioButtonYES = (RadioButton) radioGroupGommo.findViewById(R.id.radioYes);
        checkedRadioButtonNO = (RadioButton) radioGroupGommo.findViewById(R.id.radioNo);
        namePartner = (EditText) findViewById(R.id.editTxtPartner);
        imageGommo = (ImageView) findViewById(R.id.imgGommo);
        imgOrgasm = (ImageView) findViewById(R.id.imgIco3);
        numberPickerTimes = (NumberPicker) findViewById(R.id.number_picker1);
        numberPickerOrgasm = (NumberPicker) findViewById(R.id.number_picker2);
        if (intimate == 1) {
            numberPickerTimes.setValue(sextimes);
            numberPickerOrgasm.setValue(numorgasm);
        } else {
            numberPickerTimes.setValue(1);
            numberPickerOrgasm.setValue(0);
        }
        if (numorgasm == 0) {
            imgOrgasm.setImageResource(R.mipmap.ic_no_gommo);
        } else {
            imgOrgasm.setImageResource(R.mipmap.ic_yes_gommo);
        }
        if (gommo == 1) {
            gommoBox = 1;
            imageGommo.setImageResource(R.mipmap.ic_gommo_no);
            checkedRadioButtonYES.setChecked(true);
        } else {
            gommoBox = 0;
            imageGommo.setImageResource(R.mipmap.ic_gommo_yes);
            checkedRadioButtonYES.setChecked(false);
        }
        if (!secretnote.equals("")) {
            namePartner.setText(secretnote);
        }
        radioGroupGommo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                if (checkedRadioButtonYES.isChecked()) {
                    gommoBox = 1;
                    imageGommo.setImageResource(R.mipmap.ic_gommo_no);
                    return;
                }
                gommoBox = 0;
                imageGommo.setImageResource(R.mipmap.ic_gommo_yes);
            }
        });
        numberPickerOrgasm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal == 0) {
                    imgOrgasm.setImageResource(R.mipmap.ic_no_gommo);
                } else {
                    imgOrgasm.setImageResource(R.mipmap.ic_yes_gommo);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gommo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        db.updateNote(new Note(idNote, uidNote, dateNote, note, namePartner.getText().toString(), symptoms, mucus, moods, intimateyes, gommoBox, numberPickerTimes.getValue(), numberPickerOrgasm.getValue(), pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
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

    @Override
    public void onBackPressed() {
        db.updateNote(new Note(idNote, uidNote, dateNote, note, namePartner.getText().toString(), symptoms, mucus, moods, intimateyes, gommoBox, numberPickerTimes.getValue(), numberPickerOrgasm.getValue(), pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
        finish();
    }
}
