package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MyReviewActivity extends AppCompatActivity {
    private ListView myReviewList;
    private ReviewListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        myReviewList = (ListView)findViewById(R.id.myReviewList);
        adapter = new ReviewListAdapter();
        myReviewList.setAdapter(adapter);

        adapter.addReview(R.drawable.start_chicken, "교촌치킨", "서울시 관악구", "맛있어요", 4);
    }
}
