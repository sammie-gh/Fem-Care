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
import com.sammiegh.femcare.model.Moods;
import com.sammiegh.femcare.adapter.NewMoodAdapter;
import com.sammiegh.femcare.R;

public class NewMoodActivity extends AppCompatActivity {
    ImageView smilechoose;
    int activeUID;
    Cursor cursorGridEntry;
    JCGSQLiteHelper db;
    EditText editNewName;
    int imageChoosedef;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mood);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new JCGSQLiteHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activeUID = db.readActiveUID();
        cursorGridEntry = db.readAllBaseMoods(activeUID);
        GridView moodItems = (GridView) findViewById(R.id.gridChooseMoods);
        smilechoose = (ImageView) findViewById(R.id.imgNewMood);
        smilechoose.setImageResource(R.mipmap.ic_mood_img_0);
        editNewName = (EditText) findViewById(R.id.editTextMood);
        moodItems.setAdapter(new NewMoodAdapter(this, cursorGridEntry, 0));
        moodItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                imageChoosedef = position;
                if (position == 0) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_0);
                }
                if (position == 1) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_1);
                }
                if (position == 2) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_2);
                }
                if (position == 3) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_3);
                }
                if (position == 4) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_4);
                }
                if (position == 5) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_5);
                }
                if (position == 6) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_6);
                }
                if (position == 7) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_7);
                }
                if (position == 8) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_8);
                }
                if (position == 9) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_9);
                }
                if (position == 10) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_10);
                }
                if (position == 11) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_11);
                }
                if (position == 12) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_12);
                }
                if (position == 13) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_13);
                }
                if (position == 14) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_14);
                }
                if (position == 15) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_15);
                }
                if (position == 16) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_16);
                }
                if (position == 17) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_17);
                }
                if (position == 18) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_18);
                }
                if (position == 19) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_19);
                }
                if (position == 20) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_20);
                }
                if (position == 21) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_21);
                }
                if (position == 22) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_22);
                }
                if (position == 23) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_23);
                }
                if (position == 24) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_24);
                }
                if (position == 25) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_25);
                }
                if (position == 26) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_26);
                }
                if (position == 27) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_27);
                }
                if (position == 28) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_28);
                }
                if (position == 29) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_29);
                }
                if (position == 30) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_30);
                }
                if (position == 31) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_31);
                }
                if (position == 32) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_32);
                }
                if (position == 33) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_33);
                }
                if (position == 34) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_34);
                }
                if (position == 35) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_35);
                }
                if (position == 36) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_36);
                }
                if (position == 37) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_37);
                }
                if (position == 38) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_38);
                }
                if (position == 39) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_39);
                }
                if (position == 40) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_40);
                }
                if (position == 41) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_41);
                }
                if (position == 42) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_42);
                }
                if (position == 43) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_43);
                }
                if (position == 44) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_44);
                }
                if (position == 45) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_45);
                }
                if (position == 46) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_46);
                }
                if (position == 47) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_47);
                }
                if (position == 48) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_48);
                }
                if (position == 49) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_49);
                }
                if (position == 50) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_50);
                }
                if (position == 51) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_51);
                }
                if (position == 52) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_52);
                }
                if (position == 53) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_53);
                }
                if (position == 54) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_54);
                }
                if (position == 55) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_55);
                }
                if (position == 56) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_56);
                }
                if (position == 57) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_57);
                }
                if (position == 58) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_58);
                }
                if (position == 59) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_59);
                }
                if (position == 60) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_60);
                }
                if (position == 61) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_61);
                }
                if (position == 62) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_62);
                }
                if (position == 63) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_63);
                }
                if (position == 64) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_64);
                }
                if (position == 65) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_65);
                }
                if (position == 66) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_66);
                }
                if (position == 67) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_67);
                }
                if (position == 68) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_68);
                }
                if (position == 69) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_69);
                }
                if (position == 70) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_70);
                }
                if (position == 71) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_71);
                }
                if (position == 72) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_72);
                }
                if (position == 73) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_73);
                }
                if (position == 74) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_74);
                }
                if (position == 75) {
                    smilechoose.setImageResource(R.mipmap.ic_mood_img_75);
                }
            }
        });
    }

   @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mood_and_symptoms, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.oktick) {
            return super.onOptionsItemSelected(item);
        }
        int activeUID2 = db.readActiveUID();
        int maxIdMood = db.selectMaxMoodUid(activeUID2);
        db.insertMoods(new Moods(activeUID2, maxIdMood + 1, activeUID2, editNewName.getText().toString(), "", String.valueOf(imageChoosedef), 0, 1, 1));
        Intent intent = new Intent(getApplicationContext(), MoodsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        return true;
    }
}
