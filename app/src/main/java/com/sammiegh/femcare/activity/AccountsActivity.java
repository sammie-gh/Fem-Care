package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.model.Moods;
import com.sammiegh.femcare.model.Mucus;
import com.sammiegh.femcare.model.Notifications;
import com.sammiegh.femcare.model.Pills;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;
import com.sammiegh.femcare.model.Symptoms;
import com.sammiegh.femcare.model.User;
import com.sammiegh.femcare.adepter.UserAdapter;

import java.util.Locale;

public class AccountsActivity extends AppCompatActivity {
    String answer;
    int avatar;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    MaterialDialog dialogAdd;
    MaterialDialog dialogModify;
    MaterialDialog dialogModifyDef;
    String email;
    int id;
    ImageView imageAdd;
    ImageView imageModify;
    LinearLayout layDefAccount;
    LinearLayout layRelAddUser;
    String password;
    String question;
    String selectedId;
    User selectedUser;
    String selectedUserName;
    int status;
    int theme;
    TextView txtDefUser;
    int uid;
    private ListView userItems;
    String username;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        db = new JCGSQLiteHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.accounts_title));

        selectedUser = db.readUser(db.readActiveUID());
        initializeUser();
        txtDefUser = (TextView) findViewById(R.id.txtDefaultUser);
        imageModify = (ImageView) findViewById(R.id.imgModify);
        imageAdd = (ImageView) findViewById(R.id.imageAdd);
        layRelAddUser = (LinearLayout) findViewById(R.id.layAddNewUser);
        layDefAccount = (LinearLayout) findViewById(R.id.linDefUser);
        txtDefUser.setText(username);
        cursorListEntry = db.readAllUser();
        userItems = (ListView) findViewById(R.id.listDefUser);
        userItems.setAdapter(new UserAdapter(this, cursorListEntry, 0));
        userItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedId = ((TextView) view.findViewById(R.id.idTxtId)).getText().toString();
                selectedUserName = ((TextView) view.findViewById(R.id.txtUsername)).getText().toString();
                dialogModifyDef = new MaterialDialog.Builder(AccountsActivity.this).title((int) R.string.accounts_add_swi_del).iconRes(R.mipmap.ic_dialog_mod_switch_del).positiveText((int) R.string.accounts_add_swi).negativeText((int) R.string.dialog_cancel).neutralText((int) R.string.accounts_add_del).positiveColorRes(R.color.colorGreen).neutralColorRes(R.color.colorAccent).negativeColorRes(R.color.colorGrey).onPositive(new MaterialDialog.SingleButtonCallback() {
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        new MaterialDialog.Builder(AccountsActivity.this).title((int) R.string.accounts_add_swi_account).iconRes(R.mipmap.ic_dialog_switch).content((int) R.string.accounts_add_swi_sure).positiveText((int) R.string.dialog_yes).negativeText((int) R.string.dialog_no).positiveColorRes(R.color.colorGreen).neutralColorRes(R.color.colorAccent).negativeColorRes(R.color.colorGrey)
                                .callback(new MaterialDialog.ButtonCallback() {
                                    @Override
                                    public void onPositive(MaterialDialog dialog) {
                                        db.switchUser(Integer.parseInt(selectedId));
                                        Toast.makeText(getApplicationContext(), getString(R.string.accounts_add_swi_account) + " " + selectedUserName, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), AccountsActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).cancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {

                            }
                        }).show();
                    }
                }).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        new MaterialDialog.Builder(AccountsActivity.this).title((int) R.string.accounts_add_del).iconRes(R.mipmap.ic_delete_black_48dp).content((int) R.string.accounts_add_del_sure).positiveText((int) R.string.dialog_yes).negativeText((int) R.string.dialog_no).neutralColorRes(R.color.colorAccent).negativeColorRes(R.color.colorGrey).callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                db.deleteUser(Integer.parseInt(selectedId));
                                db.deleteSettings(Integer.parseInt(selectedId));
                                db.deleteMoods(Integer.parseInt(selectedId));
                                db.deleteSymptoms(Integer.parseInt(selectedId));
                                db.deleteNotes(Integer.parseInt(selectedId));
                                db.deletePills(Integer.parseInt(selectedId));
                                db.deleteAllPeriods(Integer.parseInt(selectedId));
                                db.deleteMucus(Integer.parseInt(selectedId));
                                db.deleteNotifications(Integer.parseInt(selectedId));
                                Toast.makeText(getApplicationContext(), getString(R.string.accounts_add_del_account) + " " + selectedUserName, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AccountsActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }).cancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {

                            }
                        }).show();
                    }
                }).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }).show();
            }
        });
        layDefAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogModify = new MaterialDialog.Builder(AccountsActivity.this).title((int) R.string.accounts_dialog_edit).iconRes(R.mipmap.ic_mode_edit_black_48dp).inputType(1).input((CharSequence) "", (CharSequence) username, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
                    public void onInput(MaterialDialog dialog, CharSequence inputUsername) {
                        db.updateUser(new User(id, uid, status, inputUsername.toString(), password, email, question, answer, theme, avatar));
                        Intent intent = new Intent(getApplicationContext(), AccountsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).show();
            }
        });
        layRelAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogAdd = new MaterialDialog.Builder(AccountsActivity.this).title((int) R.string.accounts_add_new).iconRes(R.mipmap.ic_add_circle_black_48dp).positiveColorRes(R.color.colorAccent).negativeColorRes(R.color.colorGrey).inputType(1).input((int) R.string.accounts_add_edit_hint, (int) R.string.nada, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
                    public void onInput(MaterialDialog dialog, CharSequence inputUsername) {
                        int newUid = db.selectMaxUidUser() + 1;
                        db.insertUser(new User(newUid, newUid, 1, inputUsername.toString(), "", "", "", "", 0, 0));
                        db.updateUsers(newUid);
                        insertDefaultSettings(newUid);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }).positiveText((int) R.string.accounts_add_create).negativeText((int) R.string.dialog_cancel).show();
            }
        });
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
        theme = selectedUser.getTheme();
        avatar = selectedUser.getAvatar();
    }

    public void insertDefaultSettings(int uidNew) {
        db.insertSettings(new Settings(uidNew, uidNew, "n_mestrual_days", "4"));
        db.insertSettings(new Settings(uidNew, uidNew, "n_cycle_days", "28"));
        db.insertSettings(new Settings(uidNew, uidNew, "n_ovulation_days", "14"));
        db.insertSettings(new Settings(uidNew, uidNew, "first_launch", "0"));
        db.insertSettings(new Settings(uidNew, uidNew, "pregnancy", "0"));
        db.insertSettings(new Settings(uidNew, uidNew, "default_mestrual_days", "DEFAULT"));
        db.insertSettings(new Settings(uidNew, uidNew, "default_cycle_days", "DEFAULT"));
        db.insertSettings(new Settings(uidNew, uidNew, "default_ovulation_days", "DEFAULT"));
        db.insertSettings(new Settings(uidNew, uidNew, "temp_unit", "C"));
        db.insertSettings(new Settings(uidNew, uidNew, "weight_unit", "kg"));
        db.insertSettings(new Settings(uidNew, uidNew, "height_unit", "cm"));
        db.insertSettings(new Settings(uidNew, uidNew, "auto_backup", "0"));
        db.insertSettings(new Settings(uidNew, uidNew, "locale", Locale.getDefault().getLanguage()));
        db.insertSettings(new Settings(uidNew, uidNew, "security_level", "normal"));
        db.insertSettings(new Settings(uidNew, uidNew, "driveid", ""));
        db.insertSettings(new Settings(uidNew, uidNew, "lastbackup", ""));
        db.insertSettings(new Settings(uidNew, uidNew, "tempdate", ""));
        db.insertSettings(new Settings(uidNew, uidNew, "olddate", ""));
        db.insertSettings(new Settings(1, uidNew, "showdayscycle", "1"));
        db.insertSettings(new Settings(1, uidNew, "showmoods", "1"));
        db.insertSettings(new Settings(1, uidNew, "showsymptoms", "1"));
        db.insertSettings(new Settings(1, uidNew, "showmedicines", "1"));
        db.insertSettings(new Settings(1, uidNew, "showovulfertile", "1"));
        db.insertSettings(new Settings(1, uidNew, "showpregnant", "1"));
        db.insertSettings(new Settings(1, uidNew, "showmintimate", "1"));
        db.insertSettings(new Settings(1, uidNew, "showfutperiod", "1"));
        db.insertSettings(new Settings(1, uidNew, "dmestruofinemese", ""));
        db.insertSettings(new Settings(1, uidNew, "dmestruoiniziomese", ""));
        db.insertSettings(new Settings(1, uidNew, "showmoonphases", "0"));
        db.insertSettings(new Settings(1, uidNew, "startdaycaldomlun", "1"));
        db.insertSettings(new Settings(1, uidNew, "firstround", "1"));
        db.insertSettings(new Settings(1, uidNew, "ov1min", ""));
        db.insertSettings(new Settings(1, uidNew, "ov1max", ""));
        db.insertSettings(new Settings(1, uidNew, "ov2min", ""));
        db.insertSettings(new Settings(1, uidNew, "ov2max", ""));
        db.insertSettings(new Settings(1, uidNew, "tempalarm", "0"));
        db.insertSettings(new Settings(1, uidNew, "used", "1"));
        db.insertSettings(new Settings(1, uidNew, "answeruse", "L"));
        db.insertSettings(new Settings(1, uidNew, "temp1", ""));
        db.insertSettings(new Settings(1, uidNew, "temp2", ""));
        db.insertSettings(new Settings(1, uidNew, "temp3", ""));
        db.insertSettings(new Settings(1, uidNew, "temp4", ""));
        db.insertSettings(new Settings(1, uidNew, "temp5", ""));
        int moodsID = uidNew;
        db.insertMoods(new Moods(moodsID, 0, uidNew, "mood_default_name", "", "moodimg0", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 1, uidNew, "mood_default_name", "", "moodimg1", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 2, uidNew, "mood_default_name", "", "moodimg2", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 3, uidNew, "mood_default_name", "", "moodimg3", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 4, uidNew, "mood_default_name", "", "moodimg4", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 5, uidNew, "mood_default_name", "", "moodimg5", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 6, uidNew, "mood_default_name", "", "moodimg6", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 7, uidNew, "mood_default_name", "", "moodimg7", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 8, uidNew, "mood_default_name", "", "moodimg8", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 9, uidNew, "mood_default_name", "", "moodimg9", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 10, uidNew, "mood_default_name", "", "moodimg10", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 11, uidNew, "mood_default_name", "", "moodimg11", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 12, uidNew, "mood_default_name", "", "moodimg12", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 13, uidNew, "mood_default_name", "", "moodimg13", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 14, uidNew, "mood_default_name", "", "moodimg14", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 15, uidNew, "mood_default_name", "", "moodimg15", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 16, uidNew, "mood_default_name", "", "moodimg16", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 17, uidNew, "mood_default_name", "", "moodimg17", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 18, uidNew, "mood_default_name", "", "moodimg18", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 19, uidNew, "mood_default_name", "", "moodimg19", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 20, uidNew, "mood_default_name", "", "moodimg20", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 21, uidNew, "mood_default_name", "", "moodimg21", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 22, uidNew, "mood_default_name", "", "moodimg22", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 23, uidNew, "mood_default_name", "", "moodimg23", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 24, uidNew, "mood_default_name", "", "moodimg24", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 25, uidNew, "mood_default_name", "", "moodimg25", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 26, uidNew, "mood_default_name", "", "moodimg26", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 27, uidNew, "mood_default_name", "", "moodimg27", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 28, uidNew, "mood_default_name", "", "moodimg28", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 29, uidNew, "mood_default_name", "", "moodimg29", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 30, uidNew, "mood_default_name", "", "moodimg30", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 31, uidNew, "mood_default_name", "", "moodimg31", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 32, uidNew, "mood_default_name", "", "moodimg32", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 33, uidNew, "mood_default_name", "", "moodimg33", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 34, uidNew, "mood_default_name", "", "moodimg34", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 35, uidNew, "mood_default_name", "", "moodimg35", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 36, uidNew, "mood_default_name", "", "moodimg36", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 37, uidNew, "mood_default_name", "", "moodimg37", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 38, uidNew, "mood_default_name", "", "moodimg38", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 39, uidNew, "mood_default_name", "", "moodimg39", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 40, uidNew, "mood_default_name", "", "moodimg40", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 41, uidNew, "mood_default_name", "", "moodimg41", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 42, uidNew, "mood_default_name", "", "moodimg42", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 43, uidNew, "mood_default_name", "", "moodimg43", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 44, uidNew, "mood_default_name", "", "moodimg44", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 45, uidNew, "mood_default_name", "", "moodimg45", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 46, uidNew, "mood_default_name", "", "moodimg46", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 47, uidNew, "mood_default_name", "", "moodimg47", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 48, uidNew, "mood_default_name", "", "moodimg48", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 49, uidNew, "mood_default_name", "", "moodimg49", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 50, uidNew, "mood_default_name", "", "moodimg50", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 51, uidNew, "mood_default_name", "", "moodimg51", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 52, uidNew, "mood_default_name", "", "moodimg52", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 53, uidNew, "mood_default_name", "", "moodimg53", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 54, uidNew, "mood_default_name", "", "moodimg54", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 55, uidNew, "mood_default_name", "", "moodimg55", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 56, uidNew, "mood_default_name", "", "moodimg56", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 57, uidNew, "mood_default_name", "", "moodimg57", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 58, uidNew, "mood_default_name", "", "moodimg58", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 59, uidNew, "mood_default_name", "", "moodimg59", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 60, uidNew, "mood_default_name", "", "moodimg60", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 61, uidNew, "mood_default_name", "", "moodimg61", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 62, uidNew, "mood_default_name", "", "moodimg62", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 63, uidNew, "mood_default_name", "", "moodimg63", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 64, uidNew, "mood_default_name", "", "moodimg64", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 65, uidNew, "mood_default_name", "", "moodimg65", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 66, uidNew, "mood_default_name", "", "moodimg66", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 67, uidNew, "mood_default_name", "", "moodimg67", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 68, uidNew, "mood_default_name", "", "moodimg68", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 69, uidNew, "mood_default_name", "", "moodimg69", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 70, uidNew, "mood_default_name", "", "moodimg70", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 71, uidNew, "mood_default_name", "", "moodimg71", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 72, uidNew, "mood_default_name", "", "moodimg72", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 73, uidNew, "mood_default_name", "", "moodimg73", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 74, uidNew, "mood_default_name", "", "moodimg74", 0, 1, 0));
        db.insertMoods(new Moods(moodsID, 75, uidNew, "mood_default_name", "", "moodimg75", 0, 1, 0));
        int symptomID = uidNew;
        db.insertSymptoms(new Symptoms(symptomID, 0, uidNew, "symptom_default_name", "", "symptomimg0", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 1, uidNew, "symptom_default_name", "", "symptomimg1", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 2, uidNew, "symptom_default_name", "", "symptomimg2", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 3, uidNew, "symptom_default_name", "", "symptomimg3", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 4, uidNew, "symptom_default_name", "", "symptomimg4", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 5, uidNew, "symptom_default_name", "", "symptomimg5", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 6, uidNew, "symptom_default_name", "", "symptomimg6", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 7, uidNew, "symptom_default_name", "", "symptomimg7", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 8, uidNew, "symptom_default_name", "", "symptomimg8", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 9, uidNew, "symptom_default_name", "", "symptomimg9", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 10, uidNew, "symptom_default_name", "", "symptomimg10", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 11, uidNew, "symptom_default_name", "", "symptomimg11", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 12, uidNew, "symptom_default_name", "", "symptomimg12", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 13, uidNew, "symptom_default_name", "", "symptomimg13", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 14, uidNew, "symptom_default_name", "", "symptomimg14", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 15, uidNew, "symptom_default_name", "", "symptomimg15", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 16, uidNew, "symptom_default_name", "", "symptomimg16", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 17, uidNew, "symptom_default_name", "", "symptomimg17", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 18, uidNew, "symptom_default_name", "", "symptomimg18", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 19, uidNew, "symptom_default_name", "", "symptomimg19", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 20, uidNew, "symptom_default_name", "", "symptomimg20", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 21, uidNew, "symptom_default_name", "", "symptomimg21", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 22, uidNew, "symptom_default_name", "", "symptomimg22", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 23, uidNew, "symptom_default_name", "", "symptomimg23", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 24, uidNew, "symptom_default_name", "", "symptomimg24", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 25, uidNew, "symptom_default_name", "", "symptomimg25", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 26, uidNew, "symptom_default_name", "", "symptomimg26", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 27, uidNew, "symptom_default_name", "", "symptomimg27", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 28, uidNew, "symptom_default_name", "", "symptomimg28", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 29, uidNew, "symptom_default_name", "", "symptomimg29", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 30, uidNew, "symptom_default_name", "", "symptomimg30", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 31, uidNew, "symptom_default_name", "", "symptomimg31", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 32, uidNew, "symptom_default_name", "", "symptomimg32", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 33, uidNew, "symptom_default_name", "", "symptomimg33", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 34, uidNew, "symptom_default_name", "", "symptomimg34", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 35, uidNew, "symptom_default_name", "", "symptomimg35", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 36, uidNew, "symptom_default_name", "", "symptomimg36", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 37, uidNew, "symptom_default_name", "", "symptomimg37", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 38, uidNew, "symptom_default_name", "", "symptomimg38", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 39, uidNew, "symptom_default_name", "", "symptomimg39", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 40, uidNew, "symptom_default_name", "", "symptomimg40", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 41, uidNew, "symptom_default_name", "", "symptomimg41", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 42, uidNew, "symptom_default_name", "", "symptomimg42", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 43, uidNew, "symptom_default_name", "", "symptomimg43", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 44, uidNew, "symptom_default_name", "", "symptomimg44", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 45, uidNew, "symptom_default_name", "", "symptomimg45", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 46, uidNew, "symptom_default_name", "", "symptomimg46", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 47, uidNew, "symptom_default_name", "", "symptomimg47", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 48, uidNew, "symptom_default_name", "", "symptomimg48", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 49, uidNew, "symptom_default_name", "", "symptomimg49", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 50, uidNew, "symptom_default_name", "", "symptomimg50", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 51, uidNew, "symptom_default_name", "", "symptomimg51", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 52, uidNew, "symptom_default_name", "", "symptomimg52", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 53, uidNew, "symptom_default_name", "", "symptomimg53", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 54, uidNew, "symptom_default_name", "", "symptomimg54", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 55, uidNew, "symptom_default_name", "", "symptomimg55", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 56, uidNew, "symptom_default_name", "", "symptomimg56", 0, 1, 0));
        db.insertSymptoms(new Symptoms(symptomID, 57, uidNew, "symptom_default_name", "", "symptomimg57", 0, 1, 0));
        int mucusID = uidNew;
        db.insertMucus(new Mucus(mucusID, 0, uidNew, "mucus_default_name", "", "mucusimg0", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 1, uidNew, "mucus_default_name", "", "mucusimg1", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 2, uidNew, "mucus_default_name", "", "mucusimg2", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 3, uidNew, "mucus_default_name", "", "mucusimg3", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 4, uidNew, "mucus_default_name", "", "mucusimg4", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 5, uidNew, "mucus_default_name", "", "mucusimg5", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 6, uidNew, "mucus_default_name", "", "mucusimg6", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 7, uidNew, "mucus_default_name", "", "mucusimg7", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 8, uidNew, "mucus_default_name", "", "mucusimg8", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 9, uidNew, "mucus_default_name", "", "mucusimg9", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 10, uidNew, "mucus_default_name", "", "mucusimg10", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 11, uidNew, "mucus_default_name", "", "mucusimg11", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 12, uidNew, "mucus_default_name", "", "mucusimg12", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 13, uidNew, "mucus_default_name", "", "mucusimg13", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 14, uidNew, "mucus_default_name", "", "mucusimg14", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 15, uidNew, "mucus_default_name", "", "mucusimg15", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 16, uidNew, "mucus_default_name", "", "mucusimg16", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 17, uidNew, "mucus_default_name", "", "mucusimg17", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 18, uidNew, "mucus_default_name", "", "mucusimg18", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 19, uidNew, "mucus_default_name", "", "mucusimg19", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 20, uidNew, "mucus_default_name", "", "mucusimg20", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 21, uidNew, "mucus_default_name", "", "mucusimg21", 0, 1, 0));
        db.insertMucus(new Mucus(mucusID, 22, uidNew, "mucus_default_name", "", "mucusimg22", 0, 1, 0));
        int pillID = uidNew;
        db.insertPills(new Pills(pillID, 0, pillID, "pill_default_name", "", "pillimg0", 0, 1, 0));
        db.insertPills(new Pills(pillID, 1, pillID, "pill_default_name", "", "pillimg1", 0, 1, 0));
        db.insertPills(new Pills(pillID, 2, pillID, "pill_default_name", "", "pillimg2", 0, 1, 0));
        db.insertPills(new Pills(pillID, 3, pillID, "pill_default_name", "", "pillimg3", 0, 1, 0));
        db.insertPills(new Pills(pillID, 4, pillID, "pill_default_name", "", "pillimg4", 0, 1, 0));
        db.insertPills(new Pills(pillID, 5, pillID, "pill_default_name", "", "pillimg5", 0, 1, 0));
        db.insertPills(new Pills(pillID, 6, pillID, "pill_default_name", "", "pillimg6", 0, 1, 0));
        db.insertPills(new Pills(pillID, 7, pillID, "pill_default_name", "", "pillimg7", 0, 1, 0));
        db.insertPills(new Pills(pillID, 8, pillID, "pill_default_name", "", "pillimg8", 0, 1, 0));
        db.insertPills(new Pills(pillID, 9, pillID, "pill_default_name", "", "pillimg9", 0, 1, 0));
        db.insertPills(new Pills(pillID, 10, pillID, "pill_default_name", "", "pillimg10", 0, 1, 0));
        db.insertPills(new Pills(pillID, 11, pillID, "pill_default_name", "", "pillimg11", 0, 1, 0));
        db.insertPills(new Pills(pillID, 12, pillID, "pill_default_name", "", "pillimg12", 0, 1, 0));
        db.insertPills(new Pills(pillID, 13, pillID, "pill_default_name", "", "pillimg13", 0, 1, 0));
        db.insertPills(new Pills(pillID, 14, pillID, "pill_default_name", "", "pillimg14", 0, 1, 0));
        db.insertPills(new Pills(pillID, 15, pillID, "pill_default_name", "", "pillimg15", 0, 1, 0));
        db.insertPills(new Pills(pillID, 16, pillID, "pill_default_name", "", "pillimg16", 0, 1, 0));
        db.insertPills(new Pills(pillID, 17, pillID, "pill_default_name", "", "pillimg17", 0, 1, 0));
        db.insertPills(new Pills(pillID, 18, pillID, "pill_default_name", "", "pillimg18", 0, 1, 0));
        db.insertPills(new Pills(pillID, 19, pillID, "pill_default_name", "", "pillimg19", 0, 1, 0));
        db.insertPills(new Pills(pillID, 20, pillID, "pill_default_name", "", "pillimg20", 0, 1, 0));
        int notID = uidNew;
        db.insertNotifications(new Notifications(0, 0, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(1, 1, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(2, 2, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(3, 3, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(4, 4, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(5, 5, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(6, 6, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(7, 7, 0, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(10, 0, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(11, 1, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(12, 2, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(13, 3, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(14, 4, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(15, 5, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(16, 6, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(17, 7, 1, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(20, 0, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(21, 1, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(22, 2, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(23, 3, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(24, 4, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(25, 5, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(26, 6, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
        db.insertNotifications(new Notifications(27, 7, 2, notID, "notification_default_name", 0, 0, 0, 7, 0, ""));
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
