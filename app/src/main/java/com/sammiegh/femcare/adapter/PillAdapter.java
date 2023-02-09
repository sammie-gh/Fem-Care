package com.sammiegh.femcare.adapter;

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
import com.sammiegh.femcare.R;

public class PillAdapter extends CursorAdapter {
    String IMAGEDEFAULT = "pill_default_name";
    int activeUID;
    int checkBox;
    JCGSQLiteHelper db;
    int idpill;
    String imagename;
    String pill_name;
    String pillname;


    public PillAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_pill, parent, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        CheckBox checkPill = (CheckBox) view.findViewById(R.id.checkBoxHiddenPill);
        TextView txtPillName = (TextView) view.findViewById(R.id.txtPillname);
        ImageView imgPill = (ImageView) view.findViewById(R.id.imgPill);
        idpill = cursor.getInt(cursor.getColumnIndexOrThrow("idpill"));
        pillname = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        imagename = cursor.getString(cursor.getColumnIndexOrThrow("imagename"));
        ((TextView) view.findViewById(R.id.idTxtIdPill)).setText(String.valueOf(idpill));
        pill_name = "";
        db = new JCGSQLiteHelper(context);
        activeUID = db.readActiveUID();
        if (pillname.equals(IMAGEDEFAULT)) {
            if (idpill == 0) {
                pill_name = context.getResources().getString(R.string.pill_0);
                imgPill.setImageResource(R.mipmap.ic_pill_img_0);
            }
            if (idpill == 1) {
                pill_name = context.getResources().getString(R.string.pill_1);
                imgPill.setImageResource(R.mipmap.ic_pill_img_1);
            }
            if (idpill == 2) {
                pill_name = context.getResources().getString(R.string.pill_2);
                imgPill.setImageResource(R.mipmap.ic_pill_img_2);
            }
            if (idpill == 3) {
                pill_name = context.getResources().getString(R.string.pill_3);
                imgPill.setImageResource(R.mipmap.ic_pill_img_3);
            }
            if (idpill == 4) {
                pill_name = context.getResources().getString(R.string.pill_4);
                imgPill.setImageResource(R.mipmap.ic_pill_img_4);
            }
            if (idpill == 5) {
                pill_name = context.getResources().getString(R.string.pill_5);
                imgPill.setImageResource(R.mipmap.ic_pill_img_5);
            }
            if (idpill == 6) {
                pill_name = context.getResources().getString(R.string.pill_6);
                imgPill.setImageResource(R.mipmap.ic_pill_img_6);
            }
            if (idpill == 7) {
                pill_name = context.getResources().getString(R.string.pill_7);
                imgPill.setImageResource(R.mipmap.ic_pill_img_7);
            }
            if (idpill == 8) {
                pill_name = context.getResources().getString(R.string.pill_8);
                imgPill.setImageResource(R.mipmap.ic_pill_img_8);
            }
            if (idpill == 9) {
                pill_name = context.getResources().getString(R.string.pill_9);
                imgPill.setImageResource(R.mipmap.ic_pill_img_9);
            }
            if (idpill == 10) {
                pill_name = context.getResources().getString(R.string.pill_10);
                imgPill.setImageResource(R.mipmap.ic_pill_img_10);
            }
            if (idpill == 11) {
                pill_name = context.getResources().getString(R.string.pill_11);
                imgPill.setImageResource(R.mipmap.ic_pill_img_11);
            }
            if (idpill == 12) {
                pill_name = context.getResources().getString(R.string.pill_12);
                imgPill.setImageResource(R.mipmap.ic_pill_img_12);
            }
            if (idpill == 13) {
                pill_name = context.getResources().getString(R.string.pill_13);
                imgPill.setImageResource(R.mipmap.ic_pill_img_13);
            }
            if (idpill == 14) {
                pill_name = context.getResources().getString(R.string.pill_14);
                imgPill.setImageResource(R.mipmap.ic_pill_img_14);
            }
            if (idpill == 15) {
                pill_name = context.getResources().getString(R.string.pill_15);
                imgPill.setImageResource(R.mipmap.ic_pill_img_15);
            }
            if (idpill == 16) {
                pill_name = context.getResources().getString(R.string.pill_16);
                imgPill.setImageResource(R.mipmap.ic_pill_img_16);
            }
            if (idpill == 17) {
                pill_name = context.getResources().getString(R.string.pill_17);
                imgPill.setImageResource(R.mipmap.ic_pill_img_17);
            }
            if (idpill == 18) {
                pill_name = context.getResources().getString(R.string.pill_18);
                imgPill.setImageResource(R.mipmap.ic_pill_img_18);
            }
            if (idpill == 19) {
                pill_name = context.getResources().getString(R.string.pill_19);
                imgPill.setImageResource(R.mipmap.ic_pill_img_19);
            }
            if (idpill == 20) {
                pill_name = context.getResources().getString(R.string.pill_20);
                imgPill.setImageResource(R.mipmap.ic_pill_img_20);
            }
        } else {
            pill_name = db.selectCustomPillName(activeUID, idpill);
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
        if (db.findCustomPillName(activeUID, idpill) == 1 && pillname.equals(IMAGEDEFAULT)) {
            pill_name = db.selectPillName(activeUID, idpill);
        }
        txtPillName.setText(pill_name);
        if (cursor.getInt(cursor.getColumnIndexOrThrow("hidden")) == 1) {
            checkPill.setChecked(true);
        } else {
            checkPill.setChecked(false);
        }
        checkPill.setTag(Integer.valueOf(idpill));
        checkPill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkBox = 1;
                } else {
                    checkBox = 0;
                }
                db.updatePillCheck(activeUID, ((Integer) v.getTag()).intValue(), checkBox);
            }
        });
    }
}
