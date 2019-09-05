package com.example.chachacha_dory.src.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;

public class MakeReviewActivity extends BaseActivity {
    ImageView mBackBtn;
    EditText mEditReview;
    RatingBar mStar;
    TextView mOkText;
    String mReview;
    int mStarNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_review);

        mBackBtn = findViewById(R.id.makeReviewBack);
        mEditReview = findViewById(R.id.makeReviewInput);
        mStar = findViewById(R.id.makeReviewStar);
        mOkText = findViewById(R.id.makeReviewOk);
        LinearLayout makeReview = findViewById(R.id.makeReivew);
        LinearLayout makeReviewLayout = findViewById(R.id.makeReviewLayout);
        LinearLayout makeReviewView = findViewById(R.id.makeReivewView);

        mOkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReview = mEditReview.getText().toString();
                mStarNum = mStar.getNumStars();
            }
        });

        makeReview.setOnClickListener(keyboardClick);
        makeReviewLayout.setOnClickListener(keyboardClick);
        makeReviewView.setOnClickListener(keyboardClick);
        mOkText.setOnClickListener(keyboardClick);
        mStar.setOnClickListener(keyboardClick);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    View.OnClickListener keyboardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard(mEditReview);
        }
    };
}
