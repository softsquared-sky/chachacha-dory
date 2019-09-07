package com.example.chachacha_dory.src.chachacha;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.src.detail.DetailResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
            convertView = inflater.inflate(R.layout.item_chacha, parent,false);
        }
        TextView name = convertView.findViewById(R.id.nameCha);
        TextView mood = convertView.findViewById(R.id.moodCha);
        TextView desc = convertView.findViewById(R.id.descCha);
        RelativeLayout layout = convertView.findViewById(R.id.chaLayout);

        RecommendResponse.RecommendResult recommendResult = recommendResults.get(position);

        name.setText(recommendResult.getStorename());
        mood.setText(recommendResult.getMood());
        desc.setText(recommendResult.getWriting());

        try {
            layout.setBackground(drawableFromUrl(recommendResult.getImg()));
        } catch (IOException e) {
           layout.setBackgroundResource(R.color.detailBackground);
        }
//        layout.setBackground(LoadImageFromWebOperations(recommendResult.getImg()));

        return convertView;
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }

    public void LoadImageFromWebOperations(String url, View view) {
        try {
            URL url1 = new URL(url);
            InputStream is = (InputStream) url1.getContent();
            // InputStream에서 Drawable 작성
            Drawable drawable = Drawable.createFromStream(is, "");
            view.setBackground(drawable);
        } catch (Exception e) {
            view.setBackgroundResource(R.color.detailBackground);
        }
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
