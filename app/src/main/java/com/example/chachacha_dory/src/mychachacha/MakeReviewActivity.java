package com.example.chachacha_dory.src.mychachacha;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.mypage.MainActivity;

public class MakeReviewActivity extends BaseActivity implements MakeReviewActivityView, TextView.OnEditorActionListener {
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

        Intent intent = getIntent();
        final int chaNum = intent.getIntExtra("chaNum", 0);

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
                hideKeyboard(mEditReview);
                tryPostReview(chaNum);
            }
        });

        mEditReview.setOnEditorActionListener(this);
        makeReview.setOnClickListener(keyboardClick);
        makeReviewLayout.setOnClickListener(keyboardClick);
        makeReviewView.setOnClickListener(keyboardClick);
        mStar.setOnClickListener(keyboardClick);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void tryPostReview(int chaNum){
        showProgressDialog();
        mReview = mEditReview.getText().toString();
        mStarNum = mStar.getNumStars();

        final MakeReviewService makeReviewService = new MakeReviewService(this);
        makeReviewService.postReview(chaNum, mReview, mStarNum);
    }

    View.OnClickListener keyboardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard(mEditReview);
        }
    };

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        showCustomToast(text);
        if(isSuccess){
            Intent intent = new Intent(MakeReviewActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(v.getId()==R.id.makeReviewInput && actionId== EditorInfo.IME_ACTION_DONE){ // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
            hideKeyboard(mEditReview);
        }

        return false;
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}
