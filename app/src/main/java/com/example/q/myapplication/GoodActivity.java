package com.example.q.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GoodActivity extends AppCompatActivity {

    Button goodNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);

        goodNextButton = (Button) findViewById(R.id.goodNext);

        goodNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                GoodActivity.this.finish();
            }
        });
    }
}
