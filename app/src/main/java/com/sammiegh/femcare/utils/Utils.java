package com.sammiegh.femcare.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.sammiegh.femcare.R;

public class Utils {
    public static InterstitialAd interstitial;
    public static AdRequest adRequest;
    public static AdView mAdView;
    public static com.google.android.gms.ads.AdView mAdViewS;
    public static InterstitialAd interstitialAd;

    public static void loadAd(Activity activity) {
        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, activity.getString(R.string.inderstrial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull  InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                interstitial = interstitialAd;
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        loadAd(activity);
                    }
                });
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error

                interstitial = null;

            }
        });


    }

    public static void displayInterstitial(Activity activity) {

        if (interstitial != null) {
            interstitial.show(activity);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }


    }

    public static void showBannerAds(Activity activity) {
//        LinearLayout adBannerLayout = activity.findViewById(R.id.adView);
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float widthPixels = metrics.widthPixels;
        float density = metrics.density;
        int adWidth = (int) (widthPixels / density);

        mAdViewS = new com.google.android.gms.ads.AdView(activity);
        mAdViewS.setAdUnitId(activity.getString(R.string.banner_ad_unit_id));
        mAdViewS.setAdSize(com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth));
//        adBannerLayout.addView(mAdViewS);
        AdRequest adRequest;
        adRequest = new AdRequest.Builder().build();
        mAdViewS.loadAd(adRequest);


    }
}
