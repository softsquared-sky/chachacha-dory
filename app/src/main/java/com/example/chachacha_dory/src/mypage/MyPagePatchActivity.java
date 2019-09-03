package com.example.chachacha_dory.src.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chachacha_dory.config.BaseActivity;
import com.example.chachacha_dory.R;

public class MyPagePatchActivity extends BaseActivity implements MyPagePatchActivityView {
    TextView mNameView, mFinishBtn;
    EditText mWritingView, mEmailView, mPhoneView;
    String mName, mWriting, mEmail, mPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mNameView = findViewById(R.id.profileName);
        mWritingView = findViewById(R.id.profileWriting);
        mEmailView = findViewById(R.id.profileEmail);
        mPhoneView = findViewById(R.id.profilePhone);
        mFinishBtn = findViewById(R.id.finishBtn);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String writing = intent.getStringExtra("writing");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");

        mNameView.setText(name);
        mWritingView.setText(writing);
        mEmailView.setText(email);
        mPhoneView.setText(phone);

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPatchMyPage();
            }
        });
    }

    private void tryPatchMyPage() {
        showProgressDialog();

        mName = mNameView.getText().toString();
        mWriting = mWritingView.getText().toString();
        mEmail = mEmailView.getText().toString();
        mPhone = mPhoneView.getText().toString();
        mPhone = mPhone.replaceAll("-", "");

        final MyPagePatchService myPageService = new MyPagePatchService(this);
        myPageService.patchMyPage(mName, mWriting, mEmail, mPhone);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        if(isSuccess){
            Intent intent1 = new Intent(MyPagePatchActivity.this, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
        }else{
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        Log.d("메시지내용", message);
        showCustomToast(message);
    }
}
