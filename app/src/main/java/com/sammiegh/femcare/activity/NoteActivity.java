package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Note;
import com.sammiegh.femcare.adapter.PagerAdapter;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.google.android.material.tabs.TabLayout;

public class NoteActivity extends AppCompatActivity {
    int activeUID;
    String dateKey;
    String date_note;
    JCGSQLiteHelper db;
    int diastolic;
    float fianchi;
    int gommo;
    float height;
    int id;
    int id_note;
    String initDate;
    int intimate;
    String key;
    String moods;
    String mucus;
    String note;
    int numorgasm;
    int ovulationtest;
    String pill;
    int pressure;
    int rowsNumDateNote;
    String secretnote;
    Note selectedNote;
    Settings selectedSettings;
    float seno;
    int sextimes;
    String symptoms;
    int systolic;
    float temperature;
    int testgravidanza;
    int uid;
    int uid_note;
    String value;
    float vita;
    float weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        dateKey = getIntent().getStringExtra("datekey");
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        selectedSettings = db.readSettings(activeUID);
        initDate = db.readKeySetting(activeUID, "tempdate");
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) getString(R.string.menu_note_notes)).setIcon(getResources().getDrawable(R.mipmap.ic_note)));
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) getString(R.string.menu_note_moods)).setIcon(getResources().getDrawable(R.mipmap.ic_smile)));
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) getString(R.string.menu_note_symptoms)).setIcon(getResources().getDrawable(R.mipmap.ic_sintomi)));
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) getString(R.string.menu_note_others)).setIcon(getResources().getDrawable(R.mipmap.ic_pill)));
        tabLayout.setTabGravity(0);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), initDate));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.addnote) {
            return super.onOptionsItemSelected(item);
        }
        new MaterialDialog.Builder(this).title((int) R.string.note_del_q).content((int) R.string.dialog_del_note_sure).iconRes(R.mipmap.ic_delete_black_48dp).positiveText((int) R.string.dialog_yes).negativeText((int) R.string.dialog_no).positiveColorRes(R.color.colorPink4).negativeColorRes(R.color.colorBluTiffany).cancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        }).callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                rowsNumDateNote = db.countRowsNote(activeUID, initDate);
                if (rowsNumDateNote == 1) {
                    Toast.makeText(getApplicationContext(), getString(R.string.note_del), Toast.LENGTH_SHORT).show();
                    activeUID = db.readActiveUID();
                    selectedNote = db.readNote(activeUID, initDate);
                    initializeNote();
                    db.deleteNote(selectedNote, initDate);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.note_del_no), Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }).show();
        return true;
    }

    public void initializeNote() {
        id_note = selectedNote.getId();
        uid_note = selectedNote.getUid();
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

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), CustomCalendarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
