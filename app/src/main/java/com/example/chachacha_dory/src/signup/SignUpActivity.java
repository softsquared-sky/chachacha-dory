package com.example.chachacha_dory.src.signup;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.R;

import java.util.regex.Pattern;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {
    EditText mEditId, mEditPw, mEditPw2, mEditName, mEditPhone, mEditEmail;
    Button mGender0Btn, mGender1Btn, mAge0Btn, mAge1Btn, mAge2Btn, mAge3Btn;
    int mSelectedAge, mSelectedGender;
    ImageView mBackBtn;
    TextView mValidationId, mValidationPw, mValidationPw2, mValidationName, mValidationPhone, mValidationEmail;
    String mPwString, mPw2String;

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
        mValidationId = findViewById(R.id.validationId);
        mValidationPw = findViewById(R.id.validationPw);
        mValidationPw2 = findViewById(R.id.validationPw2);
        mValidationName = findViewById(R.id.validationName);
        mValidationPhone = findViewById(R.id.validationPhone);
        mValidationEmail = findViewById(R.id.validationEmail);
        LinearLayout signUpLayout = findViewById(R.id.signUpLayout);
        LinearLayout signUpView = findViewById(R.id.signUpView);

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

        mEditEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mValidationEmail.setText(R.string.validation);
                    mValidationEmail.setTextColor(Color.RED);
                }else if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    mValidationEmail.setText(R.string.validationEmail);
                    mValidationEmail.setTextColor(Color.RED);
                } else {
                    mValidationEmail.setText(R.string.validationOk);
                    mValidationEmail.setTextColor(Color.rgb(47, 157, 39));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button nextBtn = findViewById(R.id.signupNextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(mEditEmail);
                hideKeyboard(mEditId);
                hideKeyboard(mEditName);
                hideKeyboard(mEditPhone);
                hideKeyboard(mEditPw);
                hideKeyboard(mEditPw2);
                tryPostSignUp();
            }
        });

        signUpLayout.setOnClickListener(keyboardClick);
        signUpView.setOnClickListener(keyboardClick);

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
        String email = mEditEmail.getText().toString();

        final SignUpService signUpService = new SignUpService(this);

        if (!pw.equals(pw2)) {
            showCustomToast("비밀번호가 일치하지 않습니다.");
            hideProgressDialog();
        } else {
            signUpService.postSignUp(id, pw, name, mSelectedAge, mSelectedGender, email, phone);
            Log.d("나이결과", String.valueOf(mSelectedAge));
            Log.d("성별결과", String.valueOf(mSelectedGender));
        }
    }

    public void clickAge(View v) {
        hideKeyboard(mEditEmail);
        hideKeyboard(mEditId);
        hideKeyboard(mEditName);
        hideKeyboard(mEditPhone);
        hideKeyboard(mEditPw);
        hideKeyboard(mEditPw2);
        clearAgeBtn();
        switch (v.getId()) {
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

    public void clickGender(View v) {
        hideKeyboard(mEditEmail);
        hideKeyboard(mEditId);
        hideKeyboard(mEditName);
        hideKeyboard(mEditPhone);
        hideKeyboard(mEditPw);
        hideKeyboard(mEditPw2);
        switch (v.getId()) {
            case R.id.signUpGender0:
                mGender1Btn.setBackgroundResource(R.drawable.round_border);
                mGender1Btn.setTextColor(Color.rgb(74, 74, 74));
                mGender0Btn.setBackgroundResource(R.drawable.roundback_border);
                mGender0Btn.setTextColor(Color.WHITE);
                mSelectedGender = 0;
                break;
            case R.id.signUpGender1:
                mGender0Btn.setBackgroundResource(R.drawable.round_border);
                mGender0Btn.setTextColor(Color.rgb(74, 74, 74));
                mGender1Btn.setBackgroundResource(R.drawable.roundback_border);
                mGender1Btn.setTextColor(Color.WHITE);
                mSelectedGender = 1;
                break;
        }
    }

    public void clearAgeBtn() {
        mAge0Btn.setBackgroundResource(R.drawable.round_border);
        mAge1Btn.setBackgroundResource(R.drawable.round_border);
        mAge2Btn.setBackgroundResource(R.drawable.round_border);
        mAge3Btn.setBackgroundResource(R.drawable.round_border);
        mAge0Btn.setTextColor(Color.rgb(74, 74, 74));
        mAge1Btn.setTextColor(Color.rgb(74, 74, 74));
        mAge2Btn.setTextColor(Color.rgb(74, 74, 74));
        mAge3Btn.setTextColor(Color.rgb(74, 74, 74));
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        if (isSuccess) {
            Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
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
            hideKeyboard(mEditEmail);
            hideKeyboard(mEditId);
            hideKeyboard(mEditName);
            hideKeyboard(mEditPhone);
            hideKeyboard(mEditPw);
            hideKeyboard(mEditPw2);
        }
    };
}
