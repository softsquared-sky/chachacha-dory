package com.example.chachacha_dory.src.mychachacha;

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
import com.example.chachacha_dory.src.detail.DetailResponse;
import com.example.chachacha_dory.src.review.MoreInfoActivity;

public class MyChaDetailActivity extends BaseActivity implements MyChaDetailActivityView, SaveMyBarActivityView {
    TextView mStoreName, mStoreNameMain, mStoreMood, mStoreAddr, mStoreTime, mStoreDesc;
    ImageView mStoreImage;
    String mStorePhone;
    boolean mSelected;
    ImageView mSelectStar;
    int mChaNum, mStoreNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cha_detail);

        Intent intent = getIntent();
        mChaNum = intent.getIntExtra("chaNum", 35);
        mStoreNum = intent.getIntExtra("storeNum", 1);

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

        final MyChaDetailService detailService = new MyChaDetailService(MyChaDetailActivity.this);
        detailService.getDetailMyCha(mChaNum);
    }

    private void trySaveBookmark(){
        showProgressDialog();

        final SaveMyBarService saveMyBarService = new SaveMyBarService(MyChaDetailActivity.this);
        saveMyBarService.saveBookMark(mStoreNum);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, DetailResponse.DetailResult store) {
        hideProgressDialog();
        if(isSuccess){
            mStoreNameMain.setText(store.getStorename());
            mStoreName.setText(store.getStorename());
            mStoreMood.setText(store.getMood());
            mStoreAddr.setText(store.getAddr());
            mStoreDesc.setText(store.getWriting());
            mStoreTime.setText(store.getOpen()+ " ~ " +store.getClose());
            Glide.with(this).load(store.getImg()).into(mStoreImage);
            mStorePhone = store.getPhone();
        }else
            showCustomToast(text);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        showCustomToast(text);
        if(isSuccess){
            if(mSelected) {
                mSelectStar.setImageResource(R.drawable.star2);
                mSelected = false;
            }else {
                mSelectStar.setImageResource(R.drawable.ic_select_star);
                mSelected = true;
            }
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    public void onClickBtn(View v){
        switch (v.getId()) {
            case R.id.detail2MoreInfo:
                Intent intent = new Intent(MyChaDetailActivity.this, MoreInfoActivity.class);
                intent.putExtra("storeNum", mStoreNum);
                startActivity(intent);
                break;
            case R.id.detail2Phone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mStorePhone));
                startActivity(intent2);
                break;
            case R.id.detail2Back:
                onBackPressed();
                break;
            case R.id.detail2ReviewBtn:
                Intent intent1 = new Intent(MyChaDetailActivity.this, MakeReviewActivity.class);
                intent1.putExtra("chaNum", mChaNum);
                intent1.putExtra("myCha", true);
                startActivity(intent1);
                break;
            case R.id.detail2SaveBtn:
                trySaveBookmark();
                break;
        }
    }
}
