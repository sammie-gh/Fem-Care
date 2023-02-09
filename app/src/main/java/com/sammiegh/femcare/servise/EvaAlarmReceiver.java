package com.sammiegh.femcare.servise;

import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EvaAlarmReceiver extends BroadcastReceiver {
    String sGiorniPrima;
    String sTipo;
    Intent service;

    public void onReceive(Context context, Intent intent) {
        sTipo = intent.getStringExtra("tipo");
        sGiorniPrima = intent.getStringExtra("giorniprima");
        service = new Intent(context, EvaSchedulingService.class);
        service.putExtra("tipo", sTipo);
        service.putExtra("giorniprima", sGiorniPrima);
        startWakefulService(context, service);
//        WakefulBroadcastReceiver.startWakefulService(context, service);
    }
}
