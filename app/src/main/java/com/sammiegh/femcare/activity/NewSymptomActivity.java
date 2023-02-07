package com.sammiegh.femcare.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.adepter.NewSymptomAdapter;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Symptoms;

public class NewSymptomActivity extends AppCompatActivity {
    ImageView symptomchoose;
    int activeUID;
    Cursor cursorGridEntry;
    JCGSQLiteHelper db;
    EditText editNewName;
    int imageChoosedef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_symptom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new JCGSQLiteHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activeUID = db.readActiveUID();
        cursorGridEntry = db.readAllBaseSymptoms(activeUID);
        GridView symptomItems = (GridView) findViewById(R.id.gridChooseSymptoms);
        symptomchoose = (ImageView) findViewById(R.id.imgNewSymptom);
        symptomchoose.setImageResource(R.mipmap.ic_symptom_img_0);
        editNewName = (EditText) findViewById(R.id.editTextSymptom);
        symptomItems.setAdapter(new NewSymptomAdapter(this, cursorGridEntry, 0));
        symptomItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                imageChoosedef = position;
                if (position == 0) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_0);
                }
                if (position == 1) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_1);
                }
                if (position == 2) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_2);
                }
                if (position == 3) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_3);
                }
                if (position == 4) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_4);
                }
                if (position == 5) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_5);
                }
                if (position == 6) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_6);
                }
                if (position == 7) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_7);
                }
                if (position == 8) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_8);
                }
                if (position == 9) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_9);
                }
                if (position == 10) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_10);
                }
                if (position == 11) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_11);
                }
                if (position == 12) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_12);
                }
                if (position == 13) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_13);
                }
                if (position == 14) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_14);
                }
                if (position == 15) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_15);
                }
                if (position == 16) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_16);
                }
                if (position == 17) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_17);
                }
                if (position == 18) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_18);
                }
                if (position == 19) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_19);
                }
                if (position == 20) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_20);
                }
                if (position == 21) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_21);
                }
                if (position == 22) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_22);
                }
                if (position == 23) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_23);
                }
                if (position == 24) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_24);
                }
                if (position == 25) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_25);
                }
                if (position == 26) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_26);
                }
                if (position == 27) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_27);
                }
                if (position == 28) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_28);
                }
                if (position == 29) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_29);
                }
                if (position == 30) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_30);
                }
                if (position == 31) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_31);
                }
                if (position == 32) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_32);
                }
                if (position == 33) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_33);
                }
                if (position == 34) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_34);
                }
                if (position == 35) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_35);
                }
                if (position == 36) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_36);
                }
                if (position == 37) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_37);
                }
                if (position == 38) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_38);
                }
                if (position == 39) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_39);
                }
                if (position == 40) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_40);
                }
                if (position == 41) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_41);
                }
                if (position == 42) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_42);
                }
                if (position == 43) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_43);
                }
                if (position == 44) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_44);
                }
                if (position == 45) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_45);
                }
                if (position == 46) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_46);
                }
                if (position == 47) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_47);
                }
                if (position == 48) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_48);
                }
                if (position == 49) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_49);
                }
                if (position == 50) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_50);
                }
                if (position == 51) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_51);
                }
                if (position == 52) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_52);
                }
                if (position == 53) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_53);
                }
                if (position == 54) {
                    symptomchoose.setImageResource(R.mipmap.ic_symptom_img_54);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mood_and_symptoms, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        int activeUID2 = db.readActiveUID();
        int maxIdSymptom = db.selectMaxSymptomUid(activeUID2);
        db.insertSymptoms(new Symptoms(activeUID2, maxIdSymptom + 1, activeUID2, editNewName.getText().toString(), "", String.valueOf(imageChoosedef), 0, 1, 1));
        Intent intent = new Intent(getApplicationContext(), SymptomsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        return true;
    }
}
