package com.sammiegh.femcare.adepter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.R;

public class TabFragment3Adapter extends CursorAdapter {
    String imagedefault = "symptom_default_name";
    String moodsstringdefault;
    String mucusstringdefault;
    String pillstringdefault;
    String symptomsstringdefault;
    int activeUID;
    String dateNote;
    JCGSQLiteHelper db;
    int diastolic;
    float fianchi;
    int gommo;
    float height;
    int idNote;
    int idsymptom;
    String imagename;
    String initTempDate;
    int intimate;
    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
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
    String stringUpSymptom;
    char symptomRecordCharCheck;
    String symptomName;
    String symptomname;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    int uid;
    int uidNote;
    float vita;
    float weight;

    public TabFragment3Adapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_note_symptom, parent, false);
    }

    public void bindView(View view, Context context, final Cursor cursor) {
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBarSymptom);
        final int position = cursor.getPosition();
        TextView txtSymptomName = (TextView) view.findViewById(R.id.txtSymptomname);
        ImageView imgSymptom = (ImageView) view.findViewById(R.id.imgSymptom);
        idsymptom = cursor.getInt(cursor.getColumnIndexOrThrow("idsymptom"));
        symptomname = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        imagename = cursor.getString(cursor.getColumnIndexOrThrow("imagename"));
        ((TextView) view.findViewById(R.id.idTxtIdSymptom)).setText(String.valueOf(idsymptom));
        symptomName = "";
        db = new JCGSQLiteHelper(context);
        activeUID = db.readActiveUID();
        initTempDate = db.readKeySetting(uid, "tempdate");
        rowsNumDateNote = db.countRowsNote(activeUID, initTempDate);
        if (rowsNumDateNote == 1) {
            selectedNote = db.readNote(activeUID, initTempDate);
            initializeNote();
            symptomRecordCharCheck = symptoms.charAt(idsymptom);
            if (symptomRecordCharCheck == '0') {
                ratingBar.setRating(0.0f);
            }
            if (symptomRecordCharCheck == '1') {
                ratingBar.setRating(1.0f);
            }
            if (symptomRecordCharCheck == '2') {
                ratingBar.setRating(2.0f);
            }
            if (symptomRecordCharCheck == '3') {
                ratingBar.setRating(3.0f);
            }
            if (symptomRecordCharCheck == '4') {
                ratingBar.setRating(4.0f);
            }
            if (symptomRecordCharCheck == '5') {
                ratingBar.setRating(5.0f);
            }
        }
        if (symptomname.equals(imagedefault)) {
            if (idsymptom == 0) {
                symptomName = context.getResources().getString(R.string.symptom_0);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_0);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 1) {
                symptomName = context.getResources().getString(R.string.symptom_1);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_1);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 2) {
                symptomName = context.getResources().getString(R.string.symptom_2);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_2);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 3) {
                symptomName = context.getResources().getString(R.string.symptom_3);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_3);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 4) {
                symptomName = context.getResources().getString(R.string.symptom_4);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_4);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 5) {
                symptomName = context.getResources().getString(R.string.symptom_5);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_5);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 6) {
                symptomName = context.getResources().getString(R.string.symptom_6);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_6);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 7) {
                symptomName = context.getResources().getString(R.string.symptom_7);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_7);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 8) {
                symptomName = context.getResources().getString(R.string.symptom_8);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_8);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 9) {
                symptomName = context.getResources().getString(R.string.symptom_9);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_9);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 10) {
                symptomName = context.getResources().getString(R.string.symptom_10);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_10);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 11) {
                symptomName = context.getResources().getString(R.string.symptom_11);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_11);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 12) {
                symptomName = context.getResources().getString(R.string.symptom_12);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_12);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 13) {
                symptomName = context.getResources().getString(R.string.symptom_13);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_13);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 14) {
                symptomName = context.getResources().getString(R.string.symptom_14);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_14);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 15) {
                symptomName = context.getResources().getString(R.string.symptom_15);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_15);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 16) {
                symptomName = context.getResources().getString(R.string.symptom_16);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_16);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 17) {
                symptomName = context.getResources().getString(R.string.symptom_17);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_17);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 18) {
                symptomName = context.getResources().getString(R.string.symptom_18);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_18);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 19) {
                symptomName = context.getResources().getString(R.string.symptom_19);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_19);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 20) {
                symptomName = context.getResources().getString(R.string.symptom_20);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_20);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 21) {
                symptomName = context.getResources().getString(R.string.symptom_21);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_21);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 22) {
                symptomName = context.getResources().getString(R.string.symptom_22);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_22);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 23) {
                symptomName = context.getResources().getString(R.string.symptom_23);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_23);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 24) {
                symptomName = context.getResources().getString(R.string.symptom_24);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_24);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 25) {
                symptomName = context.getResources().getString(R.string.symptom_25);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_25);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 26) {
                symptomName = context.getResources().getString(R.string.symptom_26);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_26);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 27) {
                symptomName = context.getResources().getString(R.string.symptom_27);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_27);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 28) {
                symptomName = context.getResources().getString(R.string.symptom_28);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_28);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 29) {
                symptomName = context.getResources().getString(R.string.symptom_29);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_29);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 30) {
                symptomName = context.getResources().getString(R.string.symptom_30);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_30);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 31) {
                symptomName = context.getResources().getString(R.string.symptom_31);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_31);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 32) {
                symptomName = context.getResources().getString(R.string.symptom_32);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_32);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 33) {
                symptomName = context.getResources().getString(R.string.symptom_33);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_33);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 34) {
                symptomName = context.getResources().getString(R.string.symptom_34);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_34);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 35) {
                symptomName = context.getResources().getString(R.string.symptom_35);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_35);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 36) {
                symptomName = context.getResources().getString(R.string.symptom_36);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_36);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 37) {
                symptomName = context.getResources().getString(R.string.symptom_37);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_37);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 38) {
                symptomName = context.getResources().getString(R.string.symptom_38);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_38);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 39) {
                symptomName = context.getResources().getString(R.string.symptom_39);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_39);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 40) {
                symptomName = context.getResources().getString(R.string.symptom_40);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_40);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 41) {
                symptomName = context.getResources().getString(R.string.symptom_41);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_41);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 42) {
                symptomName = context.getResources().getString(R.string.symptom_42);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_42);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 43) {
                symptomName = context.getResources().getString(R.string.symptom_43);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_43);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 44) {
                symptomName = context.getResources().getString(R.string.symptom_44);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_44);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 45) {
                symptomName = context.getResources().getString(R.string.symptom_45);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_45);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 46) {
                symptomName = context.getResources().getString(R.string.symptom_46);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_46);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 47) {
                symptomName = context.getResources().getString(R.string.symptom_47);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_47);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 48) {
                symptomName = context.getResources().getString(R.string.symptom_48);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_48);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorList2));
            }
            if (idsymptom == 49) {
                symptomName = context.getResources().getString(R.string.symptom_49);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_49);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 50) {
                symptomName = context.getResources().getString(R.string.symptom_50);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_50);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 51) {
                symptomName = context.getResources().getString(R.string.symptom_51);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_51);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 52) {
                symptomName = context.getResources().getString(R.string.symptom_52);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_52);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 53) {
                symptomName = context.getResources().getString(R.string.symptom_53);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_53);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 54) {
                symptomName = context.getResources().getString(R.string.symptom_54);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_54);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 55) {
                symptomName = context.getResources().getString(R.string.symptom_55);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_55);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 56) {
                symptomName = context.getResources().getString(R.string.symptom_56);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_56);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
            if (idsymptom == 57) {
                symptomName = context.getResources().getString(R.string.symptom_57);
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_57);
                txtSymptomName.setTextColor(context.getResources().getColor(R.color.colorGreen));
            }
        } else {
            symptomName = db.selectCustomSymptomName(activeUID, idsymptom);
            if (imagename.equals("0")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_0);
            }
            if (imagename.equals("1")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_1);
            }
            if (imagename.equals("2")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_2);
            }
            if (imagename.equals("3")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_3);
            }
            if (imagename.equals("4")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_4);
            }
            if (imagename.equals("5")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_5);
            }
            if (imagename.equals("6")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_6);
            }
            if (imagename.equals("7")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_7);
            }
            if (imagename.equals("8")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_8);
            }
            if (imagename.equals("9")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_9);
            }
            if (imagename.equals("10")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_10);
            }
            if (imagename.equals("11")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_11);
            }
            if (imagename.equals("12")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_12);
            }
            if (imagename.equals("13")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_13);
            }
            if (imagename.equals("14")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_14);
            }
            if (imagename.equals("15")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_15);
            }
            if (imagename.equals("16")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_16);
            }
            if (imagename.equals("17")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_17);
            }
            if (imagename.equals("18")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_18);
            }
            if (imagename.equals("19")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_19);
            }
            if (imagename.equals("20")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_20);
            }
            if (imagename.equals("21")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_21);
            }
            if (imagename.equals("22")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_22);
            }
            if (imagename.equals("23")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_23);
            }
            if (imagename.equals("24")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_24);
            }
            if (imagename.equals("25")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_5);
            }
            if (imagename.equals("26")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_26);
            }
            if (imagename.equals("27")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_27);
            }
            if (imagename.equals("28")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_28);
            }
            if (imagename.equals("29")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_29);
            }
            if (imagename.equals("30")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_30);
            }
            if (imagename.equals("31")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_31);
            }
            if (imagename.equals("32")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_32);
            }
            if (imagename.equals("33")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_33);
            }
            if (imagename.equals("34")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_34);
            }
            if (imagename.equals("35")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_35);
            }
            if (imagename.equals("36")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_36);
            }
            if (imagename.equals("37")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_37);
            }
            if (imagename.equals("38")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_38);
            }
            if (imagename.equals("39")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_39);
            }
            if (imagename.equals("40")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_40);
            }
            if (imagename.equals("41")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_41);
            }
            if (imagename.equals("42")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_42);
            }
            if (imagename.equals("43")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_43);
            }
            if (imagename.equals("44")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_44);
            }
            if (imagename.equals("45")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_45);
            }
            if (imagename.equals("46")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_46);
            }
            if (imagename.equals("47")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_47);
            }
            if (imagename.equals("48")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_48);
            }
            if (imagename.equals("49")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_49);
            }
            if (imagename.equals("50")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_50);
            }
            if (imagename.equals("51")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_51);
            }
            if (imagename.equals("52")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_52);
            }
            if (imagename.equals("53")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_53);
            }
            if (imagename.equals("54")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_54);
            }
            if (imagename.equals("55")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_55);
            }
            if (imagename.equals("56")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_56);
            }
            if (imagename.equals("57")) {
                imgSymptom.setImageResource(R.mipmap.ic_symptom_img_57);
            }
        }
        if (db.findCustomSymptomName(activeUID, idsymptom) == 1 && symptomname.equals(imagedefault)) {
            symptomName = db.selectSymptomName(activeUID, idsymptom);
        }
        txtSymptomName.setText(symptomName);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cursor.moveToPosition(position);
                if (!fromUser) {
                    return;
                }
                if (rowsNumDateNote == 1) {
                    selectedNote = db.readNote(activeUID, initTempDate);
                    initializeNote();
                    char[] symptomsNowToChars = symptoms.toCharArray();
                    symptomsNowToChars[position] = Character.forDigit((int) rating, 10);
                    stringUpSymptom = String.valueOf(symptomsNowToChars);
                    db.updateNote(new Note(activeUID, activeUID, dateNote, note, secretnote, stringUpSymptom, mucus, moods, intimate, gommo, sextimes, numorgasm, pill, ovulationtest, temperature, weight, height, seno, vita, fianchi, systolic, diastolic, pressure, testgravidanza));
                    return;
                }
                rowsNumMoodUid = db.countRowsMood(activeUID);
                rowsNumSymptomsUid = db.countRowsSymptoms(activeUID);
                rowsNumMucusUid = db.countRowsMucus(activeUID);
                rowsNumPillUid = db.countRowsPills(activeUID);
                moodsstringdefault = TabFragment3Adapter.fillString(rowsNumMoodUid, '0');
                symptomsstringdefault = TabFragment3Adapter.fillString(rowsNumSymptomsUid, '0');
                mucusstringdefault = TabFragment3Adapter.fillString(rowsNumMucusUid, '0');
                pillstringdefault = TabFragment3Adapter.fillString(rowsNumPillUid, '0');
                char[] symptomsNowToChars2 = symptomsstringdefault.toCharArray();
                symptomsNowToChars2[position] = Character.forDigit((int) rating, 10);
                stringUpSymptom = String.valueOf(symptomsNowToChars2);
                db.insertNote(new Note(activeUID, activeUID, initTempDate, "", "", stringUpSymptom, mucusstringdefault, moodsstringdefault, 0, 0, 0, 0, pillstringdefault, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0));
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
