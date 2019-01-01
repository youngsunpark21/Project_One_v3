package com.example.q.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFragment1 extends Fragment {

    public ArrayList<Map<String, String>> dataList;
    public ListView mListview;
    public Button mBtnAddress;
    public TextView textView4;
    private ListViewAdapter adapter;
    static final int REQUEST_PERMISSION_KEY = 2051;
    ArrayList<String> StoreContacts;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //might have to write later

        //might have to write earlier
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        mListview = (ListView) view.findViewById(R.id.listview_1);
        mBtnAddress = (Button) view.findViewById(R.id.btnAddress);
        textView4 = (TextView) view.findViewById(R.id.textview4);
        adapter = new ListViewAdapter(getActivity());

        //StoreContacts = new ArrayList<String>();


        mBtnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Contact info
                String[] proj = {
                        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.DATA

                };

                Uri uriBlank = Uri.parse("android.resource://com.example.q.myapplication/drawable/bob");

                Cursor c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, proj, null, null
                        , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " asc");

                if (c.moveToFirst()) {
                    //toast text
                    Toast.makeText(getActivity(), "밥심이 친구들을 불러왔어요", Toast.LENGTH_LONG).show();
                    do {
                        String uri = c.getString(0);
                        String name = c.getString(1);
                        String number = c.getString(2);
                        if (uri != null) {
                            adapter.addItem(Uri.parse(uri), name, number);
                        } else {
                            adapter.addItem(uriBlank, name, number);
                        }
                    } while (c.moveToNext());
                }
                c.close();


                mListview.setAdapter(adapter);
                // 어댑터 쌓인 데이터를 listview에 프로젝션 형태로 데이터 뿌리기
                mBtnAddress.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
            }
        });


        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                    //제목셋팅
                alertDialogBuilder.setTitle("'밥먹자 친구야!' 라고 하기!!");

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("전화하기",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick( DialogInterface dialog, int id){
                                            //String phoneSample = adapter.getItem(position).replaceAll("[^0-9]",""));
                                            String phone = (adapter.getItem(position).getPhone().replaceAll("[^0-9]",""));
                                            Intent intent = new Intent(Intent.ACTION_CALL);
                                            intent.setData(Uri.parse("tel: " + phone));
                                            try{
                                                startActivity(intent);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                        }
                                    })
                        //전화하기
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(
                                            DialogInterface dialog, int id){
                                        //다이얼로그 취소
                                        dialog.cancel();
                                    }
                                });

                //다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                //다이얼로그 보여주기
                alertDialog.show();
                alertDialog.closeOptionsMenu();

            }
        });


        return view;

    }

    public void onCall(String s) {

    }

// <- 이건 무엇을 위한거??
    /*
    public void onClick(View v) {

       Log.d("hihihi","!!!");
        switch (v.getId()) {
            case R.id.btnAddress:

        }
    }
*/

}


