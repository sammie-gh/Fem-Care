package com.sammiegh.femcare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.activity.PillsFragment5Activity;
import com.sammiegh.femcare.activity.UnitsActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TabFragment5 extends Fragment {
    String moodsstringdefault;
    String mucusStringDefault;
    float pesoSave;
    float pesoSaveDEF;
    String pillStringDefault;
    String symptomsStringDefault;
    float tempSave;
    float tempSaveDEF;
    int activeUID;
    String answer;
    int avatar;
    String dateNote;
    JCGSQLiteHelper db;
    MaterialDialog dialog;
    int diastolic;
    EditText editTextTemp;
    EditText editTextWeight;
    TextView edtTxtT;
    TextView edtTxtW;
    String email;
    float fianchi;
    int gommo;
    float height;
    int id;
    int idNote;
    ImageView imageBookmark;
    ImageView imageQM;
    String initHeightUnit;
    String initTempUnit;
    String initWeightUnit;
    int intimate;
    String key;

    AdView mAdView;
    String moods;
    String mucus;
    String newStringH;
    String newStringT;
    String newStringT2;
    String newStringW;
    String newStringW2;
    String note;
    int numorgasm;
    Date oggiDateCheck;
    int ovulationtest;
    String password;
    String pill;
    View positiveAction;
    int pressure;
    String question;
    RelativeLayout relPeso;
    RelativeLayout relTemp;
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
    Switch switchMedicines;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    TextView texTemperature;
    TextView texWeight;
    TextView textDate;
    int uid;
    int uidNote;
    TextView unitaPesoTV;
    TextView unitaTempTV;
    String username;
    String value;
    float vita;
    float weight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_5, container, false);
        mAdView = (AdView) view.findViewById(R.id.adViewNativeFrag5);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }
        });
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setVisibility(View.VISIBLE);
        textDate = (TextView) view.findViewById(R.id.txtDate);
        texTemperature = (TextView) view.findViewById(R.id.txtOptionTitleTemp);
        texWeight = (TextView) view.findViewById(R.id.txtOptionTitleWe);
        edtTxtW = (TextView) view.findViewById(R.id.editTxtWeight);
        edtTxtT = (TextView) view.findViewById(R.id.editTxtTemp);
        relPeso = (RelativeLayout) view.findViewById(R.id.relWeight);
        relTemp = (RelativeLayout) view.findViewById(R.id.relTemp);
        switchMedicines = (Switch) view.findViewById(R.id.switchMedicines);
        imageBookmark = (ImageView) view.findViewById(R.id.imgBookmark);
        imageQM = (ImageView) view.findViewById(R.id.imageQMark);
        sDateKey = getArguments().getString("datekey");
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
        db = new JCGSQLiteHelper(getActivity());
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        initTempUnit = db.readKeySetting(activeUID, "temp_unit");
        initWeightUnit = db.readKeySetting(activeUID, "weight_unit");
        initHeightUnit = db.readKeySetting(activeUID, "height_unit");
        if (initTempUnit.equals("C")) {
            newStringT = getString(R.string.other_temperature_title) + " (" + getString(R.string.units_temperature_C) + ")";
        } else {
            newStringT = getString(R.string.other_temperature_title) + " (" + getString(R.string.units_temperature_F) + ")";
        }
        if (initWeightUnit.equals("kg")) {
            newStringW = getString(R.string.other_weight_title) + " (" + getString(R.string.units_weight_kg) + ")";
        }
        if (initWeightUnit.equals("lb")) {
            newStringW = getString(R.string.other_weight_title) + " (" + getString(R.string.units_weight_lb) + ")";
        }
        if (initWeightUnit.equals("stone")) {
            newStringW = getString(R.string.other_weight_title) + " (" + getString(R.string.units_weight_stone) + ")";
        }
        if (initHeightUnit.equals("cm")) {
            newStringH = getString(R.string.other_height_title) + " (" + getString(R.string.units_height_cm) + ")";
        }
        if (initHeightUnit.equals("m")) {
            newStringH = getString(R.string.other_height_title) + " (" + getString(R.string.units_height_m) + ")";
        }
        if (initHeightUnit.equals("inch")) {
            newStringH = getString(R.string.other_height_title) + " (" + getString(R.string.units_height_inch) + ")";
        }
        if (initHeightUnit.equals("feetinch")) {
            newStringH = getString(R.string.other_height_title) + " (" + getString(R.string.units_height_ftinch) + ")";
        }
        texTemperature.setText(newStringT);
        texWeight.setText(newStringW);
        rowsNumDateNote = db.countRowsNote(activeUID, sDateKey);
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, sDateKey);
            initializeNote();
            rowsNumPillUid = db.countRowsPills(activeUID);
            pillStringDefault = fillString(rowsNumPillUid, '0');
            if (pill.equals(pillStringDefault)) {
                switchMedicines.setChecked(false);
            } else {
                switchMedicines.setChecked(true);
                imageBookmark.setVisibility(View.VISIBLE);
            }
            if (initTempUnit.equals("F")) {
                tempSaveDEF = celsiustofahrenheit(temperature);
            } else {
                tempSaveDEF = temperature;
            }
            edtTxtT.setText(Float.toString(round(tempSaveDEF, 1)));
            if (initWeightUnit.equals("lb")) {
                pesoSaveDEF = kgtolb(weight);
            } else if (initWeightUnit.equals("stone")) {
                pesoSaveDEF = kgtostone(weight);
            } else {
                pesoSaveDEF = weight;
            }
            edtTxtW.setText(Float.toString(round(pesoSaveDEF, 1)));
        }
        relPeso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog = new MaterialDialog.Builder(v.getContext()).title((int) R.string.other_weight_title).iconRes(R.mipmap.ic_weight).customView(R.layout.dialog_customview_weight, true).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        editTextWeight = (EditText) dialog.getCustomView().findViewById(R.id.editTextWeight);
                        pesoSave = Float.valueOf(editTextWeight.getText().toString()).floatValue();
                        if (initWeightUnit.equals("lb")) {
                            pesoSaveDEF = TabFragment5.lbtokg(pesoSave);
                        } else if (initWeightUnit.equals("stone")) {
                            pesoSaveDEF = TabFragment5.stonetokg(pesoSave);
                        } else {
                            pesoSaveDEF = pesoSave;
                        }
                        edtTxtW.setText(editTextWeight.getText().toString());
                        if (rowsNumDateNote == 1) {
                            selectedNote = db.readNote(activeUID, sDateKey);
                            initializeNote();
                            db.updateNote(new Note(activeUID, activeUID, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, pesoSaveDEF, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
                            return;
                        }
                        rowsNumMoodUid = db.countRowsMood(activeUID);
                        rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
                        rowsNumMucusUid = db.countRowsMucus(activeUID);
                        rowsNumPillUid = db.countRowsPills(activeUID);
                        moodsstringdefault = TabFragment5.fillString(rowsNumMoodUid, '0');
                        symptomsStringDefault = TabFragment5.fillString(rowsNumSymptomsUid, '0');
                        mucusStringDefault = TabFragment5.fillString(rowsNumMucusUid, '0');
                        pillStringDefault = TabFragment5.fillString(rowsNumPillUid, '0');
                        db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsStringDefault, mucusStringDefault, moodsstringdefault, 0, 0, 0, 0, pillStringDefault, 0, 0.0f, pesoSaveDEF, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
                    }
                }).negativeText((int) R.string.dialog_cancel).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).build();
                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                View viewD = dialog.getCustomView();
                editTextWeight = (EditText) viewD.findViewById(R.id.editTextWeight);
                unitaPesoTV = (TextView) viewD.findViewById(R.id.unitaPeso);
                if (initWeightUnit.equals("kg")) {
                    newStringW2 = getString(R.string.units_weight_kg);
                }
                if (initWeightUnit.equals("lb")) {
                    newStringW2 = getString(R.string.units_weight_lb);
                }
                if (initWeightUnit.equals("stone")) {
                    newStringW2 = getString(R.string.units_weight_stone);
                }
                editTextWeight.setText(Float.toString(TabFragment5.round(pesoSaveDEF, 1)));
                unitaPesoTV.setText(newStringW2);
                dialog.show();
            }
        });
        relTemp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog = new MaterialDialog.Builder(v.getContext()).title((int) R.string.other_temperature_title).iconRes(R.mipmap.ic_temp).customView(R.layout.dialog_customview_temp, true).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        editTextTemp = (EditText) dialog.getCustomView().findViewById(R.id.editTextTemp);
                        tempSave = Float.valueOf(editTextTemp.getText().toString()).floatValue();
                        if (initTempUnit.equals("F")) {
                            tempSaveDEF = TabFragment5.fahrenheittocelsius(tempSave);
                        } else {
                            tempSaveDEF = tempSave;
                        }
                        edtTxtT.setText(editTextTemp.getText().toString());
                        if (rowsNumDateNote == 1) {
                            selectedNote = db.readNote(activeUID, sDateKey);
                            initializeNote();
                            db.updateNote(new Note(activeUID, activeUID, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, tempSaveDEF, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
                            return;
                        }
                        rowsNumMoodUid = db.countRowsMood(activeUID);
                        rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
                        rowsNumMucusUid = db.countRowsMucus(activeUID);
                        rowsNumPillUid = db.countRowsPills(activeUID);
                        moodsstringdefault = TabFragment5.fillString(rowsNumMoodUid, '0');
                        symptomsStringDefault = TabFragment5.fillString(rowsNumSymptomsUid, '0');
                        mucusStringDefault = TabFragment5.fillString(rowsNumMucusUid, '0');
                        pillStringDefault = TabFragment5.fillString(rowsNumPillUid, '0');
                        db.insertNote(new Note(activeUID, activeUID, sDateKey, "", "", symptomsStringDefault, mucusStringDefault, moodsstringdefault, 0, 0, 0, 0, pillStringDefault, 0, tempSaveDEF, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
                    }
                }).negativeText((int) R.string.dialog_cancel).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).build();
                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                View viewD = dialog.getCustomView();
                editTextTemp = (EditText) viewD.findViewById(R.id.editTextTemp);
                unitaTempTV = (TextView) viewD.findViewById(R.id.unitaTemp);
                if (initTempUnit.equals("C")) {
                    newStringT2 = getString(R.string.units_temperature_C);
                } else {
                    newStringT2 = getString(R.string.units_temperature_F);
                }
                editTextTemp.setText(Float.toString(TabFragment5.round(tempSaveDEF, 1)));
                unitaTempTV.setText(newStringT2);
                dialog.show();
            }
        });
        imageQM.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UnitsActivity.class));
            }
        });
        imageBookmark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openGommo = new Intent(getActivity(), PillsFragment5Activity.class);
                openGommo.putExtra("datekey", sDateKey);
                startActivity(openGommo);
            }
        });
        switchMedicines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    imageBookmark.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getActivity(), PillsFragment5Activity.class);
                    intent.putExtra("datekey", sDateKey);
                    startActivity(intent);
                    return;
                }
                imageBookmark.setVisibility(View.INVISIBLE);
                db.updateNote(new Note(idNote, uidNote, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, pillStringDefault, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
            }
        });
        return view;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
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

    public static float celsiustofahrenheit(float input) {
        return ((9.0f * input) / 5.0f) + 32.0f;
    }

    public static float fahrenheittocelsius(float input) {
        return ((input - 32.0f) * 5.0f) / 9.0f;
    }

    public static float kgtolb(float input) {
        return input * 2.2046f;
    }

    public static float kgtostone(float input) {
        return input * 0.157473f;
    }

    public static float lbtokg(float input) {
        return input / 2.2046f;
    }

    public static float stonetokg(float input) {
        return input / 0.157473f;
    }

    public static float round(float d, int decimalPlace) {
        return new BigDecimal(Float.toString(d)).setScale(decimalPlace, 4).floatValue();
    }

    public static String fillString(int count, char c) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
