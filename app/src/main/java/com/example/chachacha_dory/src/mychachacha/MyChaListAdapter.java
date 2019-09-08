package com.example.chachacha_dory.src.mychachacha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chachacha_dory.R;

import java.util.ArrayList;

public class MyChaListAdapter extends BaseAdapter {
    private ArrayList<MyChaResponse.MyChaResult> mChaList;

    MyChaListAdapter(ArrayList<MyChaResponse.MyChaResult> stores){
        mChaList = stores;
    }

    @Override
    public int getCount() {
        return mChaList.size();
    }

    @Override
    public MyChaResponse.MyChaResult getItem(int position) {
        return mChaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_mycha, parent,false);
        }

        ImageView image = convertView.findViewById(R.id.mychaImage);
        TextView name = convertView.findViewById(R.id.mychaName);

        MyChaResponse.MyChaResult myChaClass = mChaList.get(position);

        Glide.with(context).load(myChaClass.getImg()).into(image);

        name.setText(myChaClass.getStorename());

        return convertView;
    }

    public void addMyCha(String icon, String name){
        MyChaResponse.MyChaResult item = new MyChaResponse.MyChaResult();
        item.setImg(icon);
        item.setStorename(name);

        mChaList.add(item);
    }

}
