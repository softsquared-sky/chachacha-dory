package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ReviewActivity extends BaseActivity implements ReviewInterface{
    private ListView mReviewList;
    private ReviewListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        TextView reviewCount = findViewById(R.id.reviewCount);

        ServiceReview serviceReview = new ServiceReview(this);
        serviceReview.getMyReview();

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

        mReviewList = findViewById(R.id.reviewList);
        mAdapter = new ReviewListAdapter();
        mReviewList.setAdapter(mAdapter);

//        mAdapter.addReview("김다정", "맛있어요", 3);

        reviewCount.setText("리뷰 "+mAdapter.getCount());
    }

    @Override
    public void validateSuccess(String text, int code) {
        showCustomToast(text);
        if(code==202){

        }
    }

    @Override
    public void validateReview(ArrayList<ResponseReview.ReviewResult.Review> reviews) {

    }

    @Override
    public void validateFailure(String message) {
        Log.d("결과", message);
        showCustomToast(message);
    }
}
