package com.example.chachacha_dory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class SignUpActivity extends BaseActivity implements MainActivityView {
    EditText mEditId, mEditPw, mEditPw2, mEditName, mEditPhone, mEditEmail;
    Button mNextBtn, mGender0Btn, mGender1Btn, mAge0Btn, mAge1Btn, mAge2Btn, mAge3Btn;
    String mId, mPw, mPw2, mName, mPhone, mEmail;
    HashMap<String, Object> mHashMap;
    int mSelectedAge, mSelectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEditId = findViewById(R.id.signUpId);
        mEditPw = findViewById(R.id.signUpPw);
        mEditPw2 = findViewById(R.id.signUpPw2);
        mEditName = findViewById(R.id.signUpName);
        mNextBtn = findViewById(R.id.signupNextBtn);
        mEditPhone = findViewById(R.id.signUpPhone);
        mEditEmail = findViewById(R.id.signUpEmail);
        mGender0Btn = findViewById(R.id.signUpGender0);
        mGender1Btn = findViewById(R.id.signUpGender1);
        mAge0Btn = findViewById(R.id.signUpAge0);
        mAge1Btn = findViewById(R.id.signUpAge1);
        mAge2Btn = findViewById(R.id.signUpAge2);
        mAge3Btn = findViewById(R.id.signUpAge3);

        mHashMap = new HashMap<>();

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mId = mEditId.getText().toString();
                mPw = mEditPw.getText().toString();
                mPw2 = mEditPw2.getText().toString();
                mName = mEditName.getText().toString();
                mPhone = mEditPhone.getText().toString();
                mEmail = mEditEmail.getText().toString();

                mHashMap.put("userid", mId);
                mHashMap.put("userpw", mPw);
                mHashMap.put("name", mName);
                mHashMap.put("age", mSelectedAge);
                mHashMap.put("gender", mSelectedGender);
                mHashMap.put("phone", mPhone);
                mHashMap.put("email", mEmail);

                tryPostSignUp();
            }
        });

    }

    private void tryPostSignUp(){
        showProgressDialog();
        final MainService mainService = new MainService(this);
        Log.d("userid내용", mId);

        if(!mPw.equals(mPw2)){
            showCustomToast("비밀번호가 일치하지 않습니다.");
            hideProgressDialog();
        }else {
            Log.d("Age값결과", ""+mHashMap.get("age"));
            Log.d("Gender값 결과", ""+mHashMap.get("gender"));
            mainService.postSignUp(mHashMap);
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
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        if(code == 100) {
            Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateSuccessMyPage(DefaultResponse.Result result) {

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("메시지내용", message);
        showCustomToast(message);
    }
}
