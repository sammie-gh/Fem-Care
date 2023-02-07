package com.sammiegh.femcare;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

import com.sammiegh.femcare.utils.AppOpenManager;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    AppOpenManager appOpenManager;
    public static Activity currentActivity;
    @Override public void onCreate() {
        super.onCreate();
        appOpenManager = new AppOpenManager(MyApplication.this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
