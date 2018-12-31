package com.example.q.myapplication;

import android.net.Uri;

public class ListViewItem {
    private Uri img;
    private String name;
    private String phone;

    public void setImg(Uri imgUri){
        img = imgUri;
    }
    public void setName(String nm){
        name = nm;
    }
    public void setPhone(String pn){
        phone = pn;
    }

    public Uri getImg(){
        return img;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
}
