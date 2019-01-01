package com.example.q.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        },SPLASH_TIME_OUT);

    }
}
