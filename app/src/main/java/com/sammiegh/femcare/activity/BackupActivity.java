package com.sammiegh.femcare.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sammiegh.femcare.adapter.BackupAdapter;
import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BackupActivity extends AppCompatActivity {
    File backupDB;
    String backupDBPath = "EvaPeriodOvulTracker//EvaPeriodTracker.db";
    String backupMailDBPath = "EvaPeriodOvulTracker//EvaPeriodTracker.pto";
    File currentDB;
    String currentDBPath = "//data//com.androworld.evaperiodtracker//databases//EvaPeriodTracker.db";
    File data;
    JCGSQLiteHelper db;
    MaterialDialog dialogSD;
    FileChannel dst;
    int id;
    Integer[] imgid = {Integer.valueOf(R.mipmap.ic_sd_storage_white_48dp)};
    String key;
    ListView listSettings;
    File sd;
    Settings selectedSettings;
    FileChannel src;
    int uid;
    String value;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.backup_title));

        String[] itemname = getResources().getStringArray(R.array.itemnamebackup);
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 23);
        }
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EvaPeriodOvulTracker/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        BackupAdapter adapter = new BackupAdapter(this, itemname, imgid);
        listSettings = (ListView) findViewById(R.id.listSettings);
        listSettings.setAdapter(adapter);
        try {
            sd = Environment.getExternalStorageDirectory();
            data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                currentDB = new File(data, currentDBPath);
                backupDB = new File(sd, backupDBPath);
                if (currentDB.exists()) {
                    src = new FileInputStream(currentDB).getChannel();
                    dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
        }
        try {
            sd = Environment.getExternalStorageDirectory();
            data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                currentDB = new File(data, currentDBPath);
                backupDB = new File(sd, backupMailDBPath);
                if (currentDB.exists()) {
                    src = new FileInputStream(currentDB).getChannel();
                    dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e2) {
        }
        db = new JCGSQLiteHelper(getApplicationContext());
        listSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long idl) {

                if (position == 0) {
                    dialogSD = new MaterialDialog.Builder(BackupActivity.this).title((int) R.string.backup_title_sd).content((int) R.string.backup_percorso).iconRes(R.mipmap.ic_sd_storage_black_48dp).onPositive(new MaterialDialog.SingleButtonCallback() {
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            try {
                                sd = Environment.getExternalStorageDirectory();
                                data = Environment.getDataDirectory();
                                if (sd.canWrite()) {
                                    currentDB = new File(data, currentDBPath);
                                    backupDB = new File(sd, backupDBPath);
                                    if (currentDB.exists()) {
                                        src = new FileInputStream(currentDB).getChannel();
                                        dst = new FileOutputStream(backupDB).getChannel();
                                        dst.transferFrom(src, 0, src.size());
                                        src.close();
                                        dst.close();
                                        db.updateSettings(new Settings(id, uid, "lastbackup", new SimpleDateFormat("d MMM yyyy, HH:mm").format(Calendar.getInstance().getTime())));
                                        new MaterialDialog.Builder(BackupActivity.this).title((int) R.string.backup_title_sd).iconRes(R.mipmap.ic_done_black_48dp).content((int) R.string.backup_txt).positiveText((int) R.string.dialog_OK).positiveColorRes(R.color.colorPrimary).show();
                                    }
                                }
                            } catch (Exception e) {
                                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.err_backup_txt) + ": " + e, Toast.LENGTH_SHORT);
                                toast.setGravity(17, 0, 0);
                                toast.show();
                            }
                        }
                    }).cancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {

                        }
                    }).positiveText((int) R.string.dialog_OK).negativeText((int) R.string.dialog_cancel).positiveColorRes(R.color.colorPrimary).show();
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
}
