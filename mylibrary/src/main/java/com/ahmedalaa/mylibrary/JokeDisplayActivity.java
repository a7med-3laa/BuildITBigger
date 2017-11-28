package com.ahmedalaa.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
    public static final String JOKE_INTENT_KEY = "Joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        TextView textView = (TextView) findViewById(R.id.textview);
        String joke = getIntent().getStringExtra(JOKE_INTENT_KEY);
        textView.setText(joke != null ? joke : "No Joke :(");
    }
}
