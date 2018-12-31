package com.example.q.myapplication;

import android.Manifest;
import android.content.Context;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFragment1 extends Fragment {

    public ArrayList<Map<String, String>> dataList;
    public ListView mListview;
    public Button mBtnAddress;
    private ListViewAdapter adapter;
    static final int REQUEST_PERMISSION_KEY = 2051;
    ArrayList<String> StoreContacts;


    @Override
    public void onAttach(Context context){
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        mListview = (ListView) view.findViewById(R.id.listview_1);
        mBtnAddress = (Button) view.findViewById(R.id.btnAddress);
        adapter = new ListViewAdapter(getActivity());

        StoreContacts = new ArrayList<String>();
/*
// 주소록 퍼미션
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
           /* if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS},1);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            *
        }
        else{
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)){
                Toast.makeText(getActivity().getApplicationContext(), "Permission Needed.",Toast.LENGTH_LONG);
            }
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},123);
        };
//</주소록 퍼미션>
*/

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

                Cursor c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,proj,null,null
                        ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY+" asc");

                if(c.moveToFirst()){
                    //toast text
                    Toast.makeText(getActivity(),"주소록을 불러왔습니다.",Toast.LENGTH_LONG).show();
                    do{
                        String name = c.getString(1);
                        String number = c.getString(2);
                        String uri = c.getString(0);
                        if (uri != null){
                            adapter.addItem(Uri.parse(uri),name,number);
                        }else {
                            adapter.addItem(uriBlank, name, number);
                        }
                    }while(c.moveToNext());
                }
                c.close();


                mListview.setAdapter(adapter);
                //TODO:두번눌러보기!
                mBtnAddress.setVisibility(View.GONE);
            }
        });

        /*Button.OnClickListener btnListner = (new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Contact info
                String[] proj = {
                        ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.DATA

                };

                Uri uriBlank = Uri.parse("android.resource://com.example.q.myapplication/drawable/bob");

                Cursor c =getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,proj,null,null
                        ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY+" asc");

                if(c.moveToFirst()){
                    //toast text
                    Toast.makeText(getActivity(),"주소록을 불러왔습니다.",Toast.LENGTH_LONG).show();
                    do{
                        String name = c.getString(1);
                        String number = c.getString(2);
                        String uri = c.getString(0);
                        if (uri != null){
                            adapter.addItem(Uri.parse(uri),name,number);
                        }else {
                            adapter.addItem(uriBlank, name, number);
                        }
                    }while(c.moveToNext());
                }
                c.close();


                mListview.setAdapter(adapter);
                mBtnAddress.setVisibility(View.GONE);


                }
            //


            //




        });*/

        //mBtnAddress.setOnClickListener(btnListner);
        /*
        mBtnAddress.setOnClickListener(new AdapterView.OnItemClickListener(){
                                       }
        );
*/
        return view;

    }

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_PERMISSION_KEY: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                } else
                {
                    Toast.makeText(getActivity(), "You must accept permissions.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
    */

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

