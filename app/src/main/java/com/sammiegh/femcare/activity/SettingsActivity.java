package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.adapter.SettingAdapter;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.utils.CustomTypefaceSpan;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.utils.Utils;

public class SettingsActivity extends AppCompatActivity {
    int activeUID;
    String date_note;
    JCGSQLiteHelper db;
    MaterialDialog dialogSettingOption;
    int diastolic;
    float fianchi;
    int gommo;
    float height;
    int id;
    int id_note;
    Integer[] imgid = {
            Integer.valueOf(R.mipmap.ic_durata_mestruazioni),
            Integer.valueOf(R.mipmap.ic_durata_ciclo),
            Integer.valueOf(R.mipmap.ic_ovulazione2),
            Integer.valueOf(R.mipmap.ic_lock_white_48dp),
            Integer.valueOf(R.mipmap.ic_visibility_white_48dp),
            Integer.valueOf(R.mipmap.ic_notifications_white_48dp),
            Integer.valueOf(R.mipmap.ic_sintomi),
            Integer.valueOf(R.mipmap.ic_smile),
            Integer.valueOf(R.mipmap.ic_pill),
            Integer.valueOf(R.mipmap.ic_gravidanza),
            Integer.valueOf(R.mipmap.ic_backup_white_48dp),
            Integer.valueOf(R.mipmap.ic_settings_backup_restore_white_48dp),
            Integer.valueOf(R.mipmap.ic_person_add_white_48dp),
            Integer.valueOf(R.mipmap.ic_unita_di_misura2),
            Integer.valueOf(R.mipmap.ic_translate_white_48dp),
            Integer.valueOf(R.mipmap.ic_grade_white_48dp),
            Integer.valueOf(R.mipmap.ic_share_white_48dp),
            Integer.valueOf(R.mipmap.ic_refresh_white_48dp),
            Integer.valueOf(R.drawable.ic_policy)};
    int intimate;
    String key;
    ListView listSettings;

    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
    int pressure;
    String pwdUserGetty;
    String secretnote;
    Note selectedNote;
    Settings selectedSettings;
    User selectedUser;
    float seno;
    int sextimes;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    int uid;
    int uidNote;
    Vibrator v;
    String value;
    float vita;
    float weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Utils.showBannerAds(this);


        Typeface BillFont = Typeface.createFromAsset(getAssets(), getString(R.string.app_bar_font));
        String actionBarTitle = "Settings";
        SpannableStringBuilder ssb = new SpannableStringBuilder(actionBarTitle);
        ssb.setSpan(new CustomTypefaceSpan("test", BillFont), 0, actionBarTitle.length(), 34);
        getSupportActionBar().setTitle((CharSequence) ssb);
        SettingAdapter adapter = new SettingAdapter(this,
                getResources().getStringArray(R.array.itemname), imgid);
        listSettings = (ListView) findViewById(R.id.listSettings);
        listSettings.setAdapter(adapter);
        v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final SharedPreferences settings = getSharedPreferences("localPreferences", 0);
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        selectedUser = db.readUser(activeUID);
        listSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(SettingsActivity.this, PeriodLengthActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(SettingsActivity.this, CycleLenghtActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(SettingsActivity.this, OvulationActivity.class));
                } else if (position == 3) {
                    pwdUserGetty = db.readPwdUser();
                    if (pwdUserGetty.equals("")) {
                    }
                    startActivity(new Intent(SettingsActivity.this, PinInsertActivity.class));
                } else if (position == 4) {
                    startActivity(new Intent(SettingsActivity.this, ShowHideActivity.class));
                } else if (position == 5) {
                    startActivity(new Intent(SettingsActivity.this, NotificationsActivity.class));
                } else if (position == 6) {
                    startActivity(new Intent(SettingsActivity.this, SymptomsActivity.class));
                } else if (position == 7) {
                    startActivity(new Intent(SettingsActivity.this, MoodsActivity.class));
                } else if (position == 8) {
                    startActivity(new Intent(SettingsActivity.this, PillsActivity.class));
                } else if (position == 9) {
                    startActivity(new Intent(SettingsActivity.this, PregnancyActivity.class));
                } else if (position == 10) {
                    startActivity(new Intent(SettingsActivity.this, BackupActivity.class));
                } else if (position == 11) {
                    startActivity(new Intent(SettingsActivity.this, RestoreActivity.class));
                } else if (position == 12) {
                    startActivity(new Intent(SettingsActivity.this, AccountsActivity.class));
                } else if (position == 13) {
                    startActivity(new Intent(SettingsActivity.this, UnitsActivity.class));
                } else if (position == 14) {
                    startActivity(new Intent(SettingsActivity.this, LanguageActivity.class));
                } else if (position == 15) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.sammiegh.femcare")));
                } else if (position == 16) {
                    String text = getResources().getString(R.string.mess_share) + "\n\n" + getResources().getString(R.string.mess_try) + "\n" + "https://play.google.com/store/apps/details?id=com.sammiegh.femcare";
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", text);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, text));
                } else if (position == 17) {
                    dialogSettingOption = new MaterialDialog.Builder(SettingsActivity.this).title((int) R.string.settings_title_dialog_delete).content((int) R.string.settings_question_dialog_delete).iconRes(R.mipmap.ic_delete_black_48dp).positiveColorRes(R.color.colorAccent).negativeColorRes(R.color.colorGrey).callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            int readActiveUID = db.readActiveUID();
                            settings.edit().putBoolean("isFirstRun", true).apply();
                            v.vibrate(500);
                            Intent intent = new Intent(SettingsActivity.this, SplashScreen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }).cancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                        }
                    }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).show();
                } else if (position == 18) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://doc-hosting.flycricket.io/fem-care-privacy-policy/9850637d-da26-4024-b181-794aa049bb72/privacy"));
                    startActivity(browserIntent);
                }
            }
        });
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    public void initializeNote() {
        id_note = selectedNote.getId();
        uidNote = selectedNote.getUid();
        date_note = selectedNote.getDate();
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
    @Override
    protected void onDestroy() {
        if (Utils.mAdView != null) {
            Utils.mAdView.destroy();
        }
        super.onDestroy();
    }
}
