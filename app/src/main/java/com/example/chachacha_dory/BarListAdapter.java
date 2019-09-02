package com.example.chachacha_dory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BarListAdapter extends BaseAdapter {
    private ArrayList<StoreResponse.StoreResult> barList;
    BarListAdapter(ArrayList<StoreResponse.StoreResult> arrayList){
        barList = arrayList;
    }

    @Override
    public int getCount() {
        return barList.size();
    }

    @Override
    public Object getItem(int position) {
        return barList.get(position);
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
            convertView = inflater.inflate(R.layout.item_chacha, parent,false);
        }
        TextView name = convertView.findViewById(R.id.nameCha);
        TextView mood = convertView.findViewById(R.id.moodCha);
        TextView desc =  convertView.findViewById(R.id.descCha);
        RelativeLayout layout = convertView.findViewById(R.id.chaLayout);
        ImageView imageView = convertView.findViewById(R.id.myBarStar);
        StoreResponse.StoreResult chaClass = barList.get(position);

        name.setText(chaClass.getStorename());
        mood.setText(chaClass.getMood());
        desc.setText(chaClass.getWriting());
        layout.setBackground(Drawable.createFromPath(chaClass.getImg()));

        return convertView;
    }

    public void addBar(String name, String mood, String desc, String back){
        StoreResponse.StoreResult item = new StoreResponse.StoreResult();
        item.setStorename(name);
        item.setMood(mood);
        item.setWriting(desc);
        item.setImg(back);
        barList.add(item);
    }
}
