package com.sammiegh.femcare.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class OpenBackupEmailActivity extends AppCompatActivity {
    File backupDB;
    String backupDBPath = "EvaPeriodOvulTracker//EvaPeriodTracker.db";
    File currentDB;
    String currentDBPath = "//data//com.androworld.evaperiodtracker//databases//EvaPeriodTracker.db";
    File data;
    MaterialDialog dialogEmail;
    FileChannel dst;
    File sd;
    FileChannel src;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_backup_email);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action.compareTo("android.intent.action.VIEW") == 0) {
            String scheme = intent.getScheme();
            ContentResolver resolver = getContentResolver();
            if (scheme.compareTo("content") == 0) {
                Uri uri = intent.getData();
                Log.v("tag", "Content intent detected: " + action + " : " + intent.getDataString() + " : " + intent.getType() + " : " + getContentName(resolver, uri));
                InputStream input = null;
                try {
                    input = resolver.openInputStream(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                InputStreamToFile(input, "/storage/emulated/0/EvaPeriodOvulTracker/EvaPeriodTracker.db");
            } else if (scheme.compareTo("file") == 0) {
                Uri uri2 = intent.getData();
                Log.v("tag", "File intent detected: " + action + " : " + intent.getDataString() + " : " + intent.getType() + " : " + uri2.getLastPathSegment());
                InputStream input2 = null;
                try {
                    input2 = resolver.openInputStream(uri2);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                InputStreamToFile(input2, "/storage/emulated/0/EvaPeriodOvulTracker/EvaPeriodTracker.db");
            } else if (scheme.compareTo("http") == 0 || scheme.compareTo("ftp") == 0) {
            }
        }
        dialogEmail = new MaterialDialog.Builder(this).title((int) R.string.restore_des_email).content((int) R.string.restore_des_email).iconRes(R.mipmap.ic_email_black_48dp).onPositive(new MaterialDialog.SingleButtonCallback() {
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                try {
                    sd = Environment.getExternalStorageDirectory();
                    data = Environment.getDataDirectory();
                    if (sd.canWrite()) {
                        currentDB = new File(data, currentDBPath);
                        backupDB = new File(sd, backupDBPath);
                        if (currentDB.exists()) {
                            src = new FileInputStream(backupDB).getChannel();
                            dst = new FileOutputStream(currentDB).getChannel();
                            dst.transferFrom(src, 0, src.size());
                            src.close();
                            dst.close();
                            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.restore_txt), Toast.LENGTH_LONG);
                            toast.setGravity(17, 0, 0);
                            toast.show();
                        }
                    }
                } catch (Exception e) {
                    Toast toast2 = Toast.makeText(getApplicationContext(), getString(R.string.err_restore_txt), Toast.LENGTH_SHORT);
                    toast2.setGravity(17, 0, 0);
                    toast2.show();
                }
                startActivity(new Intent(OpenBackupEmailActivity.this, MainActivity.class));
                finish();
            }
        }).cancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).show();
    }

    private String getContentName(ContentResolver resolver, Uri uri) {
        Cursor cursor = resolver.query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        cursor.moveToFirst();
        int nameIndex = cursor.getColumnIndex("_display_name");
        if (nameIndex >= 0) {
            return cursor.getString(nameIndex);
        }
        return null;
    }

    private void InputStreamToFile(InputStream in, String file) {
        try {
            OutputStream out = new FileOutputStream(new File(file));
            byte[] buffer = new byte[1024];
            while (true) {
                int size = in.read(buffer);
                if (size != -1) {
                    out.write(buffer, 0, size);
                } else {
                    out.close();
                    return;
                }
            }
        } catch (Exception e) {
            Log.e("MainActivity", "InputStreamToFile exception: " + e.getMessage());
        }
    }
}
