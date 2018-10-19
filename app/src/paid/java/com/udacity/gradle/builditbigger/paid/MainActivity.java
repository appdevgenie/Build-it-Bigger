package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;

import com.appdevgenie.androidlibrary.JokeActivity;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements JokeAsyncTask.AsyncRequestComplete{

    private static final String JOKE = "joke";

    private ProgressBar progressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.bTellJoke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new JokeAsyncTask(MainActivity.this).execute();
            }
        });

    }

    @Override
    public void requestComplete(String joke) {
        progressBar.setVisibility(View.GONE);

        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE, joke);
        context.startActivity(intent);
    }
}
