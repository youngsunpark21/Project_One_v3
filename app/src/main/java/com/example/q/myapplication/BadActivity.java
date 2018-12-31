package com.example.q.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BadActivity extends AppCompatActivity {

    Button badNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad);

        badNextButton = (Button) findViewById(R.id.badNext);

        badNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BadActivity.this.finish();
            }
        });
    }
}
