package com.udacity.gradle.builditbigger.free;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ahmedalaa.mylibrary.JokeDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.SendInterface;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {
    ProgressBar progressBar;
    Button btn;
    private PublisherInterstitialAd mPublisherInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mPublisherInterstitialAd = new PublisherInterstitialAd(getActivity());
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        btn = (Button) root.findViewById(R.id.tellJoke);
        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new JokeAsyncTask(new SendInterface() {

                    @Override
                    public void onPostExecute(final String s) {

                        mPublisherInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                super.onAdClosed();
                                Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
                                intent.putExtra(JokeDisplayActivity.JOKE_INTENT_KEY, s);
                                startActivity(intent);
                            }
                        });
                        if(mPublisherInterstitialAd.isLoaded())
                        mPublisherInterstitialAd.show();
                        progressBar.setVisibility(View.GONE);


                    }
                }, getActivity()).execute();
            }
        });
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
    }
}
