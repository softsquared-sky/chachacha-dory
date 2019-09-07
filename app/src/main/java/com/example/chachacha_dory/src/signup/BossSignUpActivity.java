package com.example.chachacha_dory.src.signup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseActivity;

import java.util.regex.Pattern;

public class BossSignUpActivity extends BaseActivity implements SignUpActivityView, TextView.OnEditorActionListener {
    EditText mEditId, mEditPw, mEditPw2, mEditName, mEditPhone;
    ImageView mBackBtn;
    TextView mValidationId, mValidationPw, mValidationPw2, mValidationName, mValidationPhone;
    String mPwString, mPw2String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_sign_up);

        mEditId = findViewById(R.id.signUpBossId);
        mEditPw = findViewById(R.id.signUpBossPw);
        mEditPw2 = findViewById(R.id.signUpBossPw2);
        mEditName = findViewById(R.id.signUpBossName);
        mEditPhone = findViewById(R.id.signUpBossPhone);
        mBackBtn = findViewById(R.id.signUpBossBackBtn);
        mValidationId = findViewById(R.id.validationBossId);
        mValidationPw = findViewById(R.id.validationBossPw);
        mValidationPw2 = findViewById(R.id.validationBossPw2);
        mValidationName = findViewById(R.id.validationBossName);
        mValidationPhone = findViewById(R.id.validationBossPhone);
        LinearLayout signUpLayout = findViewById(R.id.signUpBossLayout);
        LinearLayout signUpView = findViewById(R.id.signUpBossView);
        RelativeLayout signUpRelative = findViewById(R.id.signUpBossRelative);

        mEditId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mValidationId.setText(R.string.validation);
                    mValidationId.setTextColor(Color.RED);
                }else if (!Pattern.matches("^[a-zA-Z0-9]{4,10}$", s)) {
                    mValidationId.setText(R.string.validationId);
                    mValidationId.setTextColor(Color.RED);
                } else {
                    mValidationId.setText(R.string.validationOk);
                    mValidationId.setTextColor(Color.rgb(47, 157, 39));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mValidationPw.setText(R.string.validation);
                    mValidationPw.setTextColor(Color.RED);
                } else if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}$", s)) {
                    mValidationPw.setText(R.string.validationPw);
                    mValidationPw.setTextColor(Color.RED);
                } else {
                    mValidationPw.setText(R.string.validationOk);
                    mValidationPw.setTextColor(Color.rgb(47, 157, 39));
                }
                if(!s.toString().equals(mPw2String)){
                    mValidationPw2.setText(R.string.validationPw2);
                    mValidationPw2.setTextColor(Color.RED);
                }else{
                    mValidationPw2.setText(R.string.validationOk);
                    mValidationPw2.setTextColor(Color.rgb(47, 157, 39));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPwString = mEditPw.getText().toString();
            }
        });

        mEditPw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mValidationPw2.setText(R.string.validation);
                    mValidationPw2.setTextColor(Color.RED);
                } else if (s.toString().equals(mPwString)) {
                    mValidationPw2.setText(R.string.validationOk);
                    mValidationPw2.setTextColor(Color.rgb(47, 157, 39));
                } else {
                    mValidationPw2.setText(R.string.validationPw2);
                    mValidationPw2.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPw2String = s.toString();
            }
        });

        mEditName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mValidationName.setText(R.string.validation);
                    mValidationName.setTextColor(Color.RED);
                }else if (!Pattern.matches("^[가-힣]*$", s)) {
                    mValidationName.setText(R.string.validationName);
                    mValidationName.setTextColor(Color.RED);
                } else {
                    mValidationName.setText(R.string.validationOk);
                    mValidationName.setTextColor(Color.rgb(47, 157, 39));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mValidationPhone.setText(R.string.validation);
                    mValidationPhone.setTextColor(Color.RED);
                }else if (!Pattern.matches("^01(?:[0-9]{8,10})$", s)) {
                    mValidationPhone.setText(R.string.validationPhone);
                    mValidationPhone.setTextColor(Color.RED);
                } else {
                    mValidationPhone.setText(R.string.validationOk);
                    mValidationPhone.setTextColor(Color.rgb(47, 157, 39));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button nextBtn = findViewById(R.id.signupBossNextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(mEditId);
                hideKeyboard(mEditName);
                hideKeyboard(mEditPhone);
                hideKeyboard(mEditPw);
                hideKeyboard(mEditPw2);
                tryPostSignUp();
            }
        });

        mEditPhone.setOnEditorActionListener(this);
        signUpLayout.setOnClickListener(keyboardClick);
        signUpView.setOnClickListener(keyboardClick);
        signUpRelative.setOnClickListener(keyboardClick);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void tryPostSignUp() {
        showProgressDialog();

        String id = mEditId.getText().toString();
        String pw = mEditPw.getText().toString();
        String pw2 = mEditPw2.getText().toString();
        String name = mEditName.getText().toString();
        String phone = mEditPhone.getText().toString();

        final SignUpService signUpService = new SignUpService(this);

        if (!pw.equals(pw2)) {
            showCustomToast("비밀번호가 일치하지 않습니다.");
            hideProgressDialog();
        } else {
            signUpService.postBossSignUp(id, pw, name, phone);
        }
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        if (isSuccess) {
            Intent intent = new Intent(BossSignUpActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("메시지내용", message);
        showCustomToast(message);
    }

    View.OnClickListener keyboardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard(mEditId);
            hideKeyboard(mEditName);
            hideKeyboard(mEditPhone);
            hideKeyboard(mEditPw);
            hideKeyboard(mEditPw2);
        }
    };

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(v.getId()==R.id.signUpBossPhone && actionId== EditorInfo.IME_ACTION_DONE){ // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
            hideKeyboard(mEditPhone);
        }

        return false;
    }
}
