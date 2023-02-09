package com.sammiegh.femcare.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sammiegh.femcare.R;

public class LanguageAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] imgid;
    private final String[] itemname;

    public LanguageAdapter(Activity context2, String[] itemname2, Integer[] imgid2) {
        super(context2, R.layout.item_languages, itemname2);
        context = context2;
        itemname = itemname2;
        imgid = imgid2;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = context.getLayoutInflater().inflate(R.layout.item_languages, (ViewGroup) null, true);
        ((TextView) rowView.findViewById(R.id.txt1Settings)).setText(itemname[position]);
        ((ImageView) rowView.findViewById(R.id.txtDate)).setImageResource(imgid[position].intValue());
        return rowView;
    }
}
