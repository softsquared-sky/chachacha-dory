package com.example.chachacha_dory.src.mychachacha;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chachacha_dory.R;

import java.io.InputStream;
import java.net.URL;
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
    public Object getItem(int position) {
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

        URL url = null;
        try {
            url = new URL(myChaClass.getImg());
            InputStream is = (InputStream) url.getContent();
            // InputStream에서 Drawable 작성
            Drawable drawable = Drawable.createFromStream(is, "");
            image.setImageDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        image.setImageDrawable(LoadImageFromWebOperations(myChaClass.getImg()));
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
