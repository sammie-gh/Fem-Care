package com.sammiegh.femcare.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.sammiegh.femcare.R;
import com.sammiegh.femcare.model.Settings;

public class MoodNoteAdapter extends ArrayAdapter<String> {
    private final Context context;
    int id;
    private final int[] imgid;
    private final String[] itemname;
    String key;
    Settings selectedSettings;
    int uid;
    String value;

    public MoodNoteAdapter(Context context2, String[] itemname2, int[] imgid2) {
        super(context2, R.layout.item_settings, itemname2);
        context = context2;
        itemname = itemname2;
        imgid = imgid2;
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        View rowView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_note_moods, (ViewGroup) null);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageMood);
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 0) {
            imageView.setImageResource(R.mipmap.ic_mood_img_0);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 1) {
            imageView.setImageResource(R.mipmap.ic_mood_img_1);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 2) {
            imageView.setImageResource(R.mipmap.ic_mood_img_2);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 3) {
            imageView.setImageResource(R.mipmap.ic_mood_img_3);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 4) {
            imageView.setImageResource(R.mipmap.ic_mood_img_4);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 5) {
            imageView.setImageResource(R.mipmap.ic_mood_img_5);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 6) {
            imageView.setImageResource(R.mipmap.ic_mood_img_6);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 7) {
            imageView.setImageResource(R.mipmap.ic_mood_img_7);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 8) {
            imageView.setImageResource(R.mipmap.ic_mood_img_8);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 9) {
            imageView.setImageResource(R.mipmap.ic_mood_img_9);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 10) {
            imageView.setImageResource(R.mipmap.ic_mood_img_10);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 11) {
            imageView.setImageResource(R.mipmap.ic_mood_img_11);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 12) {
            imageView.setImageResource(R.mipmap.ic_mood_img_12);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 13) {
            imageView.setImageResource(R.mipmap.ic_mood_img_13);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 14) {
            imageView.setImageResource(R.mipmap.ic_mood_img_14);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 15) {
            imageView.setImageResource(R.mipmap.ic_mood_img_15);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 16) {
            imageView.setImageResource(R.mipmap.ic_mood_img_16);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 17) {
            imageView.setImageResource(R.mipmap.ic_mood_img_17);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 18) {
            imageView.setImageResource(R.mipmap.ic_mood_img_18);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 19) {
            imageView.setImageResource(R.mipmap.ic_mood_img_19);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 20) {
            imageView.setImageResource(R.mipmap.ic_mood_img_20);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 21) {
            imageView.setImageResource(R.mipmap.ic_mood_img_21);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 22) {
            imageView.setImageResource(R.mipmap.ic_mood_img_22);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 23) {
            imageView.setImageResource(R.mipmap.ic_mood_img_23);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 24) {
            imageView.setImageResource(R.mipmap.ic_mood_img_24);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 25) {
            imageView.setImageResource(R.mipmap.ic_mood_img_25);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 26) {
            imageView.setImageResource(R.mipmap.ic_mood_img_26);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 27) {
            imageView.setImageResource(R.mipmap.ic_mood_img_27);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 28) {
            imageView.setImageResource(R.mipmap.ic_mood_img_28);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 29) {
            imageView.setImageResource(R.mipmap.ic_mood_img_29);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 30) {
            imageView.setImageResource(R.mipmap.ic_mood_img_30);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 31) {
            imageView.setImageResource(R.mipmap.ic_mood_img_31);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 32) {
            imageView.setImageResource(R.mipmap.ic_mood_img_32);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 33) {
            imageView.setImageResource(R.mipmap.ic_mood_img_33);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 34) {
            imageView.setImageResource(R.mipmap.ic_mood_img_34);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 35) {
            imageView.setImageResource(R.mipmap.ic_mood_img_35);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 36) {
            imageView.setImageResource(R.mipmap.ic_mood_img_36);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 37) {
            imageView.setImageResource(R.mipmap.ic_mood_img_37);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 38) {
            imageView.setImageResource(R.mipmap.ic_mood_img_38);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 39) {
            imageView.setImageResource(R.mipmap.ic_mood_img_39);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 40) {
            imageView.setImageResource(R.mipmap.ic_mood_img_40);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 41) {
            imageView.setImageResource(R.mipmap.ic_mood_img_41);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 42) {
            imageView.setImageResource(R.mipmap.ic_mood_img_42);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 43) {
            imageView.setImageResource(R.mipmap.ic_mood_img_43);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 44) {
            imageView.setImageResource(R.mipmap.ic_mood_img_44);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 45) {
            imageView.setImageResource(R.mipmap.ic_mood_img_45);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 46) {
            imageView.setImageResource(R.mipmap.ic_mood_img_46);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 47) {
            imageView.setImageResource(R.mipmap.ic_mood_img_47);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 48) {
            imageView.setImageResource(R.mipmap.ic_mood_img_48);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 49) {
            imageView.setImageResource(R.mipmap.ic_mood_img_49);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 50) {
            imageView.setImageResource(R.mipmap.ic_mood_img_50);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 51) {
            imageView.setImageResource(R.mipmap.ic_mood_img_51);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 52) {
            imageView.setImageResource(R.mipmap.ic_mood_img_52);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 53) {
            imageView.setImageResource(R.mipmap.ic_mood_img_53);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 54) {
            imageView.setImageResource(R.mipmap.ic_mood_img_54);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 55) {
            imageView.setImageResource(R.mipmap.ic_mood_img_55);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 56) {
            imageView.setImageResource(R.mipmap.ic_mood_img_56);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 57) {
            imageView.setImageResource(R.mipmap.ic_mood_img_57);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 58) {
            imageView.setImageResource(R.mipmap.ic_mood_img_58);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 59) {
            imageView.setImageResource(R.mipmap.ic_mood_img_59);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 60) {
            imageView.setImageResource(R.mipmap.ic_mood_img_60);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 61) {
            imageView.setImageResource(R.mipmap.ic_mood_img_61);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 62) {
            imageView.setImageResource(R.mipmap.ic_mood_img_62);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 63) {
            imageView.setImageResource(R.mipmap.ic_mood_img_63);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 64) {
            imageView.setImageResource(R.mipmap.ic_mood_img_64);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 65) {
            imageView.setImageResource(R.mipmap.ic_mood_img_65);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 66) {
            imageView.setImageResource(R.mipmap.ic_mood_img_66);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 67) {
            imageView.setImageResource(R.mipmap.ic_mood_img_67);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 68) {
            imageView.setImageResource(R.mipmap.ic_mood_img_68);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 69) {
            imageView.setImageResource(R.mipmap.ic_mood_img_69);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 70) {
            imageView.setImageResource(R.mipmap.ic_mood_img_70);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 71) {
            imageView.setImageResource(R.mipmap.ic_mood_img_71);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 72) {
            imageView.setImageResource(R.mipmap.ic_mood_img_72);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 73) {
            imageView.setImageResource(R.mipmap.ic_mood_img_73);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 74) {
            imageView.setImageResource(R.mipmap.ic_mood_img_74);
        }
        if (Integer.valueOf(itemname[position]).intValue() == 1 && imgid[position] == 75) {
            imageView.setImageResource(R.mipmap.ic_mood_img_75);
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
