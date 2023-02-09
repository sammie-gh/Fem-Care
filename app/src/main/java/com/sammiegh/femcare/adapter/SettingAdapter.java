package com.sammiegh.femcare.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class SettingAdapter extends ArrayAdapter<String> {
    private final Activity context;
    JCGSQLiteHelper db;
    int id;
    private final Integer[] imgid;
    private final String[] itemname;
    String key;
    TextView optionalText;
    String pwdUserGetty;
    Settings selectedSettings;
    int uid;
    String value;

    public SettingAdapter(Activity context2, String[] itemname2, Integer[] imgid2) {
        super(context2, R.layout.item_settings, itemname2);
        context = context2;
        itemname = itemname2;
        imgid = imgid2;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = context.getLayoutInflater().inflate(R.layout.item_settings, (ViewGroup) null, true);
        TextView extratxt = (TextView) rowView.findViewById(R.id.txt2Settings);
        optionalText = (TextView) rowView.findViewById(R.id.optionalText);
        ((TextView) rowView.findViewById(R.id.txt1Settings)).setText(itemname[position]);
        ((ImageView) rowView.findViewById(R.id.txtDate)).setImageResource(imgid[position].intValue());
        db = new JCGSQLiteHelper(getContext().getApplicationContext());
        if (position == 0) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_period));
            selectedSettings = db.readSettings(db.readActiveUID());
            initializeSettings();
            String numDaysCycle = db.readKeySetting(uid, "n_mestrual_days");
            if (db.readKeySetting(uid, "default_mestrual_days").equals("DEFAULT")) {
                optionalText.setText(numDaysCycle + " " + getContext().getApplicationContext().getString(R.string.days));
            } else {
                optionalText.setText(getContext().getApplicationContext().getString(R.string.average));
            }
        }
        if (position == 1) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_cycle));
            selectedSettings = db.readSettings(db.readActiveUID());
            initializeSettings();
            String numDaysCycle2 = db.readKeySetting(uid, "n_cycle_days");
            if (db.readKeySetting(uid, "default_cycle_days").equals("DEFAULT")) {
                optionalText.setText(numDaysCycle2 + " " + getContext().getApplicationContext().getString(R.string.days));
            } else {
                optionalText.setText(getContext().getApplicationContext().getString(R.string.average));
            }
        }
        if (position == 2) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_ovulation));
        }
        if (position == 3) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_security));
            pwdUserGetty = db.readPwdUser();
            if (pwdUserGetty.equals("")) {
                optionalText.setText(getContext().getApplicationContext().getString(R.string.off));
            } else {
                optionalText.setText(getContext().getApplicationContext().getString(R.string.on));
            }
        }

        if (position == 4) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_show_hide));
        }
        if (position == 5) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_notification));
        }
        if (position == 6) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.symptoms_custom_new));
        }
        if (position == 7) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.moods_custom_new));
        }
        if (position == 8) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_pill));
        }
        if (position == 9) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_pregnant));
        }
        if (position == 10) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_backup));
            selectedSettings = db.readSettings(db.readActiveUID());
            initializeSettings();
            String lastbackup = db.readKeySetting(uid, "lastbackup");
            if (!lastbackup.equals("")) {
                optionalText.setText(getContext().getApplicationContext().getString(R.string.backup_last) + "\n" + lastbackup + "\n");
                optionalText.setTextSize(12.0f);
                optionalText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
        }
        if (position == 11) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_restore));
        }
        if (position == 12) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.accounts_des));
        }
        if (position == 13) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.units_des));
        }
        if (position == 14) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_lang));
        }

        if (position == 15) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_rating));
        }
        if (position == 16) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_share));
        }
        if (position == 17) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_delete));
        }
        if (position == 18) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.policy_info));
        }
        if (position == 19) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.settings_delete));
        }
        if (position == 20) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.policy_info));
        }
        return rowView;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }
}
