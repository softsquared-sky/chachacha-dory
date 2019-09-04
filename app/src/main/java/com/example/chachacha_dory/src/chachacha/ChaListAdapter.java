package com.example.chachacha_dory.src.chachacha;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.src.detail.DetailResponse;

import java.util.ArrayList;

public class ChaListAdapter extends BaseAdapter {
    private ArrayList<DetailResponse.DetailResult> chaList = new ArrayList<>();
    ChaListAdapter(){}

    @Override
    public int getCount() {
        return chaList.size();
    }

    @Override
    public Object getItem(int position) {
        return chaList.get(position);
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
        TextView desc = convertView.findViewById(R.id.descCha);
        RelativeLayout layout = convertView.findViewById(R.id.chaLayout);

        DetailResponse.DetailResult chaClass = chaList.get(position);

        name.setText(chaClass.getStorename());
        mood.setText(chaClass.getMood());
        desc.setText(chaClass.getWriting());
        layout.setBackground(Drawable.createFromPath(chaClass.getImg()));

        return convertView;
    }

    public void addCha(String name, String mood, String desc, String back){
        DetailResponse.DetailResult item = new DetailResponse.DetailResult();
        item.setStorename(name);
        item.setMood(mood);
        item.setWriting(desc);
        item.setImg(back);
        chaList.add(item);
    }
}