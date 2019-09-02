package com.example.chachacha_dory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyChaListAdapter extends BaseAdapter {
    private ArrayList<StoreResponse.StoreResult> mChaList;
    MyChaListAdapter(ArrayList<StoreResponse.StoreResult> stores){
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

        StoreResponse.StoreResult myChaClass = mChaList.get(position);

        image.setImageDrawable(LoadImageFromWebOperations(myChaClass.getImg()));
        name.setText(myChaClass.getStorename());

        return convertView;
    }

    public void addMyCha(String icon, String name){
        StoreResponse.StoreResult item = new StoreResponse.StoreResult();
        item.setImg(icon);
        item.setStorename(name);

        mChaList.add(item);
    }

    public Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        }catch (Exception e) {
            System.out.println("Exc="+e);
            return null;
        }
    }
}
