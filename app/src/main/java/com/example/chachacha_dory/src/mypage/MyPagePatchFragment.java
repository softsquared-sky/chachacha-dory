package com.example.chachacha_dory.src.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPagePatchFragment extends BaseFragment implements MyPageActivityView, MyPagePatchActivityView {
    TextView mNameView, mFinishBtn;
    EditText mWritingView, mEmailView, mPhoneView;
    String mName, mWriting, mEmail, mPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_profile, container, false);

        mNameView = rootView.findViewById(R.id.profileName);
        mWritingView = rootView.findViewById(R.id.profileWriting);
        mEmailView = rootView.findViewById(R.id.profileEmail);
        mPhoneView = rootView.findViewById(R.id.profilePhone);
        mFinishBtn = rootView.findViewById(R.id.finishBtn);

        tryGetMyPage();

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPatchMyPage();
            }
        });
        ImageView backBtn = rootView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new MyPageFragment());
            }
        });

        return rootView;
    }

    private void tryGetMyPage(){
        final MyPageService myPageService = new MyPageService(this);
        myPageService.getMyPage();
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
            ((MainActivity)getActivity()).replaceFragment(new MyPageFragment());
        }else{
            showCustomToast(text);
        }
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, MyPageResponse.MyPageResult myPageResult) {
        if(isSuccess){
            String name = myPageResult.getName();
            String writing = myPageResult.getWriting();
            String  email = myPageResult.getEmail();
            String phone = myPageResult.getPhone();

            mNameView.setText(name);
            mWritingView.setText(writing);
            mEmailView.setText(email);
            mPhoneView.setText(phone);
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
