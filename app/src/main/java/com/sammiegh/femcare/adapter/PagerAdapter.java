package com.sammiegh.femcare.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sammiegh.femcare.fragment.TabFragment1;
import com.sammiegh.femcare.fragment.TabFragment2;
import com.sammiegh.femcare.fragment.TabFragment3;
import com.sammiegh.femcare.fragment.TabFragment5;

public class PagerAdapter extends FragmentStatePagerAdapter {
    String datekey;
    Bundle bundle = new Bundle();
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numoftabs, String datekey2) {
        super(fm);
        mNumOfTabs = numoftabs;
        datekey = datekey2;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                bundle = new Bundle();
                bundle.putString("datekey", datekey);
                TabFragment1 tab1 = new TabFragment1();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                bundle = new Bundle();
                bundle.putString("datekey", datekey);
                TabFragment2 tab2 = new TabFragment2();
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                bundle = new Bundle();
                bundle.putString("datekey", datekey);
                TabFragment3 tab3 = new TabFragment3();
                tab3.setArguments(bundle);
                return tab3;
            case 3:
                bundle = new Bundle();
                bundle.putString("datekey", datekey);
                TabFragment5 tab5 = new TabFragment5();
                tab5.setArguments(bundle);
                return tab5;
            default:
                return null;
        }
    }

    public int getCount() {
        return mNumOfTabs;
    }
}
