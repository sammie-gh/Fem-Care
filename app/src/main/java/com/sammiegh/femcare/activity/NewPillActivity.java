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
import com.sammiegh.femcare.adapter.NewPillAdapter;
import com.sammiegh.femcare.model.Pills;
import com.sammiegh.femcare.R;

public class NewPillActivity extends AppCompatActivity {
    ImageView pillChoose;
    int activeUID;
    Cursor cursorGridEntry;
    JCGSQLiteHelper db;
    EditText editNewName;
    int imageChoosedef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new JCGSQLiteHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activeUID = db.readActiveUID();
        cursorGridEntry = db.readAllBasePills(activeUID);
        GridView pillItems = (GridView) findViewById(R.id.gridChoosePills);
        pillChoose = (ImageView) findViewById(R.id.imgNewPill);
        pillChoose.setImageResource(R.mipmap.ic_pill_img_8);
        editNewName = (EditText) findViewById(R.id.editTextPill);
        pillItems.setAdapter(new NewPillAdapter(this, cursorGridEntry, 0));
        pillItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                imageChoosedef = position;
                if (position == 0) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_0);
                }
                if (position == 1) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_1);
                }
                if (position == 2) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_2);
                }
                if (position == 3) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_3);
                }
                if (position == 4) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_4);
                }
                if (position == 5) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_5);
                }
                if (position == 6) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_6);
                }
                if (position == 7) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_7);
                }
                if (position == 8) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_8);
                }
                if (position == 9) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_9);
                }
                if (position == 10) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_10);
                }
                if (position == 11) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_11);
                }
                if (position == 12) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_12);
                }
                if (position == 13) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_13);
                }
                if (position == 14) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_14);
                }
                if (position == 15) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_15);
                }
                if (position == 16) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_16);
                }
                if (position == 17) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_17);
                }
                if (position == 18) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_18);
                }
                if (position == 19) {
                    pillChoose.setImageResource(R.mipmap.ic_pill_img_19);
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
        int maxIdPill = db.selectMaxPillUid(activeUID2);
        db.insertPills(new Pills(activeUID2, maxIdPill + 1, activeUID2, editNewName.getText().toString(), "", String.valueOf(imageChoosedef), 0, 1, 1));
        Intent intent = new Intent(getApplicationContext(), PillsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        return true;
    }
}
