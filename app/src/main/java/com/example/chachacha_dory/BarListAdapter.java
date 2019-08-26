package com.example.chachacha_dory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BarListAdapter extends BaseAdapter {
    private ArrayList<ChaClass> barList = new ArrayList<ChaClass>();
    BarListAdapter(){}

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
        TextView name = (TextView)convertView.findViewById(R.id.nameCha);
        TextView mood = (TextView)convertView.findViewById(R.id.moodCha);
        TextView desc = (TextView) convertView.findViewById(R.id.descCha);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.chaLayout);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.myBarStar);
        ChaClass chaClass = barList.get(position);

        name.setText(chaClass.getName());
        mood.setText(chaClass.getMood());
        desc.setText(chaClass.getDesc());
        layout.setBackgroundResource(chaClass.getBackImg());

        return convertView;
    }

    public void addBar(String name, String mood, String desc, int back){
        ChaClass item = new ChaClass();
        item.setName(name);
        item.setMood(mood);
        item.setDesc(desc);
        item.setBackImg(back);
        barList.add(item);
    }
}
