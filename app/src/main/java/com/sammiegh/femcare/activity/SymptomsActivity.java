package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.adepter.SymptomAdapter;
import com.sammiegh.femcare.model.User;

public class SymptomsActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    TextView textView;
    int activeUID;
    String answer;
    int avatar;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    MaterialDialog dialogModify;
    String email;
    int id;
    String password;
    String question;
    String selectedId;
    String selectedSymptomName;
    User selectedUser;
    int status;
    private ListView symptomItems;
    int uid;
    String username;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        db = new JCGSQLiteHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.menu_note_symptoms));

        activeUID = db.readActiveUID();
        selectedUser = db.readUser(activeUID);
        initializeUser();
        cursorListEntry = db.readAllSymptoms(activeUID);
        symptomItems = (ListView) findViewById(R.id.listDefSymptom);
        linearLayout = (LinearLayout) findViewById(R.id.layShowNewSymptom);
        textView = (TextView) findViewById(R.id.txtSymptomname);
        symptomItems.setAdapter(new SymptomAdapter(this, cursorListEntry, 0));
        symptomItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedId = ((TextView) view.findViewById(R.id.idTxtIdSymptom)).getText().toString();
                selectedSymptomName = ((TextView) view.findViewById(R.id.txtSymptomname)).getText().toString();
                dialogModify = new MaterialDialog.Builder(SymptomsActivity.this).title((int) R.string.dialog_symptom_edit).iconRes(R.mipmap.ic_mode_edit_black_48dp).inputType(1).input((CharSequence) "", (CharSequence) selectedSymptomName, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
                    public void onInput(MaterialDialog dialog, CharSequence inputSymptomName) {
                        db.updateSymptomName(activeUID, Integer.parseInt(selectedId), selectedSymptomName.toString());
                    }
                }).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                    }
                }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).show();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, NewSymptomActivity.class));
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
