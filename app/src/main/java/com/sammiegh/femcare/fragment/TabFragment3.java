package com.sammiegh.femcare.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.adepter.TabFragment3Adapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabFragment3 extends Fragment {
    TextView textsymptomname;
    int activeUID;
    String answer;
    int avatar;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    String email;
    int id;

    AdView mAdView;
    Date oggiDateCheck;
    String password;
    String question;
    String sDateKey;
    String sOggiDateCheck;
    User selectedUser;
    int status;
    TextView textDate;
    int uid;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);
        mAdView = (AdView) view.findViewById(R.id.adViewNativeFrag3);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }
        });
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setVisibility(View.VISIBLE);
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
        cursorListEntry = db.readAllSymptomsNONHidden(activeUID);
        ListView symptomItems = (ListView) view.findViewById(R.id.listDefSymptom);
        textsymptomname = (TextView) view.findViewById(R.id.txtSymptomname);
        symptomItems.setAdapter(new TabFragment3Adapter(view.getContext(), cursorListEntry, 0));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
}
