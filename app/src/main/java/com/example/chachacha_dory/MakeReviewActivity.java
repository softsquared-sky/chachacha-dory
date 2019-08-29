package com.example.chachacha_dory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MakeReviewActivity extends AppCompatActivity {
    ImageView mBackBtn;
    EditText mEditReview;
    RatingBar mStar;
    TextView mOkText;
    String mReview;
    int starNum;

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
                starNum = mStar.getNumStars();

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
