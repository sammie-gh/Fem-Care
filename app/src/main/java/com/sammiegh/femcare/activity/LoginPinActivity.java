package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.User;

public class LoginPinActivity extends AppCompatActivity {
    String answer;
    EditText answerInput;
    int avatar;
    JCGSQLiteHelper db;
    MaterialDialog dialog;
    String email;
    int id;
    ImageView imgLostPin;
    ImageView imgSecQ;
    private IndicatorDots mIndicatorDots;
    private PinLockListener mPinLockListener = new PinLockListener() {
        public void onComplete(String pin) {
            db = new JCGSQLiteHelper(getApplicationContext());
            int activeUID = db.readActiveUID();
            selectedUser = db.readUser(activeUID);
            initializeUser();
            if (pin.equals(password)) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
            Toast toast = Toast.makeText(LoginPinActivity.this, getString(R.string.reset_pin_error), Toast.LENGTH_SHORT);
            toast.setGravity(17, 0, 0);
            toast.show();
            Intent intent2 = new Intent(getApplicationContext(), LoginPinActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }

        public void onEmpty() {

        }

        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };
    private PinLockView mPinLockView;
    String password;
    View positiveAction;
    String question;
    TextView questionOutput;
    User selectedUser;
    int status;
    String subject = "";
    int theme;
    String txtmsg = "";
    int uid;
    String username;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.db = new JCGSQLiteHelper(getApplicationContext());
        this.selectedUser = this.db.readUser(this.db.readActiveUID());
        initializeUser();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login_pin);
        this.mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        this.mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        this.mPinLockView.attachIndicatorDots(this.mIndicatorDots);
        this.mPinLockView.setPinLockListener(this.mPinLockListener);
        this.mPinLockView.setPinLength(4);
        this.mPinLockView.setTextColor(getResources().getColor(R.color.white));
        this.imgSecQ = (ImageView) findViewById(R.id.imgQuestion);
        this.imgLostPin = (ImageView) findViewById(R.id.imgRetrieve);
        this.imgLostPin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog = new MaterialDialog.Builder(LoginPinActivity.this).title((int) R.string.secret_retrieve_mail).iconRes(R.mipmap.ic_settings_backup_restore_black_48dp).customView(R.layout.dialog_customview_login_email, true).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        answerInput = (EditText) dialog.getCustomView().findViewById(R.id.edtTxtAnswer);
                        String answIn = answerInput.getText().toString();
                        int activeUID = db.readActiveUID();
                        selectedUser = db.readUser(activeUID);
                        initializeUser();
                        if (answIn.equals(email)) {
                            JCGSQLiteHelper jCGSQLiteHelper = db;
                            JCGSQLiteHelper jCGSQLiteHelper2 = jCGSQLiteHelper;
                            jCGSQLiteHelper2.updateUser(new User(id, uid, status, username, "", email, "", "", theme, avatar));
                            Toast toast = Toast.makeText(LoginPinActivity.this, getString(R.string.reset_pin_done), Toast.LENGTH_SHORT);
                            toast.setGravity(17, 0, 0);
                            toast.show();
                            subject = getString(R.string.pwd_title);
                            txtmsg = getString(R.string.pwd_msgw1) + " " + password + getString(R.string.pwd_msgw2);
                            String[] TO = {email};
                            Intent intent = new Intent("android.intent.action.SEND");
                            intent.setData(Uri.parse("mailto:"));
                            intent.setType("text/html; charset=utf-8");
                            intent.putExtra("android.intent.extra.EMAIL", TO);
                            intent.putExtra("android.intent.extra.CC", new String[]{""});
                            intent.putExtra("android.intent.extra.SUBJECT", subject);
                            intent.putExtra("android.intent.extra.TEXT", txtmsg);
                            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);
                            finish();
                            return;
                        }
                        Toast toast2 = Toast.makeText(LoginPinActivity.this, getString(R.string.rec_email_err), Toast.LENGTH_SHORT);
                        toast2.setGravity(17, 0, 0);
                        toast2.show();
                        Intent intent3 = new Intent(getApplicationContext(), LoginPinActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent3);
                        finish();
                    }
                }).positiveText((int) R.string.dialog_reset).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).build();
                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                dialog.show();
            }
        });
        this.imgSecQ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog = new MaterialDialog.Builder(LoginPinActivity.this).title((int) R.string.reset_pin_title).iconRes(R.mipmap.ic_pin_reset).customView(R.layout.dialog_customview_login_question, true).cancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        answerInput = (EditText) dialog.getCustomView().findViewById(R.id.edtTxtAnswer);
                        String answIn = answerInput.getText().toString();
                        int activeUID = db.readActiveUID();
                        selectedUser = db.readUser(activeUID);
                        initializeUser();
                        if (answIn.equals(answer)) {
                            JCGSQLiteHelper jCGSQLiteHelper = db;
                            JCGSQLiteHelper jCGSQLiteHelper2 = jCGSQLiteHelper;
                            jCGSQLiteHelper2.updateUser(new User(id, uid, status, username, "", email, "", "", theme, avatar));
                            Toast toast = Toast.makeText(LoginPinActivity.this, getString(R.string.reset_pin_done), Toast.LENGTH_SHORT);
                            toast.setGravity(17, 0, 0);
                            toast.show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            return;
                        }
                        Toast toast2 = Toast.makeText(LoginPinActivity.this, getString(R.string.reset_pin_error), Toast.LENGTH_SHORT);
                        toast2.setGravity(17, 0, 0);
                        toast2.show();
                        Intent intent2 = new Intent(getApplicationContext(), LoginPinActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        finish();
                    }
                }).positiveText((int) R.string.dialog_reset).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).build();
                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                questionOutput = (TextView) dialog.getCustomView().findViewById(R.id.txtQuestion);
                questionOutput.setText(question);
                dialog.show();
            }
        });
    }

    public void initializeUser() {
        this.id = this.selectedUser.getId();
        this.uid = this.selectedUser.getUid();
        this.status = this.selectedUser.getStatus();
        this.username = this.selectedUser.getUsername();
        this.password = this.selectedUser.getPassword();
        this.email = this.selectedUser.getEmail();
        this.question = this.selectedUser.getQuestion();
        this.answer = this.selectedUser.getAnswer();
        this.theme = this.selectedUser.getTheme();
        this.avatar = this.selectedUser.getAvatar();
    }
}
