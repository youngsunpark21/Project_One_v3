package com.example.q.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

public class BadActivity extends AppCompatActivity {

    ImageButton badNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad);

        badNextButton = (ImageButton) findViewById(R.id.badNext);

        badNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BadActivity.this.finish();
            }
        });

    }
}
