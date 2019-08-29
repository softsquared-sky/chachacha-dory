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

public class ReviewListAdapter extends BaseAdapter {
    private ArrayList<ReviewClass> reviewList = new ArrayList<ReviewClass>();
    ReviewListAdapter(){}

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewList.get(position);
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
            convertView = inflater.inflate(R.layout.item_review, parent,false);
        }
        ImageView image = convertView.findViewById(R.id.reviewImage);
        TextView name = convertView.findViewById(R.id.reviewName);
        TextView addr = convertView.findViewById(R.id.reviewAddr);
        TextView review = convertView.findViewById(R.id.reviewText);
        RatingBar ratingBar = convertView.findViewById(R.id.reviewStar);

        ReviewClass reviewClass = reviewList.get(position);

        image.setImageResource(reviewClass.getImage());
        image.setBackground(new ShapeDrawable(new OvalShape()));
        image.setClipToOutline(true);
        name.setText(reviewClass.getName());
        addr.setText(reviewClass.getAddress());
        review.setText(reviewClass.getReview());
        ratingBar.setRating(reviewClass.getStarRating());
        return convertView;
    }

    public void addReview(int icon, String name, String addr, String review, int star){
        ReviewClass item = new ReviewClass();
        item.setImage(icon);
        item.setName(name);
        item.setAddress(addr);
        item.setReview(review);
        item.setStarRating(star);

        reviewList.add(item);
    }
}
