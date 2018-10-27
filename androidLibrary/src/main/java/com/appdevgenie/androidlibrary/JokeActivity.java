package com.appdevgenie.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE = "joke";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_output);

        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra(JOKE)) {
                TextView textView = findViewById(R.id.tvJoke);
                textView.setText(intent.getStringExtra(JOKE));
            }
        }
    }
}
