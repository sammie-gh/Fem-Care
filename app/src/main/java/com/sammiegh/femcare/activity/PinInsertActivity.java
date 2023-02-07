package com.sammiegh.femcare.activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.User;

import me.philio.pinentry.PinEntryView;

public class PinInsertActivity extends AppCompatActivity {
    String answer;
    EditText answerInput;
    int avatar;
    Button btnSave;
    JCGSQLiteHelper db;
    MaterialDialog dialog;
    String email;
    EditText emailInput;
    int id;
    String password;
    PinEntryView pinEntry1;
    PinEntryView pinEntry2;
    View positiveAction;
    String question;
    EditText questionInput;
    ScrollView scrollPIN;
    User selectedUser;
    int status;
    String subject = "";
    Switch switchPin;
    int theme;
    String txtmsg = "";
    int uid;
    String username;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_insert);
        db = new JCGSQLiteHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Security");
        selectedUser = db.readUser(db.readActiveUID());
        initializeUser();
        pinEntry1 = (PinEntryView) findViewById(R.id.pin_entry_simple);
        pinEntry2 = (PinEntryView) findViewById(R.id.pin_entry_confirm);
        scrollPIN = (ScrollView) findViewById(R.id.scrollPin);
        switchPin = (Switch) findViewById(R.id.PinSwitch);
        if (password.equals("")) {
            scrollPIN.setVisibility(View.INVISIBLE);
            switchPin.setChecked(false);
        } else {
            scrollPIN.setVisibility(View.VISIBLE);
            switchPin.setChecked(true);
        }
        switchPin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    scrollPIN.setVisibility(View.VISIBLE);
                    return;
                }
                scrollPIN.setVisibility(View.INVISIBLE);
                db.updateUser(new User(id, uid, status, username, "", email, "", "", theme, avatar));
            }
        });
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pinEntry1.getText().toString().trim().length() < 4 || pinEntry2.getText().toString().trim().length() < 4) {
                    Toast toast = Toast.makeText(PinInsertActivity.this, getString(R.string.toast_dialog_corti), Toast.LENGTH_SHORT);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(), PinInsertActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                if (!pinEntry1.getText().toString().equals(pinEntry2.getText().toString())) {
                    Toast toast2 = Toast.makeText(PinInsertActivity.this, getString(R.string.toast_dialog_diff), Toast.LENGTH_SHORT);
                    toast2.setGravity(17, 0, 0);
                    toast2.show();
                    Intent intent2 = new Intent(getApplicationContext(), PinInsertActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                    finish();
                }
                dialog = new MaterialDialog.Builder(PinInsertActivity.this).title((int) R.string.dialog_security_title).iconRes(R.mipmap.ic_dialog_lock).customView(R.layout.dialog_customview_question_answer, true).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent(getApplicationContext(), PinInsertActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                        questionInput = (EditText) dialog.getCustomView().findViewById(R.id.edtTxtQuestion);
                        answerInput = (EditText) dialog.getCustomView().findViewById(R.id.edtTxtAnswer);
                        emailInput = (EditText) dialog.getCustomView().findViewById(R.id.edtEmail);
                        String questIn = questionInput.getText().toString();
                        String answIn = answerInput.getText().toString();
                        String emailIn = emailInput.getText().toString();
                        int activeUID = db.readActiveUID();
                        selectedUser = db.readUser(activeUID);
                        initializeUser();
                        String pinUser = pinEntry1.getText().toString();
                        db.updateUser(new User(id, uid, status, username, pinUser, emailIn, questIn, answIn, theme, avatar));
                        subject = getString(R.string.pwd_title);
                        txtmsg = getString(R.string.pwd_msgw1) + " " + pinUser + getString(R.string.pwd_msgw2);
                        Intent emailIntent = new Intent("android.intent.action.SEND");
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/html; charset=utf-8");
                        emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{emailIn});
                        emailIntent.putExtra("android.intent.extra.CC", new String[]{""});
                        emailIntent.putExtra("android.intent.extra.SUBJECT", subject);
                        emailIntent.putExtra("android.intent.extra.TEXT", txtmsg);
                        Toast toast = Toast.makeText(PinInsertActivity.this, getString(R.string.security_pin_email_question), Toast.LENGTH_SHORT);
                        toast.setGravity(17, 0, 0);
                        toast.show();
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(PinInsertActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).show();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
