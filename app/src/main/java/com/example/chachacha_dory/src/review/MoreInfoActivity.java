package com.example.chachacha_dory.src.review;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.src.detail.MyChaSaveActivityView;
import com.example.chachacha_dory.src.detail.MyChaSaveService;
import com.example.chachacha_dory.src.mychachacha.MakeReviewActivity;

import java.util.ArrayList;

public class MoreInfoActivity extends BaseActivity implements ReviewActivityView, MenuActivityView, MyChaSaveActivityView {
    private ListView mReviewList, mMenuList;
    private ReviewListAdapter mAdapter;
    private MenuListAdapter mMenuAdapter;
    TextView mReviewCount;
    ImageView mSelectImage;
    boolean mSelected, mIsMyCha;
    Button mChaBtn;
    int mStoreNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        Intent intent = getIntent();
        mStoreNum = intent.getIntExtra("storeNum", 1);
        mIsMyCha = intent.getBooleanExtra("myCha", false);

        mChaBtn = findViewById(R.id.chachachaBtn);

        if(mIsMyCha){
            mChaBtn.setText("리뷰 쓰러가기");
        }

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

        tryGetReview(mStoreNum);
        tryGetMenu(mStoreNum);
    }

    public void tryGetReview(int storeNum){
        showProgressDialog();

        final ReviewService reviewService = new ReviewService(this);
        reviewService.getReview(storeNum);
    }

    public void tryGetMenu(int storeNum){
        showProgressDialog();

        final MenuService menuService = new MenuService(this);
        menuService.getStoreMenu(storeNum);
    }

    public void tryPostMyCha(int storeNum){
        showProgressDialog();

        final MyChaSaveService myChaSaveService = new MyChaSaveService(this);
        myChaSaveService.postMyCha(storeNum);
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
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();

        if(isSuccess){
            showCustomToast(text);
        }else {
            showCustomToast(text);
        }
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
                if(mIsMyCha){
                    Intent intent = new Intent(MoreInfoActivity.this, MakeReviewActivity.class);

                    startActivity(intent);
                }else {
                    tryPostMyCha(mStoreNum);
                }
                break;
        }
    }
}
