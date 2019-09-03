package com.example.chachacha_dory.src.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chachacha_dory.R;

public class MakeReviewActivity extends AppCompatActivity {
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

        mOkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReview = mEditReview.getText().toString();
                mStarNum = mStar.getNumStars();

            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
