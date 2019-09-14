package com.example.chachacha_dory.src.bookmark;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chachacha_dory.R;

import java.util.ArrayList;

public class MyBarListAdapter extends BaseAdapter {
    private ArrayList<MyBarResponse.MyBarResult> barList;

    MyBarListAdapter(ArrayList<MyBarResponse.MyBarResult> arrayList) {
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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chacha, parent, false);
        }

        TextView name = convertView.findViewById(R.id.nameCha);
        TextView mood = convertView.findViewById(R.id.moodCha);
        TextView desc = convertView.findViewById(R.id.descCha);
        ImageView chaBackground = convertView.findViewById(R.id.chaImageBack);
        ImageView imageView = convertView.findViewById(R.id.myBarStar);
        MyBarResponse.MyBarResult chaClass = barList.get(position);

        name.setText(chaClass.getStorename());
        mood.setText(chaClass.getMood());
        desc.setText(chaClass.getWriting());
        imageView.setImageResource(R.drawable.ic_select_star);
        Glide.with(context).load(chaClass.getImg()).into(chaBackground);

        GradientDrawable drawable = (GradientDrawable)context.getDrawable(R.drawable.border_round);
        chaBackground.setBackground(drawable);
        chaBackground.setClipToOutline(true);

        return convertView;
    }

    public void addBar(String name, String mood, String desc, String back) {
        MyBarResponse.MyBarResult item = new MyBarResponse.MyBarResult();
        item.setStorename(name);
        item.setMood(mood);
        item.setWriting(desc);
        item.setImg(back);
        barList.add(item);
    }
}
