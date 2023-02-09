package com.sammiegh.femcare.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sammiegh.femcare.utils.JCGSQLiteHelper;
import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class RestoreAdapter extends ArrayAdapter<String> {
    private final Activity context;
    JCGSQLiteHelper db;
    int id;
    private final Integer[] imgid;
    private final String[] itemname;
    String key;
    TextView optionalText;
    Settings selectedSettings;
    int uid;
    String value;

    public RestoreAdapter(Activity context2, String[] itemname2, Integer[] imgid2) {
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
            extratxt.setText(getContext().getApplicationContext().getString(R.string.restore_des_sd));
        }
        if (position == 1) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.restore_des_email));
        }
        if (position == 2) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.restore_des_cloud));
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
