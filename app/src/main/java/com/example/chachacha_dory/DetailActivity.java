package com.example.chachacha_dory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity implements StoreInterface {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime;
    ResponseStore.StoreResult detailStore;
    View mStoreImage;
    String mStorePhone;

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

        final ServiceStore serviceStore = new ServiceStore(DetailActivity.this);
        serviceStore.getStoreDetail();
    }

    @Override
    public void validateSuccess(String text, int code, ArrayList<ResponseStore.StoreResult> stores) {
        if(code==207){
            detailStore = stores.get(0);
            mStoreNameMain.setText(detailStore.getStorename());
            mStoreName.setText(detailStore.getStorename());
            mStoreMood.setText(detailStore.getMood());
            mStoreAddr.setText(detailStore.getAddr());
            mStoreTime.setText(detailStore.getOpen()+ " ~ " +detailStore.getClose());
            mStoreImage.setBackground(LoadImageFromWebOperations(detailStore.getImg()));
            mStorePhone = detailStore.getPhone();
        }else
            showCustomToast(text);
    }

    @Override
    public void validateFailure(String message) {
        showCustomToast(message);
    }

    public void onClickBtn(View v){
        switch (v.getId()) {
            case R.id.detailMoreInfo:
                Intent intent = new Intent(DetailActivity.this, ReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.detailPhone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(mStorePhone));
                startActivity(intent2);
            case R.id.detailBackBtn:
                onBackPressed();
        }
    }

}
