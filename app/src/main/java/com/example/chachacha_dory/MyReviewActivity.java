package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MyReviewActivity extends AppCompatActivity {
    private ListView myReviewList;
    private ReviewListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        myReviewList = (ListView)findViewById(R.id.myReviewList);
        mAdapter = new ReviewListAdapter();
        myReviewList.setAdapter(mAdapter);

        mAdapter.addReview(R.drawable.start_chicken, "교촌치킨", "서울시 관악구", "맛있어요", 4);
    }
}
