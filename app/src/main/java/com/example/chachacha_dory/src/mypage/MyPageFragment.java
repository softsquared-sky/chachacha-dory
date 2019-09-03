package com.example.chachacha_dory.src.mypage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chachacha_dory.config.BaseFragment;
import com.example.chachacha_dory.src.chachacha.MyBarFragment;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.src.login.LoginActivity;

public class MyPageFragment extends BaseFragment implements MyPageActivityView {
    Button mReviewBtn, myBarBtn;
    ViewGroup mRootView;
    String mName, mWriting, mEmail, mPhone, mSignUp;
    TextView mNameView, mWritingView, mEmailView, mPhoneView, mSignUpView, mReplaceBtn, mLogOutBtn;
    MyPageReviewFragment myPageReviewFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup)inflater.inflate(R.layout.fragment_mypage, container, false);

        mReviewBtn = mRootView.findViewById(R.id.myReviewBtn);
        myBarBtn = mRootView.findViewById(R.id.myBarBtn);
        mNameView = mRootView.findViewById(R.id.myPageName);
        mWritingView = mRootView.findViewById(R.id.statusMessage);
        mEmailView = mRootView.findViewById(R.id.myPageEmail);
        mPhoneView = mRootView.findViewById(R.id.myPagePhone);
        mSignUpView = mRootView.findViewById(R.id.myPageJoinDate);
        mReplaceBtn = mRootView.findViewById(R.id.replaceBtn);
        mLogOutBtn = mRootView.findViewById(R.id.logOutBtn);

        myPageReviewFragment = new MyPageReviewFragment();
        tryGetMyPage();

        return mRootView;
    }

    private void tryGetMyPage() {
//        showProgressDialog();
        final MyPageService myPageService = new MyPageService(this);
        myPageService.getMyPage();
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, MyPageResponse.MyPageResult myPageResult) {
        hideProgressDialog();
        if(isSuccess) {
            mName = myPageResult.getName();
            mWriting = myPageResult.getWriting();
            mEmail = myPageResult.getEmail();
            mPhone = myPageResult.getPhone();
            mSignUp = myPageResult.getSignuptime();

            mNameView.setText(mName);
            mWritingView.setText(mWriting);
            mEmailView.setText(mEmail);
            mPhoneView.setText(mPhone);
            mSignUpView.setText(mSignUp);
        }else{
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d("결과", message);
        showCustomToast(message);
    }

    @Override
    public void onResume() {
        super.onResume();

        mReplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPagePatchActivity.class);
                intent.putExtra("name", mName);
                intent.putExtra("writing", mWriting);
                intent.putExtra("email", mEmail);
                intent.putExtra("phone", mPhone);
                startActivity(intent);
            }
        });

        mReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(myPageReviewFragment);
            }
        });

        myBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBarFragment myBarFragment = new MyBarFragment();
                ((MainActivity)getActivity()).replaceFragment(myBarFragment);
            }
        });

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putBoolean("isSaved", false);
                editor.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}
