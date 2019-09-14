package com.example.chachacha_dory.src.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.bookmark.SaveMyBarActivityView;
import com.example.chachacha_dory.src.bookmark.SaveMyBarService;
import com.example.chachacha_dory.src.review.MoreInfoActivity;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity implements DetailActivityView, MyChaSaveActivityView, SaveMyBarActivityView {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime, mStoreDesc;
    DetailResponse.DetailRes.DetailResult detailStore;
    ImageView mStoreImage;
    String mStorePhone;
    ImageView mSelectStar;
    boolean mSelected;
    int mStoreNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mStoreNum = intent.getIntExtra("storeNum", 1);

        mStoreNameMain = findViewById(R.id.detailStoreName);
        mStoreName = findViewById(R.id.detailName);
        mStoreMood = findViewById(R.id.detailMood);
        mStoreAddr = findViewById(R.id.detailAddress);
        mStoreTime = findViewById(R.id.detailTime);
        mStoreImage = findViewById(R.id.detailStoreImg);
        mSelectStar = findViewById(R.id.detailSaveStoreBtn);
        mStoreDesc = findViewById(R.id.detailDesc);
    }

    @Override
    protected void onResume() {
        super.onResume();

        tryGetDetail();
    }

    public void tryGetDetail() {
        showProgressDialog();

        final DetailService detailService = new DetailService(DetailActivity.this);
        detailService.getStoreDetail(mStoreNum);
    }

    public void trySaveMyCha() {
        showProgressDialog();

        MyChaSaveService myChaService = new MyChaSaveService(this);
        myChaService.postMyCha(mStoreNum);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, int isBook, ArrayList<DetailResponse.DetailRes.DetailResult> stores) {
        hideProgressDialog();
        if (isSuccess) {
            detailStore = stores.get(0);
            mStoreNameMain.setText(detailStore.getStorename());
            mStoreName.setText(detailStore.getStorename());
            mStoreMood.setText(detailStore.getMood());
            mStoreAddr.setText(detailStore.getAddr());
            mStoreDesc.setText(detailStore.getWriting());
            mStoreTime.setText(detailStore.getOpen() + " ~ " + detailStore.getClose());
            Glide.with(this).load(detailStore.getImg()).into(mStoreImage);
            mStorePhone = detailStore.getPhone();
            if(isBook==1){
                mSelected = true;
                mSelectStar.setImageResource(R.drawable.select);
            }
        } else
            showCustomToast(text);
    }

    private void trySaveBookmark() {
        showProgressDialog();

        final SaveMyBarService saveMyBarService = new SaveMyBarService(this);
        saveMyBarService.saveBookMark(mStoreNum);
    }

    @Override
    public void validateSuccessSave(String text, boolean isSuccess) {
        hideProgressDialog();
        showCustomToast(text);
        if (isSuccess) {
            if (mSelected) {
                mSelectStar.setImageResource(R.drawable.star2);
                mSelected = false;
            } else {
                mSelectStar.setImageResource(R.drawable.select);
                mSelected = true;
            }
        }
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        showCustomToast(text);
        if (isSuccess) {
            finish();
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.detailMoreInfo:
                Intent intent = new Intent(DetailActivity.this, MoreInfoActivity.class);
                intent.putExtra("storeNum", mStoreNum);
                intent.putExtra("myCha", false);
                intent.putExtra("isExistbook", mSelected);
                startActivity(intent);
                break;
            case R.id.detailPhone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mStorePhone));
                startActivity(intent2);
                break;
            case R.id.detailBackBtn:
                onBackPressed();
                break;
            case R.id.detailSaveStoreBtn:
                trySaveBookmark();
                break;
            case R.id.chaBtn:
                trySaveMyCha();
                break;
        }
    }

}
