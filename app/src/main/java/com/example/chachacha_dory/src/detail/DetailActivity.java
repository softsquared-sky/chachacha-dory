package com.example.chachacha_dory.src.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.mypage.MainActivity;
import com.example.chachacha_dory.src.review.MoreInfoActivity;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity implements DetailActivityView, MyChaSaveActivityView {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime, mStoreDesc;
    DetailResponse.DetailResult detailStore;
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

        tryGetDetail();
    }

    public void tryGetDetail(){
        showProgressDialog();

        final DetailService detailService = new DetailService(DetailActivity.this);
        detailService.getStoreDetail(mStoreNum);
    }

    public void trySaveMyCha(){
        showProgressDialog();

        MyChaSaveService myChaService = new MyChaSaveService(this);
        myChaService.postMyCha(mStoreNum);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<DetailResponse.DetailResult> stores) {
        hideProgressDialog();
        if(isSuccess){
            detailStore = stores.get(0);
            mStoreNameMain.setText(detailStore.getStorename());
            mStoreName.setText(detailStore.getStorename());
            mStoreMood.setText(detailStore.getMood());
            mStoreAddr.setText(detailStore.getAddr());
            mStoreDesc.setText(detailStore.getWriting());
            mStoreTime.setText(detailStore.getOpen()+ " ~ " +detailStore.getClose());
            Glide.with(this).load(detailStore.getImg()).into(mStoreImage);
            mStorePhone = detailStore.getPhone();
        }else
            showCustomToast(text);
    }

    private void trySaveBookmark(){

    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        showCustomToast(text);
        if(isSuccess){
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void onClickBtn(View v){
        switch (v.getId()) {
            case R.id.detailMoreInfo:
                Intent intent = new Intent(DetailActivity.this, MoreInfoActivity.class);
                intent.putExtra("storeNum", mStoreNum);
                intent.putExtra("myCha", false);
                startActivity(intent);
                break;
            case R.id.detailPhone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mStorePhone));
                startActivity(intent2);
                break;
            case R.id.detailBackBtn:
                onBackPressed();
                break;
            case R.id.detailSaveStoreBtn:
                if(mSelected) {
                    mSelectStar.setImageResource(R.drawable.star2);
                    mSelected = false;
                }else {
                    mSelectStar.setImageResource(R.drawable.ic_select_star);
                    mSelected = true;
                }
                break;
            case R.id.chaBtn:
                trySaveMyCha();
                break;
        }
    }

}
