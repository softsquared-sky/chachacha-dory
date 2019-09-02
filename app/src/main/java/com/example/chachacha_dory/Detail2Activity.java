package com.example.chachacha_dory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Detail2Activity extends BaseActivity implements StoreInterface {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime;
    StoreResponse.StoreResult detailStore;
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

        final StoreService storeService = new StoreService(Detail2Activity.this);
        storeService.getStoreDetail();
    }

    @Override
    public void validateSuccess(String text, int code, ArrayList<StoreResponse.StoreResult> stores) {
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
            case R.id.detail2MoreInfo:
                Intent intent = new Intent(Detail2Activity.this, ReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.detail2Phone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(mStorePhone));
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
