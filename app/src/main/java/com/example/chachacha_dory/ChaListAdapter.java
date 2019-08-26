package com.example.chachacha_dory;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChaListAdapter extends BaseAdapter {
    private ArrayList<ChaClass> chaList = new ArrayList<ChaClass>();
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
        TextView name = (TextView)convertView.findViewById(R.id.nameCha);
        TextView mood = (TextView)convertView.findViewById(R.id.moodCha);
        TextView desc = (TextView) convertView.findViewById(R.id.descCha);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.chaLayout);

        ChaClass chaClass = chaList.get(position);

        name.setText(chaClass.getName());
        mood.setText(chaClass.getMood());
        desc.setText(chaClass.getDesc());
        layout.setBackgroundResource(chaClass.getBackImg());

        return convertView;
    }

    public void addCha(String name, String mood, String desc, int back){
        ChaClass item = new ChaClass();
        item.setName(name);
        item.setMood(mood);
        item.setDesc(desc);
        item.setBackImg(back);
        chaList.add(item);
    }
}
