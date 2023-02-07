package com.sammiegh.femcare.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class MucusNoteAdapter extends ArrayAdapter<String> {
    private final Context context;
    int id;
    private final int[] imgid;
    private final String[] itemname;
    String key;
    Settings selectedSettings;
    int uid;
    String value;

    public MucusNoteAdapter(Context context2, String[] itemname2, int[] imgid2) {
        super(context2, R.layout.item_settings, itemname2);
        context = context2;
        itemname = itemname2;
        imgid = imgid2;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_note_mucus, (ViewGroup) null);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageMucusx);
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 1) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_1);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 2) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_2);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 3) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_3);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 4) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_4);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 5) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_5);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 6) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_6);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 7) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_7);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 8) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_8);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 10) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_10);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 11) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_11);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 12) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_12);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 13) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_13);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 14) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_14);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 15) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_15);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 17) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_17);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 18) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_18);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 19) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_19);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 20) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_20);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 21) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_21);
        }
        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 22) {
            imageView.setImageResource(R.mipmap.ic_mucus_img_22);
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
