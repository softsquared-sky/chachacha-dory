package com.example.chachacha_dory.src.login;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.src.signup.BeforeSignUpActivity;
import com.example.chachacha_dory.src.mypage.MainActivity;
import com.example.chachacha_dory.R;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    EditText mIdEdit, mPwEdit;
    String mId, mPw;
    Button mLoginBtn;
    TextView mSignUpText;
    Switch mSaveLoginSwitch;
    boolean mSaveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSaveLogin = sSharedPreferences.getBoolean("isSaved", false);

        if (mSaveLogin) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        RelativeLayout loginLayout = findViewById(R.id.loginLayout);
        LinearLayout loginView = findViewById(R.id.loginView);
        mSaveLoginSwitch = findViewById(R.id.saveLoginSwitch);
        mIdEdit = findViewById(R.id.idEditText);
        mPwEdit = findViewById(R.id.pwEditText);
        mLoginBtn = findViewById(R.id.loginBtn);
        mSignUpText = findViewById(R.id.signupTextview);

        loginLayout.setOnClickListener(keyboardClick);
        loginView.setOnClickListener(keyboardClick);
        mLoginBtn.setOnClickListener(keyboardClick);
        mSaveLoginSwitch.setOnClickListener(keyboardClick);
        mSignUpText.setOnClickListener(keyboardClick);

        mSignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, BeforeSignUpActivity.class);
                startActivity(intent);
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetLogin();
            }
        });

    }

    private void tryGetLogin() {
        showProgressDialog();

        mId = mIdEdit.getText().toString();
        mPw = mPwEdit.getText().toString();

        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putBoolean("isSaved", mSaveLoginSwitch.isChecked());
        editor.apply();

        final LoginService loginService = new LoginService(this);
        loginService.getLogin(mId, mPw);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, String jwt) {
        hideProgressDialog();
        if (isSuccess) {
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, jwt);
            editor.putString(USER_ID, mId);
            editor.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("결과", message);
        showCustomToast(message);
    }

    View.OnClickListener keyboardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard(mIdEdit);
            hideKeyboard(mPwEdit);
        }
    };
}
