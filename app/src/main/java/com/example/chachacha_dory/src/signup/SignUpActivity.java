package com.example.chachacha_dory.src.signup;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.R;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {
    EditText mEditId, mEditPw, mEditPw2, mEditName, mEditPhone, mEditEmail;
    Button mGender0Btn, mGender1Btn, mAge0Btn, mAge1Btn, mAge2Btn, mAge3Btn;
    int mSelectedAge, mSelectedGender;
    ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEditId = findViewById(R.id.signUpId);
        mEditPw = findViewById(R.id.signUpPw);
        mEditPw2 = findViewById(R.id.signUpPw2);
        mEditName = findViewById(R.id.signUpName);
        mEditPhone = findViewById(R.id.signUpPhone);
        mEditEmail = findViewById(R.id.signUpEmail);
        mGender0Btn = findViewById(R.id.signUpGender0);
        mGender1Btn = findViewById(R.id.signUpGender1);
        mAge0Btn = findViewById(R.id.signUpAge0);
        mAge1Btn = findViewById(R.id.signUpAge1);
        mAge2Btn = findViewById(R.id.signUpAge2);
        mAge3Btn = findViewById(R.id.signUpAge3);
        mBackBtn = findViewById(R.id.signUpBackBtn);


        Button nextBtn = findViewById(R.id.signupNextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPostSignUp();
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void tryPostSignUp(){
        showProgressDialog();

        String id = mEditId.getText().toString();
        String pw = mEditPw.getText().toString();
        String pw2 = mEditPw2.getText().toString();
        String name = mEditName.getText().toString();
        String phone = mEditPhone.getText().toString();
        String email = mEditEmail.getText().toString();

        final SignUpService signUpService = new SignUpService(this);

        if(!pw.equals(pw2)){
            showCustomToast("비밀번호가 일치하지 않습니다.");
            hideProgressDialog();
        }else {
            signUpService.postSignUp(id, pw, name, mSelectedAge, mSelectedGender, email, phone);
        }
    }

    public void clickAge(View v){
        clearAgeBtn();
        switch (v.getId()){
            case R.id.signUpAge0:
                mAge0Btn.setBackgroundResource(R.drawable.roundback_border);
                mAge0Btn.setTextColor(Color.WHITE);
                mSelectedAge = 0;
                break;
            case R.id.signUpAge1:
                mAge1Btn.setBackgroundResource(R.drawable.roundback_border);
                mAge1Btn.setTextColor(Color.WHITE);
                mSelectedAge = 1;
                break;
            case R.id.signUpAge2:
                mAge2Btn.setBackgroundResource(R.drawable.roundback_border);
                mAge2Btn.setTextColor(Color.WHITE);
                mSelectedAge = 2;
                break;
            case R.id.signUpAge3:
                mAge3Btn.setBackgroundResource(R.drawable.roundback_border);
                mAge3Btn.setTextColor(Color.WHITE);
                mSelectedAge = 3;
                break;
        }
    }

    public void clickGender(View v){
        switch (v.getId()){
            case R.id.signUpGender0:
                mGender1Btn.setBackgroundResource(R.drawable.round_border);
                mGender1Btn.setTextColor(Color.BLACK);
                mGender0Btn.setBackgroundResource(R.drawable.roundback_border);
                mGender0Btn.setTextColor(Color.WHITE);
                mSelectedGender = 0;
                break;
            case R.id.signUpGender1:
                mGender0Btn.setBackgroundResource(R.drawable.round_border);
                mGender0Btn.setTextColor(Color.BLACK);
                mGender1Btn.setBackgroundResource(R.drawable.roundback_border);
                mGender1Btn.setTextColor(Color.WHITE);
                mSelectedGender = 1;
                break;
        }
    }

    public void clearAgeBtn(){
        mAge0Btn.setBackgroundResource(R.drawable.round_border);
        mAge1Btn.setBackgroundResource(R.drawable.round_border);
        mAge2Btn.setBackgroundResource(R.drawable.round_border);
        mAge3Btn.setBackgroundResource(R.drawable.round_border);
        mAge0Btn.setTextColor(Color.BLACK);
        mAge1Btn.setTextColor(Color.BLACK);
        mAge2Btn.setTextColor(Color.BLACK);
        mAge3Btn.setTextColor(Color.BLACK);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        if(isSuccess) {
            Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("메시지내용", message);
        showCustomToast(message);
    }
}
