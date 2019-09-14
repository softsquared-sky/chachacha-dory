package com.example.chachacha_dory.src.chachacha;

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

public class ChaListAdapter extends BaseAdapter {
    private ArrayList<RecommendResponse.RecommendResult> recommendResults;
    ChaListAdapter(ArrayList<RecommendResponse.RecommendResult> recommendResult){
        recommendResults = recommendResult;
    }

    @Override
    public int getCount() {
        return recommendResults.size();
    }

    @Override
    public RecommendResponse.RecommendResult getItem(int position) {
        return recommendResults.get(position);
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
            convertView = inflater.inflate(R.layout.item_chacha_nostar, parent,false);
        }
        TextView name = convertView.findViewById(R.id.nameCha2);
        TextView mood = convertView.findViewById(R.id.moodCha2);
        TextView desc = convertView.findViewById(R.id.descCha2);
//        RelativeLayout layout = convertView.findViewById(R.id.chaLayout);
        ImageView chaBackground = convertView.findViewById(R.id.chaImageBack2);
        RecommendResponse.RecommendResult recommendResult = recommendResults.get(position);

        name.setText(recommendResult.getStorename());
        mood.setText(recommendResult.getMood());
        desc.setText(recommendResult.getWriting());
        Glide.with(context).load(recommendResult.getImg()).into(chaBackground);

        GradientDrawable drawable = (GradientDrawable)context.getDrawable(R.drawable.border_round);
        chaBackground.setBackground(drawable);
        chaBackground.setClipToOutline(true);

        return convertView;
    }

    public void addCha(String name, String mood, String desc, String back){
        RecommendResponse.RecommendResult item = new RecommendResponse.RecommendResult();
        item.setStorename(name);
        item.setMood(mood);
        item.setWriting(desc);
        item.setImg(back);
        recommendResults.add(item);
    }
}
