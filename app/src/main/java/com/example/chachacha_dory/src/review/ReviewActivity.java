package com.example.chachacha_dory.src.review;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.R;

import java.util.ArrayList;

public class ReviewActivity extends BaseActivity implements ReviewActivityView, MenuActivityView {
    private ListView mReviewList, mMenuList;
    private ReviewListAdapter mAdapter;
    private MenuListAdapter mMenuAdapter;
    TextView mReviewCount;
    ImageView mSelectImage;
    boolean mSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        final MenuService menuService = new MenuService(this);
        menuService.getStoreMenu();

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
        mSelectImage = findViewById(R.id.reviewSelected);

        tryGetReview();
    }

    public void tryGetReview(){
        showProgressDialog();

        final ReviewService reviewService = new ReviewService(this);
        reviewService.getReview();
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<ReviewResponse.ReviewResult.Review> reviews) {
        hideProgressDialog();

        if (isSuccess) {
            mAdapter = new ReviewListAdapter(reviews);
            mReviewList.setAdapter(mAdapter);
            mReviewCount.setText("리뷰 " + mAdapter.getCount());
        } else
            showCustomToast(text);
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();

        Log.d("결과", message);
        showCustomToast(message);
    }

    @Override
    public void validateSuccessMenu(String text, int code, ArrayList<MenuResponse.MenuClass.FoodClass> menus) {
        if (code == 211) {
            mMenuAdapter = new MenuListAdapter(menus);
            mMenuList.setAdapter(mMenuAdapter);
        } else
            showCustomToast(text);
    }

    @Override
    public void validateFailureMenu(String message) {
        showCustomToast(message);
    }

    public void onClickReview(View v) {
        switch (v.getId()) {
            case R.id.reviewBack:
                onBackPressed();
                break;
            case R.id.reviewSelected:
                if (mSelected) {
                    mSelectImage.setImageResource(R.drawable.star2);
                    mSelected = false;
                } else {
                    mSelectImage.setImageResource(R.drawable.ic_select_star);
                    mSelected = true;
                }
                break;
            case R.id.chachachaBtn:
                break;
        }
    }
}