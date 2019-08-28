package com.example.chachacha_dory;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements MainActivityView {
    EditText mIdEdit, mPwEdit;
    String mId, mPw;
    Button mLoginBtn;
    TextView mSignUpText;
    HashMap<String, Object> mHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHashMap = new HashMap<>();

        mIdEdit = findViewById(R.id.idEditText);
        mPwEdit = findViewById(R.id.pwEditText);
        mLoginBtn = findViewById(R.id.loginBtn);
        mSignUpText = findViewById(R.id.signupTextview);

        mSignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mId = mIdEdit.getText().toString();
                mPw = mPwEdit.getText().toString();
                mHashMap.put("userid", mId);
                mHashMap.put("userpw", mPw);
                tryGetLogin();
            }
        });

    }

    //    private void tryGetTest() {
//        showProgressDialog();
//
//        final MainService mainService = new MainService(this);
//        mainService.getTest();
//    }
    private void tryGetLogin(){
        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getLogin(mHashMap);
    }
    @Override
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        if(code==115){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else
            Toast.makeText(this, text, Toast.LENGTH_LONG);

//        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("결과", message);
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.loginBtn:
//                mId = mIdEdit.getText().toString();
//                mPw = mPwEdit.getText().toString();
//                tryGetLogin();
//            default:
//                break;
//        }
//    }
}
