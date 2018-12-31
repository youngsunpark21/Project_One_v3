package com.example.q.myapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>();
    private Context mContext;

    public ListViewAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public ListViewItem getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,viewGroup,false);


        }



        ImageView iv = (ImageView) convertView.findViewById(R.id.image_profile);
        TextView name = (TextView) convertView.findViewById(R.id.textName);
        TextView phone = (TextView) convertView.findViewById(R.id.textPhoneNumber);

        ListViewItem item = (ListViewItem) getItem(position);

        Glide.with(mContext)
                .load(item.getImg())
                .into(iv);
        name.setText(item.getName());
        phone.setText(item.getPhone());


        return convertView;
    }

    public void addItem(Uri img, String name, String phone){
        ListViewItem newItem = new ListViewItem();
        newItem.setImg(img);
        newItem.setName(name);
        newItem.setPhone(phone);

        itemList.add(newItem);
    }
}
