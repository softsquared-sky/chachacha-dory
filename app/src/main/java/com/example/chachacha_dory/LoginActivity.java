package com.example.chachacha_dory;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity implements MainActivityView {
    EditText mIdEdit, mPwEdit;
    String mId, mPw;
    Button mLoginBtn;
    TextView mSignUpText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        mainService.getLogin(mId, mPw);
    }
    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        mIdEdit.setText(text);
//        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
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
