package com.example.chachacha_dory.src.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.review.ReviewActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends BaseActivity implements DetailActivityView, MyChaSaveActivityView {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime, mStoreDesc;
    DetailResponse.DetailResult detailStore;
    View mStoreImage;
    String mStorePhone;
    ImageView mSelectStar;
    boolean mSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
        detailService.getStoreDetail();
    }

    public void trySaveMyCha(){
        showProgressDialog();

//                mHashMap.put("storename", mStoreName.getText().toString());
        MyChaSaveService myChaService = new MyChaSaveService(this);
        myChaService.postMyCha(1);
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
//            mStoreImage.setBackground(LoadImageFromWebOperations(detailStore.getImg()));
            mStorePhone = detailStore.getPhone();
        }else
            showCustomToast(text);
    }


    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
//        if(isSuccess){
            showCustomToast(text);
//        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void onClickBtn(View v){
        switch (v.getId()) {
            case R.id.detailMoreInfo:
                Intent intent = new Intent(DetailActivity.this, ReviewActivity.class);
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
