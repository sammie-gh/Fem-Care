package com.sammiegh.femcare.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;

public class NotificationAdapter extends CursorAdapter {
    int activeUID;
    JCGSQLiteHelper db;
    int idnot;
    String name;
    String nameString;
    int type;

    public NotificationAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_notification, parent, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNotificationName = (TextView) view.findViewById(R.id.txtNotificationname);
        idnot = cursor.getInt(cursor.getColumnIndexOrThrow("idnot"));
        name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        type = cursor.getInt(cursor.getColumnIndexOrThrow("type"));
        db = new JCGSQLiteHelper(context);
        if (type == 0) {
            txtNotificationName.setText(context.getResources().getString(R.string.period_alarm));
        } else if (type == 1) {
            txtNotificationName.setText(context.getResources().getString(R.string.fertility_alarm));
        } else if (type == 2) {
            txtNotificationName.setText(context.getResources().getString(R.string.ovulation_alarm));
        } else if (type > 2) {
            nameString = db.selectNameNotification(activeUID, type);
            txtNotificationName.setText(nameString);
        }
    }
}
