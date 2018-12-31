package com.example.q.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class TabFragment3 extends Fragment {

    EditText totalNumInput;
    EditText buyNumInput;

    ArrayList<Integer> numList = new ArrayList<>();

    //game data sender interface
    public interface OneTimeGameData {
        public void oneTimeGameData(String peopleNum, String payNum);
    }

    OneTimeGameData onetimegamedataitem;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onetimegamedataitem = (OneTimeGameData) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.tab_fragment_3, container, false);

     return mView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstancesState){

        totalNumInput = (EditText) getView().findViewById(R.id.totalInput);
        buyNumInput = (EditText) getView().findViewById(R.id.buyInput);
        ImageButton riceButt = (ImageButton) getView().findViewById(R.id.riceButton);

        riceButt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try{

                    String valueTotal = totalNumInput.getText().toString();
                    String valueBuy = buyNumInput.getText().toString();

                    Integer totalInputNum = Integer.parseInt(valueTotal);
                    Integer buyInputNum = Integer.parseInt(valueBuy);
                    numList.add(totalInputNum);
                    numList.add(buyInputNum);

                    onetimegamedataitem.oneTimeGameData(totalInputNum.toString(), buyInputNum.toString());

                    //이미 MainActivity로 넘어가서 안써도 됨.
                    /*Intent intent = new Intent(getActivity(), GameActivity.class);
                    intent.putExtra("totalNbuy", totalInputNum);
                    startActivity(intent);*/

                } catch (NumberFormatException n) {
                    View viewGame = getActivity().findViewById(R.id.gameLayout);
                    String message = "숫자로 써주세요!";
                    int duration = Snackbar.LENGTH_SHORT;
                    Snackbar.make(viewGame, message, duration).show();
                }
            }
        });

    }

}
