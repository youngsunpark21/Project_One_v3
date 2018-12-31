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
        adapter = new ListViewAdapter(getActivity());

        //StoreContacts = new ArrayList<String>();
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

                Cursor c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, proj, null, null
                        , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " asc");

                if (c.moveToFirst()) {
                    //toast text
                    Toast.makeText(getActivity(), "주소록을 불러왔습니다.", Toast.LENGTH_LONG).show();
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
                                                // 전화걸기 method
                                            //AlertDialogActivity.this.finish();
                                            //nCall("01012122323");
                                        }
                                    })
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



        /*
//전화하기
        mListview.setOnItemClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity mainActivity = (MainActivity) getContext().getApplicationContext();
                if(MainActivity.checkPermission){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    context.startActivity(intent);
                }
                else{
                    Toast t= Toast.makeText(context, R.string.permission_error, Toast.LENGTH_LONG);
                    t.show();
                }
            }

        });
*/

/*
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                                                 if (checkPermission()) {
                                                     Log.d("shit", "damnn!!!!!!!!!");
                                                     AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
                                                     Log.d("shit", "damnn!!!!!!!!!~~~~~");
                                                     a_builder.setMessage("밥먹자고 전화할까요?")
                                                             .setCancelable(false)
                                                             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(DialogInterface dialogInterface, int i) {
                                                                     dialogInterface.dismiss();
                                                                 }
                                                             })
                                                             .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(DialogInterface dialogInterface, int i) {
                                                                     dialogInterface.cancel();
                                                                 }
                                                             });
                                                     Log.d("shit", "damnn!!!!!!!!!~~~~~!!!");
                                                 }
                                             }
                                         });

*/

/*
        new AlertDialog.Builder(getActivity()).setTitle("밥먹자 전화할까요?").setPositiveButton("전화걸기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                Log.d("shit", "damnn~~~~~!~!~!");
                String phone = (StoreContacts.get(position).replaceAll("[^0-9]", ""));
                //Intent intent = new Intent();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + phone));
                // Intent.setData(Uri.parse("tel" + phone));
                Log.d("shit", "damnn~~~~~");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("shit", "damnnnnnn");
                    e.printStackTrace();
                }
            }
        });
*/



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

    public void onCall(String s) {

    }

//    public boolean checkPermission(){
//        Log.d("shit", "damnn~~~~~");
//        int permissionCall = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
//
//        List<String> listPermission = new ArrayList<>();
//        if(permissionCall != PackageManager.PERMISSION_GRANTED){
//            Log.d("shit", "damnn~~~~~??????");
//            listPermission.add(Manifest.permission.CALL_PHONE);
//            Log.d("shit", "damnn!?!??!?!?!??!?!??!??!?!?!??!?!!??!?!?");
//        }
//        if (!listPermission.isEmpty()){
//            ActivityCompat.requestPermissions(getActivity(),listPermission.toArray(new String[listPermission.size()]),1);
//            return false;
//        }
//        Log.d("shit", "damnn~~~~~!~!~!~!~!~!~!~!~!~!");
//        return true;
//
//    }

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


