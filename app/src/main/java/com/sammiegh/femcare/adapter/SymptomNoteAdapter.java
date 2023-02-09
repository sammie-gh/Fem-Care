package com.sammiegh.femcare.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class SymptomNoteAdapter extends ArrayAdapter<String> {
    private final Context context;
    private int i = 0;
    int id;
    private final int[] imgid;
    private final String[] itemname;
    String key;
    Settings selectedSettings;
    int uid;
    String value;

    public SymptomNoteAdapter(Context context2, String[] itemname2, int[] imgid2) {
        super(context2, R.layout.item_settings, itemname2);
        context = context2;
        itemname = itemname2;
        imgid = imgid2;
        while (itemname.length > i) {
            Log.e("adfsdfds", "" + itemname[i]);
            i++;
        }
        Log.e("adfsdfds", "" + itemname.length);
        Log.e("adfsdfds", "" + imgid.length);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_note_symptoms, (ViewGroup) null);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageSymptomx);

        if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 0) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_0);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 1) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_1);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 2) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_2);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 3) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_3);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 4) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_4);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 5) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_5);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 6) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_6);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 7) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_7);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 8) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_8);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 9) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_9);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 10) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_10);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 11) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_11);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 12) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_12);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 13) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_13);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 14) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_14);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 15) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_15);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 16) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_16);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 17) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_17);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 18) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_18);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 19) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_19);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 20) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_20);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 21) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_21);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 22) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_22);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 23) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_23);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 24) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_24);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 25) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_25);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 26) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_26);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 27) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_27);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 28) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_28);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 29) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_29);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 30) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_30);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 31) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_31);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 32) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_32);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 33) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_33);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 34) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_34);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 35) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_35);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 36) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_36);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 37) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_37);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 38) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_38);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 39) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_39);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 40) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_40);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 41) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_41);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 42) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_42);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 43) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_43);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 44) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_44);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 45) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_45);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 46) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_46);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 47) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_47);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 48) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_48);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 49) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_49);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 50) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_50);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 51) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_51);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 52) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_52);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 53) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_53);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 54) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_54);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 55) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_55);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 56) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_56);
        } else if (Integer.valueOf(itemname[position]).intValue() > 0 && imgid[position] == 57) {
            imageView.setImageResource(R.mipmap.ic_symptom_img_57);
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
