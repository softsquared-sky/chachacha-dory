package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ReviewActivity extends AppCompatActivity {
    private ListView mReviewList;
    private Review2ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mReviewList = (ListView)findViewById(R.id.review2List);
        mAdapter = new Review2ListAdapter();
        mReviewList.setAdapter(mAdapter);

        mAdapter.addReview2("김다정", "맛있어요", 3);
    }
}
