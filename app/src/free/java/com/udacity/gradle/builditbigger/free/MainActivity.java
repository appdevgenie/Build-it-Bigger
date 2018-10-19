package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.appdevgenie.androidlibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements JokeAsyncTask.AsyncRequestComplete {

    private static final String JOKE = "joke";

    private InterstitialAd interstitialAd;
    private ProgressBar progressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_frame);

        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                showJoke();
            }
        });

        Button button = findViewById(R.id.bTellJoke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }else {
                    showJoke();
                }
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void showJoke() {
        progressBar.setVisibility(View.VISIBLE);
        new JokeAsyncTask(this).execute();
    }


    @Override
    public void requestComplete(String joke) {
        progressBar.setVisibility(View.GONE);

        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE, joke);
        context.startActivity(intent);
    }
}
