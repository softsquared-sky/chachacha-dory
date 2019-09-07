package com.example.chachacha_dory.src.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chachacha_dory.R;

import java.util.ArrayList;

public class MyPageReviewListAdapter extends BaseAdapter {
    private ArrayList<MyPageReviewResponse.MyPageReviewResult> reviewList;
    MyPageReviewListAdapter(ArrayList<MyPageReviewResponse.MyPageReviewResult> arrayList){
        reviewList = arrayList;
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

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_my_review, parent,false);
        }
        ImageView image = convertView.findViewById(R.id.reviewImage);
        TextView name = convertView.findViewById(R.id.reviewName);
        TextView addr = convertView.findViewById(R.id.reviewAddr);
        TextView review = convertView.findViewById(R.id.reviewText);
        RatingBar ratingBar = convertView.findViewById(R.id.reviewStar);

        MyPageReviewResponse.MyPageReviewResult reviewClass = reviewList.get(position);

//        image.setImageResource(reviewClass.getImage());
//        image.setBackground(new ShapeDrawable(new OvalShape()));
//        image.setClipToOutline(true);
        name.setText(reviewClass.getStorename());
        addr.setText(reviewClass.getAddress());
        review.setText(reviewClass.getText());
        ratingBar.setRating(reviewClass.getStar());

        return convertView;
    }

    public void addMyReview(int icon, String name, String addr, String review, int star){
         MyPageReviewResponse.MyPageReviewResult item = new MyPageReviewResponse.MyPageReviewResult();
//        item.setImage(icon);
        item.setStorename(name);
        item.setAddress(addr);
        item.setText(review);
        item.setStar(star);

        reviewList.add(item);
    }
}
