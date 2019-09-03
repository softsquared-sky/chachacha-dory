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

public class Detail2Activity extends BaseActivity implements DetailActivityView {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime, mStoreDesc;
    DetailResponse.DetailResult detailStore;
    View mStoreImage;
    String mStorePhone;
    boolean mSelected;
    ImageView mSelectStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        mStoreNameMain = findViewById(R.id.detail2StoreName);
        mStoreName = findViewById(R.id.detail2Name);
        mStoreMood = findViewById(R.id.detail2Mood);
        mStoreAddr = findViewById(R.id.detail2Address);
        mStoreTime = findViewById(R.id.detail2Time);
        mStoreImage = findViewById(R.id.detail2Image);
        mSelectStar = findViewById(R.id.detail2SaveBtn);
        mStoreDesc = findViewById(R.id.detail2Desc);

        tryGetDetail2();
    }

    public void tryGetDetail2(){
        showProgressDialog();
        final DetailService detailService = new DetailService(Detail2Activity.this);
        detailService.getStoreDetail();
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
            mStoreImage.setBackground(LoadImageFromWebOperations(detailStore.getImg()));
            mStorePhone = detailStore.getPhone();
        }else
            showCustomToast(text);
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void onClickBtn(View v){
        switch (v.getId()) {
            case R.id.detail2MoreInfo:
                Intent intent = new Intent(Detail2Activity.this, ReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.detail2Phone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mStorePhone));
                startActivity(intent2);
                break;
            case R.id.detail2Back:
                onBackPressed();
                break;
            case R.id.detail2SaveBtn:
                if(mSelected) {
                    mSelectStar.setImageResource(R.drawable.star2);
                    mSelected = false;
                }else {
                    mSelectStar.setImageResource(R.drawable.ic_select_star);
                    mSelected = true;
                }
                break;
        }
    }
}
