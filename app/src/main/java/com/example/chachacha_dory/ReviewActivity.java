package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import com.google.android.material.tabs.TabLayout;

public class ReviewActivity extends AppCompatActivity {
    private ListView mReviewList;
    private Review2ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        
        mReviewList = findViewById(R.id.review2List);
        mAdapter = new Review2ListAdapter();
        mReviewList.setAdapter(mAdapter);

        mAdapter.addReview2("김다정", "맛있어요", 3);

        TabHost host = findViewById(R.id.host);
        host.setup();
        TabHost.TabSpec spec = host.newTabSpec("menuTab");
        spec.setIndicator("메뉴");
        spec.setContent(R.id.menuTab);
        host.addTab(spec);

        spec = host.newTabSpec("reviewTab");
        spec.setIndicator("리뷰");
        spec.setContent(R.id.reviewTab);
        host.addTab(spec);

    }
}
