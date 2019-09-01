package com.example.chachacha_dory;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import java.util.HashMap;
import static com.example.chachacha_dory.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements MainActivityView {
    EditText mIdEdit, mPwEdit;
    String mId, mPw;
    Button mLoginBtn;
    TextView mSignUpText;
    HashMap<String, Object> mHashMap;
    Switch mSaveLoginSwitch;
//    SharedPreferences mSharedPref;
    boolean mSaveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHashMap = new HashMap<>();

        mSaveLogin = sSharedPreferences.getBoolean("isSaved", false);

        if(mSaveLogin){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        mSaveLoginSwitch = findViewById(R.id.saveLoginSwitch);
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

                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putBoolean("isSaved", mSaveLoginSwitch.isChecked());
                editor.commit();

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
        if(code==113){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else
            showCustomToast(text);
    }

    @Override
    public void validateSuccessMyPage(DefaultResponse.Result result) {

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("결과", message);
        showCustomToast(message);
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
