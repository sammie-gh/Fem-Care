package com.sammiegh.femcare.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.pill_reminder.Pill_MainActivity;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.adepter.NotificationAdapter;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class NotificationsActivity extends AppCompatActivity {
    int activeUID;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    int id;
    String key;
    Settings selectedSettings;
    int uid;
    String value;
    Button newAlarmButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.settings_notifiche));

        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        cursorListEntry = db.readAllAlarmsType(activeUID);
        ListView notificationItems = (ListView) findViewById(R.id.listDefNotifications);
        notificationItems.setAdapter(new NotificationAdapter(this, cursorListEntry, 0));
        notificationItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent openDetails = new Intent(NotificationsActivity.this, NotificationDetailActivity.class);
                openDetails.putExtra("position", String.valueOf(position));
                startActivity(openDetails);
            }
        });
        newAlarmButton = (Button) findViewById(R.id.NewAlarmButton);
        newAlarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(NotificationsActivity.this, Pill_MainActivity.class));

//                Intent openDetails = new Intent(NotificationsActivity.this, NewAlarmActivity.class);
//                startActivity(openDetails);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_units, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

}
