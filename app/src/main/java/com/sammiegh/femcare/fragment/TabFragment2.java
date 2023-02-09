package com.sammiegh.femcare.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdView;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.adapter.TabFragment2Adapter;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabFragment2 extends Fragment {
    TextView textMoodname;
    int activeUID;
    String answer;
    int avatar;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    String email;
    int id;
    String key;

    AdView mAdView;
    ListView moodItems;
    Date oggiDateCheck;
    String password;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);



        textDate = (TextView) view.findViewById(R.id.txtDate);
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
        selectedUser = db.readUser(activeUID);
        initializeUser();
        selectedSettings = db.readSettings(activeUID);
        initializeSettings();
        db.updateSettings(new Settings(id, activeUID, "tempdate", sDateKey));
        cursorListEntry = db.readAllMoodsNONHidden(activeUID);
        moodItems = (ListView) view.findViewById(R.id.listDefMood);
        textMoodname = (TextView) view.findViewById(R.id.txtMoodname);
        TabFragment2Adapter tabFragment4Adapter = new TabFragment2Adapter(view.getContext(), cursorListEntry, 0);
        moodItems.setAdapter(tabFragment4Adapter);
        return view;
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
