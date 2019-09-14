package com.example.chachacha_dory.src.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.src.chachacha.RecommendResponse;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class SearchListAdapter extends BaseAdapter {
    private ArrayList<SearchResponse.SearchResult> mSearchResults;
    SearchListAdapter(ArrayList<SearchResponse.SearchResult> searchResult){
        mSearchResults = searchResult;
    }

    @Override
    public int getCount() {
        return mSearchResults.size();
    }

    @Override
    public SearchResponse.SearchResult getItem(int position) {
        return mSearchResults.get(position);
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
        ImageView searchImage = convertView.findViewById(R.id.chaImageBack2);

        SearchResponse.SearchResult searchResult = mSearchResults.get(position);

        name.setText(searchResult.getStorename());
        mood.setText(searchResult.getMood());
        desc.setText(searchResult.getWriting());
        Glide.with(context).load(searchResult.getImg()).into(searchImage);

        GradientDrawable drawable = (GradientDrawable)context.getDrawable(R.drawable.border_round);
        searchImage.setBackground(drawable);
        searchImage.setClipToOutline(true);

        return convertView;
    }

    public void addCha(String name, String mood, String desc, String back){
        SearchResponse.SearchResult item = new SearchResponse.SearchResult();
        item.setStorename(name);
        item.setMood(mood);
        item.setWriting(desc);
        item.setImg(back);
        mSearchResults.add(item);
    }
}
