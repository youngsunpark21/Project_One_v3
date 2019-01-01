package com.example.q.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    TextView sampleTotal;
    TextView sampleBuy;
    TextView gameBadNum;

    ImageButton redButton;

    Integer totalIntentInt;
    Integer buyIntentInt;

    String totalIntentStr;
    String buyIntentStr;

    ArrayList<Integer> gameList;
    ArrayList<Integer> buyList;

    Random numGenerator = new Random();
    int randomNum;

    //버튼 누른 수
    int numPressed = 0;

    //꽝 나온 수
    int gameNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sampleTotal = (TextView) findViewById(R.id.sample1);
        sampleBuy = (TextView) findViewById(R.id.sample2);
        gameBadNum = (TextView) findViewById(R.id.gameNum);
        redButton = (ImageButton) findViewById(R.id.pressButton);


        Intent intent = getIntent();
        totalIntentStr  = intent.getStringExtra("total");
        buyIntentStr = intent.getStringExtra("buy");

        sampleTotal.setText(totalIntentStr);
        sampleBuy.setText( buyIntentStr);

        totalIntentInt = Integer.parseInt(totalIntentStr);
        buyIntentInt = Integer.parseInt(buyIntentStr);

        gameList = new ArrayList<>();
        buyList = new ArrayList<>();

        //초기 틀 만들기
        for (int i = 0; i < totalIntentInt ; i++) {
            gameList.add(0);
        }

        //buy개수의 random position들 만들기
        while (buyIntentInt != 0) {
            randomNum = numGenerator.nextInt(totalIntentInt - 1);
            //make no 중복
            if (!buyList.contains(randomNum)) {
                buyList.add(randomNum);
                buyIntentInt -= 1;
            }
        }

        Log.d("BUYLIST", buyList.toString());

        //1로 채우기
        for (Integer i = 0; i < gameList.size(); i++ ) {
            if (buyList.contains(i)) {
                gameList.set(i, 1);
            } else {
                gameList.set(i, 0);
            }
        }

        Log.d("RANDOMLIST", gameList.toString());

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (gameList.get(numPressed) == 0) {
                        Intent intent = new Intent(GameActivity.this, GoodActivity.class);
                        startActivity(intent);
                        numPressed += 1;
                    } else {
                        Intent intent = new Intent(GameActivity.this, BadActivity.class);
                        startActivity(intent);
                        numPressed +=1;
                        gameNum +=1;
                        gameBadNum.setText(Integer.toString(gameNum));
                    }
                } catch (IndexOutOfBoundsException i) {
                    View viewGame = findViewById(R.id.gamingLayout);
                    String message = "게임이 끝났습니다.";
                    int duration = Snackbar.LENGTH_SHORT;
                    Snackbar.make(viewGame, message, duration).show();
                }
            }
        });

    }
}
