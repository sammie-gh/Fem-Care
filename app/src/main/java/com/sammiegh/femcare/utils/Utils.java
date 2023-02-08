package com.sammiegh.femcare.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
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
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
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
        LinearLayout adBannerLayout = activity.findViewById(R.id.banner_AdView);
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float widthPixels = metrics.widthPixels;
        float density = metrics.density;
        int adWidth = (int) (widthPixels / density);

        mAdViewS = new com.google.android.gms.ads.AdView(activity);
        mAdViewS.setAdUnitId(activity.getString(R.string.banner_ad_unit_id));
        mAdViewS.setAdSize(com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth));
        adBannerLayout.addView(mAdViewS);
        AdRequest adRequest;
        adRequest = new AdRequest.Builder().build();
        mAdViewS.loadAd(adRequest);


    }

    public static void LoadNativeAds(final AppCompatActivity activity) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, "ca-app-pub-1555030338481101/4716212467")
                .forNativeAd(nativeAd -> {

                    FrameLayout frameLayout = activity.findViewById(R.id.native_ad_container);
                    NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.lyt_native_ads, null);
                    // populateNativeAdView(unifiedNativeAd,adView);
                    populateNativeAdView(nativeAd, adView);
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                });

        // Load the Native ads.
        builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Handle the failure by logging, altering the UI, and so on.
            }

            @Override
            public void onAdClicked() {
                // Log the click event or other custom behavior.
            }
        }).build().loadAd(new AdRequest.Builder().build());


    }

    public static void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd


        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));


        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.GONE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.GONE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);
    }
}
