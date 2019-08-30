package com.example.chachacha_dory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyPageFragment extends Fragment implements MainActivityView{
    Button reviewBtn, myBarBtn;
    ViewGroup mRootView;
    boolean mPageSuccess;
//    DefaultResponse.Result mResult;
    String mName, mWriting, mEmail, mPhone, mSignUp;
    TextView mNameView, mWritingView, mEmailView, mPhoneView, mSignUpView, mReplaceBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MainService mainService = new MainService(MyPageFragment.this);
        mainService.getMyPage();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup)inflater.inflate(R.layout.fragment_mypage, container, false);

        reviewBtn = mRootView.findViewById(R.id.myReviewBtn);
        myBarBtn = mRootView.findViewById(R.id.myBarBtn);
        mNameView = mRootView.findViewById(R.id.myPageName);
        mWritingView = mRootView.findViewById(R.id.statusMessage);
        mEmailView = mRootView.findViewById(R.id.myPageEmail);
        mPhoneView = mRootView.findViewById(R.id.myPagePhone);
        mSignUpView = mRootView.findViewById(R.id.myPageJoinDate);
        mReplaceBtn = mRootView.findViewById(R.id.replaceBtn);

        return mRootView;
    }

    @Override
    public void validateSuccess(String text, int code) {
        if(code==113){
            mPageSuccess = true;
        }else
            mPageSuccess = false;
    }

    @Override
    public void validateSuccessMyPage(DefaultResponse.Result result) {
        Log.d("결과네임1", result.getName());
//        mResult = result;
        mName = result.getName();
        mWriting = result.getWriting();
        mEmail = result.getEmail();
        mPhone = result.getPhone();
        mSignUp = result.getSignuptime();

        mNameView.setText(mName);
        mWritingView.setText(mWriting);
        mEmailView.setText(mEmail);
        mPhoneView.setText(mPhone);
        mSignUpView.setText(mSignUp);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        Log.d("결과", message);
        mPageSuccess = false;
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void onResume() {
        super.onResume();

        mReplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("name", mName);
                intent.putExtra("writing", mWriting);
                intent.putExtra("email", mEmail);
                intent.putExtra("phone", mPhone);
                startActivity(intent);
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReviewActivity.class);
                startActivity(intent);
            }
        });

        myBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyBarActivity.class);
                startActivity(intent);
            }
        });
    }
}
