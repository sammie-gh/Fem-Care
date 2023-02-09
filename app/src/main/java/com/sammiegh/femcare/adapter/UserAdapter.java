package com.sammiegh.femcare.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;

public class UserAdapter extends CursorAdapter {
    JCGSQLiteHelper db;

    public UserAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_account, parent, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        RadioButton radioUser = (RadioButton) view.findViewById(R.id.radioBtnUser);
        TextView txtUser = (TextView) view.findViewById(R.id.txtUsername);
        int iduder = cursor.getInt(cursor.getColumnIndexOrThrow("uid"));
        int statususer = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
        String username2 = cursor.getString(cursor.getColumnIndexOrThrow("username"));
        ((TextView) view.findViewById(R.id.idTxtId)).setText(String.valueOf(iduder));
        if (statususer == 1) {
            radioUser.setChecked(true);
        } else {
            radioUser.setChecked(false);
        }
        txtUser.setText(username2);
        db = new JCGSQLiteHelper(context);
    }
}
