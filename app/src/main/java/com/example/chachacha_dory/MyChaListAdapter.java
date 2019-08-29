package com.example.chachacha_dory;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MyChaListAdapter extends BaseAdapter {
    private ArrayList<MyChaClass> mChaList = new ArrayList<>();
    MyChaListAdapter(){}

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

        MyChaClass myChaClass = mChaList.get(position);

        image.setImageResource(myChaClass.getImg());
        name.setText(myChaClass.getName());

        return convertView;
    }

    public void addMyCha(int icon, String name){
        MyChaClass item = new MyChaClass();
        item.setImg(icon);
        item.setName(name);

        mChaList.add(item);
    }
}
