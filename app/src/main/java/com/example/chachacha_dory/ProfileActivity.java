package com.example.chachacha_dory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class ProfileActivity extends BaseActivity implements MainInterface {
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
                mName = mNameView.getText().toString();
                mWriting = mWritingView.getText().toString();
                mEmail = mEmailView.getText().toString();
                mPhone = mPhoneView.getText().toString();

                mPhone = mPhone.replaceAll("-", "");
                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("name", mName);
                hashMap.put("writing", mWriting);
                hashMap.put("phone", mPhone);
                hashMap.put("email", mEmail);

                final MainService mainService = new MainService(ProfileActivity.this);
                mainService.patchMyPage(hashMap);

                Intent intent1 = new Intent(ProfileActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void validateSuccess(String text, int code) {

    }

    @Override
    public void validateSuccessMyPage(MainResponse.Result result) {

    }

    @Override
    public void validateFailure(String message) {
        Log.d("메시지내용", message);
    }
}
