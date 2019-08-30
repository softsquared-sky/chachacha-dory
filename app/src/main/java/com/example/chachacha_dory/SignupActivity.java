package com.example.chachacha_dory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class SignUpActivity extends BaseActivity implements MainActivityView {
    EditText mEditId, mEditPw, mEditPw2, mEditName, mEditPhone, mEditEmail;
    Button mNextBtn;
    String mId, mPw, mPw2, mName, mPhone, mEmail;
    HashMap<String, Object> mHashMap;
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
                mHashMap.put("userpw2", mPw2);
                mHashMap.put("name", mName);
                mHashMap.put("age", 0);
                mHashMap.put("gender", 0);
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
        mainService.postSignUp(mHashMap);
//        mainService.postSignUp(mId, mPw, mPw2, mName, 0, 0, "dory@gmail.com");
    }

    @Override
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        if(code == 100) {
            Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, text, Toast.LENGTH_LONG);
        }
//        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateSuccessMyPage(DefaultResponse.Result result) {

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
//        Toast.makeText(this, message, Toast.LENGTH_LONG);
        Log.d("메시지내용", message);
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
