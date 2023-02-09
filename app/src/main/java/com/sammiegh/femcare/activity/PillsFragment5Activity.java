package com.sammiegh.femcare.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.adapter.PillsFragment5Adapter;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PillsFragment5Activity extends AppCompatActivity {
    TextView textpillname;
    int activeUID;
    String answer;
    int avatar;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    String email;
    int id;
    String key;
    Date oggiDateCheck;
    String password;
    ListView pillItems;
    String question;
    String sDateKey;
    String sOggiDateCheck;
    Settings selectedSettings;
    User selectedUser;
    int status;
    TextView textDate;
    int uid;
    String username;
    String value;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_fragment_5);
        textDate = (TextView) findViewById(R.id.txtDate);
        sDateKey = getIntent().getStringExtra("datekey");
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
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        selectedUser = db.readUser(activeUID);
        initializeUser();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        db.updateSettings(new Settings(id, uid, "tempdate", sDateKey));
        cursorListEntry = db.readAllPillsNONHidden(activeUID);
        pillItems = (ListView) findViewById(R.id.listDefPill);
        textpillname = (TextView) findViewById(R.id.txtPillname);
        PillsFragment5Adapter pillsFragment5Adapter = new PillsFragment5Adapter(this, cursorListEntry, 0);
        pillItems.setAdapter(pillsFragment5Adapter);
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
        finish();
        return true;
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

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }
}
