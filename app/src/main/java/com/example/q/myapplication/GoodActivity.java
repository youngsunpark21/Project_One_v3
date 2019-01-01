package com.example.q.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GoodActivity extends AppCompatActivity {

    ImageButton goodNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);

        goodNextButton = (ImageButton) findViewById(R.id.goodNext);

        goodNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GoodActivity.this.finish();
            }
        });
    }
}
