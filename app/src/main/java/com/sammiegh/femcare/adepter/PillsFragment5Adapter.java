package com.sammiegh.femcare.adepter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;

public class PillsFragment5Adapter extends CursorAdapter {
    String pillDefaultName = "pill_default_name";
    String moodsStringDefault;
    String mucusstringdefault;
    String pillstringdefault;
    String symptomsStringDefault;
    int activeUID;
    char charbox;
    String dateNote;
    JCGSQLiteHelper db;
    int diastolic;
    float fianchi;
    int gommo;
    float height;
    int idNote;
    int idpill;
    String imagename;
    String initTempDate;
    int intimate;
    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
    char pillRecordCharCheck;
    String pillName;
    String pillname;
    int pressure;
    int rowsNumDateNote;
    int rowsNumMoodUid;
    int rowsNumMucusUid;
    int rowsNumPillUid;
    int rowsNumSymptomsUid;
    String secretnote;
    Note selectedNote;
    float seno;
    int sextimes;
    String stringUpPill;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    int uid;
    int uidNote;
    float vita;
    float weight;

    public PillsFragment5Adapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_note_medicines, parent, false);
    }

    public void bindView(View view, Context context, final Cursor cursor) {
        CheckBox checkPillFrag2 = (CheckBox) view.findViewById(R.id.checkBoxHiddenPillFrag2);
        final int position = cursor.getPosition();
        TextView txtPillName = (TextView) view.findViewById(R.id.txtPillname);
        ImageView imgPill = (ImageView) view.findViewById(R.id.imgPill);
        idpill = cursor.getInt(cursor.getColumnIndexOrThrow("idpill"));
        pillname = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        imagename = cursor.getString(cursor.getColumnIndexOrThrow("imagename"));
        ((TextView) view.findViewById(R.id.idTxtIdPill)).setText(String.valueOf(idpill));
        pillName = "";
        db = new JCGSQLiteHelper(context);
        activeUID = db.readActiveUID();
        initTempDate = db.readKeySetting(uid, "tempdate");
        rowsNumDateNote = db.countRowsNote(activeUID, initTempDate);
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, initTempDate);
            initializeNote();
            pillRecordCharCheck = pill.charAt(idpill);
            if (pillRecordCharCheck == '1') {
                checkPillFrag2.setChecked(true);
            } else {
                checkPillFrag2.setChecked(false);
            }
        } else {
            checkPillFrag2.setChecked(false);
        }
        if (pillname.equals(pillDefaultName)) {
            if (idpill == 0) {
                pillName = context.getResources().getString(R.string.pill_0);
                imgPill.setImageResource(R.mipmap.ic_pill_img_0);
            }
            if (idpill == 1) {
                pillName = context.getResources().getString(R.string.pill_1);
                imgPill.setImageResource(R.mipmap.ic_pill_img_1);
            }
            if (idpill == 2) {
                pillName = context.getResources().getString(R.string.pill_2);
                imgPill.setImageResource(R.mipmap.ic_pill_img_2);
            }
            if (idpill == 3) {
                pillName = context.getResources().getString(R.string.pill_3);
                imgPill.setImageResource(R.mipmap.ic_pill_img_3);
            }
            if (idpill == 4) {
                pillName = context.getResources().getString(R.string.pill_4);
                imgPill.setImageResource(R.mipmap.ic_pill_img_4);
            }
            if (idpill == 5) {
                pillName = context.getResources().getString(R.string.pill_5);
                imgPill.setImageResource(R.mipmap.ic_pill_img_5);
            }
            if (idpill == 6) {
                pillName = context.getResources().getString(R.string.pill_6);
                imgPill.setImageResource(R.mipmap.ic_pill_img_6);
            }
            if (idpill == 7) {
                pillName = context.getResources().getString(R.string.pill_7);
                imgPill.setImageResource(R.mipmap.ic_pill_img_7);
            }
            if (idpill == 8) {
                pillName = context.getResources().getString(R.string.pill_8);
                imgPill.setImageResource(R.mipmap.ic_pill_img_8);
            }
            if (idpill == 9) {
                pillName = context.getResources().getString(R.string.pill_9);
                imgPill.setImageResource(R.mipmap.ic_pill_img_9);
            }
            if (idpill == 10) {
                pillName = context.getResources().getString(R.string.pill_10);
                imgPill.setImageResource(R.mipmap.ic_pill_img_10);
            }
            if (idpill == 11) {
                pillName = context.getResources().getString(R.string.pill_11);
                imgPill.setImageResource(R.mipmap.ic_pill_img_11);
            }
            if (idpill == 12) {
                pillName = context.getResources().getString(R.string.pill_12);
                imgPill.setImageResource(R.mipmap.ic_pill_img_12);
            }
            if (idpill == 13) {
                pillName = context.getResources().getString(R.string.pill_13);
                imgPill.setImageResource(R.mipmap.ic_pill_img_13);
            }
            if (idpill == 14) {
                pillName = context.getResources().getString(R.string.pill_14);
                imgPill.setImageResource(R.mipmap.ic_pill_img_14);
            }
            if (idpill == 15) {
                pillName = context.getResources().getString(R.string.pill_15);
                imgPill.setImageResource(R.mipmap.ic_pill_img_15);
            }
            if (idpill == 16) {
                pillName = context.getResources().getString(R.string.pill_16);
                imgPill.setImageResource(R.mipmap.ic_pill_img_16);
            }
            if (idpill == 17) {
                pillName = context.getResources().getString(R.string.pill_17);
                imgPill.setImageResource(R.mipmap.ic_pill_img_17);
            }
            if (idpill == 18) {
                pillName = context.getResources().getString(R.string.pill_18);
                imgPill.setImageResource(R.mipmap.ic_pill_img_18);
            }
            if (idpill == 19) {
                pillName = context.getResources().getString(R.string.pill_19);
                imgPill.setImageResource(R.mipmap.ic_pill_img_19);
            }
            if (idpill == 20) {
                pillName = context.getResources().getString(R.string.pill_20);
                imgPill.setImageResource(R.mipmap.ic_pill_img_20);
            }
        } else {
            pillName = db.selectCustomMoodName(activeUID, idpill);
            if (imagename.equals("0")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_0);
            }
            if (imagename.equals("1")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_1);
            }
            if (imagename.equals("2")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_2);
            }
            if (imagename.equals("3")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_3);
            }
            if (imagename.equals("4")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_4);
            }
            if (imagename.equals("5")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_5);
            }
            if (imagename.equals("6")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_6);
            }
            if (imagename.equals("7")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_7);
            }
            if (imagename.equals("8")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_8);
            }
            if (imagename.equals("9")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_9);
            }
            if (imagename.equals("10")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_10);
            }
            if (imagename.equals("11")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_11);
            }
            if (imagename.equals("12")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_12);
            }
            if (imagename.equals("13")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_13);
            }
            if (imagename.equals("14")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_14);
            }
            if (imagename.equals("15")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_15);
            }
            if (imagename.equals("16")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_16);
            }
            if (imagename.equals("17")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_17);
            }
            if (imagename.equals("18")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_18);
            }
            if (imagename.equals("19")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_19);
            }
            if (imagename.equals("20")) {
                imgPill.setImageResource(R.mipmap.ic_pill_img_20);
            }
        }
        if (db.findCustomMoodName(activeUID, idpill) == 1 && pillname.equals(pillDefaultName)) {
            pillName = db.selectPillName(activeUID, idpill);
        }
        txtPillName.setText(pillName);
        checkPillFrag2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cursor.moveToPosition(position);
                if (((CheckBox) v).isChecked()) {
                    charbox = '1';
                } else {
                    charbox = '0';
                }
                if (rowsNumDateNote == 1) {
                    selectedNote = db.readNote(activeUID, initTempDate);
                    initializeNote();
                    char[] pillsNowToChars = pill.toCharArray();
                    pillsNowToChars[position] = charbox;
                    stringUpPill = String.valueOf(pillsNowToChars);
                    db.updateNote(new Note(activeUID, activeUID, dateNote, note, secretnote, symptoms, mucus, moods, intimate, gommo, sextimes, numorgasm, stringUpPill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
                    return;
                }
                rowsNumMoodUid = db.countRowsMood(activeUID);
                rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
                rowsNumMucusUid = db.countRowsMucus(activeUID);
                rowsNumPillUid = db.countRowsPills(activeUID);
                moodsStringDefault = PillsFragment5Adapter.fillString(rowsNumMoodUid, '0');
                symptomsStringDefault = PillsFragment5Adapter.fillString(rowsNumSymptomsUid, '0');
                mucusstringdefault = PillsFragment5Adapter.fillString(rowsNumMucusUid, '0');
                pillstringdefault = PillsFragment5Adapter.fillString(rowsNumPillUid, '0');
                char[] pillsNowToChars2 = pillstringdefault.toCharArray();
                pillsNowToChars2[position] = charbox;
                stringUpPill = String.valueOf(pillsNowToChars2);
                db.insertNote(new Note(activeUID, activeUID, initTempDate, "", "", symptomsStringDefault, mucusstringdefault, moodsStringDefault, 0, 0, 0, 0, stringUpPill, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
            }
        });
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
}
