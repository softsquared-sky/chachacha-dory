package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ReviewActivity extends BaseActivity implements ReviewInterface, StoreMenuInterface{
    private ListView mReviewList, mMenuList;
    private ReviewListAdapter mAdapter;
    private MenuListAdapter mMenuAdapter;
    TextView mReviewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        final ServiceReview serviceReview = new ServiceReview(this);
        serviceReview.getReview();

        final ServiceMenu serviceMenu = new ServiceMenu(this);
        serviceMenu.getStoreMenu();

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

        mMenuList = findViewById(R.id.menuList);
        mReviewList = findViewById(R.id.reviewList);
        mReviewCount = findViewById(R.id.reviewCount);

    }

    @Override
    public void validateSuccess(String text, int code, ArrayList<ResponseReview.ReviewResult.Review> reviews) {
        if(code==209){
            mAdapter = new ReviewListAdapter(reviews);
            mReviewList.setAdapter(mAdapter);
            mReviewCount.setText("리뷰 "+mAdapter.getCount());
        } else
            showCustomToast(text);
    }

    @Override
    public void validateFailure(String message) {
        Log.d("결과", message);
        showCustomToast(message);
    }

    @Override
    public void validateSuccessMenu(String text, int code, ArrayList<ResponseMenu.MenuClass.FoodClass> menus) {
        if(code==211){
            mMenuAdapter = new MenuListAdapter(menus);
            mMenuList.setAdapter(mMenuAdapter);
        } else
            showCustomToast(text);
    }

    @Override
    public void validateFailureMenu(String message) {
        showCustomToast(message);
    }
}
