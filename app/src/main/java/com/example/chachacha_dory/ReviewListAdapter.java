package com.example.chachacha_dory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewListAdapter extends BaseAdapter {
    private ArrayList<ReviewResponse.ReviewResult.Review> reviewList;

    ReviewListAdapter(ArrayList<ReviewResponse.ReviewResult.Review> reviews) {
        reviewList = reviews;
    }

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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_review2, parent, false);
        }
        TextView pName = convertView.findViewById(R.id.review2Name);
        TextView review = convertView.findViewById(R.id.review2Text);
        RatingBar ratingBar = convertView.findViewById(R.id.review2Star);

        ReviewResponse.ReviewResult.Review reviews = reviewList.get(position);

        pName.setText(reviews.getName());
        review.setText(reviews.getText());
        ratingBar.setRating(reviews.getStar());
        return convertView;
    }

    public void addReview(String pName, String review, int star) {
        ReviewResponse.ReviewResult.Review item = new ReviewResponse.ReviewResult.Review();
        item.setName(pName);
        item.setText(review);
        item.setStar(star);

        reviewList.add(item);
    }
}
