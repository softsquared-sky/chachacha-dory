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

public class Review2ListAdapter extends BaseAdapter {
    private ArrayList<ReviewClass> review2List = new ArrayList<ReviewClass>();
    Review2ListAdapter(){}

    @Override
    public int getCount() {
        return review2List.size();
    }

    @Override
    public Object getItem(int position) {
        return review2List.get(position);
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
            convertView = inflater.inflate(R.layout.item_review2, parent,false);
        }
        TextView pName = (TextView)convertView.findViewById(R.id.review2Name);
        TextView review = (TextView)convertView.findViewById(R.id.review2Text);
        RatingBar ratingBar = (RatingBar)convertView.findViewById(R.id.review2Star);

        ReviewClass reviewClass = review2List.get(position);

        pName.setText(reviewClass.getpName());
        review.setText(reviewClass.getReview());
        ratingBar.setRating(reviewClass.getStarRating());
        return convertView;
    }

    public void addReview2(String pName, String review, int star){
        ReviewClass item = new ReviewClass();
        item.setpName(pName);
        item.setReview(review);
        item.setStarRating(star);

        review2List.add(item);
    }
}
