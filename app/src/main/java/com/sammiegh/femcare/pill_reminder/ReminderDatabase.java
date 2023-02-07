package com.sammiegh.femcare.pill_reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ReminderDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ReminderDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_DATE = "date";
    private static final String KEY_ID = "id";
    private static final String KEY_REPEAT = "repeat";
    private static final String KEY_REPEAT_NO = "repeat_no";
    private static final String KEY_REPEAT_TYPE = "repeat_type";
    private static final String KEY_TIME = "time";
    private static final String KEY_TITLE = "title";
    private static final String TABLE_REMINDERS = "ReminderTable";

    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ReminderTable(id INTEGER PRIMARY KEY,title TEXT,date TEXT,time INTEGER,repeat BOOLEAN,repeat_no INTEGER,repeat_type TEXT,active BOOLEAN)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ReminderTable");
            onCreate(sQLiteDatabase);
        }
    }

    public int addReminder(Reminder reminder) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", reminder.getTitle());
        contentValues.put(KEY_DATE, reminder.getDate());
        contentValues.put(KEY_TIME, reminder.getTime());
        contentValues.put(KEY_REPEAT, reminder.getRepeat());
        contentValues.put(KEY_REPEAT_NO, reminder.getRepeatNo());
        contentValues.put(KEY_REPEAT_TYPE, reminder.getRepeatType());
        contentValues.put("active", reminder.getActive());
        long insert = writableDatabase.insert(TABLE_REMINDERS, (String) null, contentValues);
        writableDatabase.close();
        return (int) insert;
    }

    public Reminder getReminder(int i) {
        Cursor query = getReadableDatabase().query(TABLE_REMINDERS, new String[]{KEY_ID, "title", KEY_DATE, KEY_TIME, KEY_REPEAT, KEY_REPEAT_NO, KEY_REPEAT_TYPE, "active"}, "id=?", new String[]{String.valueOf(i)}, (String) null, (String) null, (String) null, (String) null);
        if (query != null) {
            query.moveToFirst();
        }
        return new Reminder(Integer.parseInt(query.getString(0)), query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getString(6), query.getString(7));
    }

    public List<Reminder> getAllReminders() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM ReminderTable", (String[]) null);
        if (rawQuery.moveToFirst()) {
            do {
                Reminder reminder = new Reminder();
                reminder.setID(Integer.parseInt(rawQuery.getString(0)));
                reminder.setTitle(rawQuery.getString(1));
                reminder.setDate(rawQuery.getString(2));
                reminder.setTime(rawQuery.getString(3));
                reminder.setRepeat(rawQuery.getString(4));
                reminder.setRepeatNo(rawQuery.getString(5));
                reminder.setRepeatType(rawQuery.getString(6));
                reminder.setActive(rawQuery.getString(7));
                arrayList.add(reminder);
            } while (rawQuery.moveToNext());
        }
        return arrayList;
    }

    public int getRemindersCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM ReminderTable", (String[]) null);
        rawQuery.close();
        return rawQuery.getCount();
    }

    public int updateReminder(Reminder reminder) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", reminder.getTitle());
        contentValues.put(KEY_DATE, reminder.getDate());
        contentValues.put(KEY_TIME, reminder.getTime());
        contentValues.put(KEY_REPEAT, reminder.getRepeat());
        contentValues.put(KEY_REPEAT_NO, reminder.getRepeatNo());
        contentValues.put(KEY_REPEAT_TYPE, reminder.getRepeatType());
        contentValues.put("active", reminder.getActive());
        return writableDatabase.update(TABLE_REMINDERS, contentValues, "id=?", new String[]{String.valueOf(reminder.getID())});
    }

    public void deleteReminder(Reminder reminder) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_REMINDERS, "id=?", new String[]{String.valueOf(reminder.getID())});
        writableDatabase.close();
    }
}
