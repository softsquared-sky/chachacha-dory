package com.example.chachacha_dory.src.chachacha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.detail.DetailActivity;
import com.example.chachacha_dory.src.mypage.MainActivity;

import java.util.ArrayList;

public class ChaChaActivity extends BaseActivity implements RecommendActivityView{
    private ListView mListview;
    private ChaListAdapter mAdapter;
    ImageView mBackBtn;
    RelativeLayout mZeroLayout;
    Button mSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chacha);

        final Intent intent = getIntent();
        int people = intent.getIntExtra("people", 1);
        String kind = intent.getStringExtra("kind");
        String mood = intent.getStringExtra("mood");

        mListview = findViewById(R.id.chachaList);
        mBackBtn = findViewById(R.id.chachaBackBtn);
        mZeroLayout = findViewById(R.id.chachaZero);
        mSearchBtn = findViewById(R.id.searchBtnCha);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(ChaChaActivity.this, DetailActivity.class);
                intent1.putExtra("storeNum", mAdapter.getItem(position).getStoreNum());
                startActivity(intent1);
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChaChaActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("what", 3);
                startActivity(intent1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tryGetRecommend(people, kind, mood);
    }

    private void tryGetRecommend(int people, String kind, String mood){
        showProgressDialog();

        final RecommendService recommendService = new RecommendService(this);
        recommendService.getRecommend(people, kind, mood);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<RecommendResponse.RecommendResult> stores) {
        hideProgressDialog();
        if(isSuccess){
            mAdapter = new ChaListAdapter(stores);
            mListview.setAdapter(mAdapter);
            if(mAdapter.getCount()==0){
                mZeroLayout.setVisibility(View.VISIBLE);
            }
        }else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}
