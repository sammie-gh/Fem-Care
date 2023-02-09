package com.sammiegh.femcare.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.sammiegh.femcare.R;

public class NewPillAdapter extends CursorAdapter {
    int idpill;
    String pillName;

    public NewPillAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_new_pill_grid, parent, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imgPill = (ImageView) view.findViewById(R.id.imgNewPill);
        idpill = cursor.getInt(cursor.getColumnIndexOrThrow("idpill"));
        pillName = "";
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
    }
}
