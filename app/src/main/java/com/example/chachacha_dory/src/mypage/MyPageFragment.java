package com.example.chachacha_dory.src.mypage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

import android.content.DialogInterface;
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
import com.example.chachacha_dory.src.bookmark.MyBarFragment;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.src.login.LoginActivity;

public class MyPageFragment extends BaseFragment implements MyPageActivityView {
    Button mReviewBtn, myBarBtn;
    ViewGroup mRootView;
    String mName, mWriting, mEmail, mPhone, mSignUp;
    TextView mNameView, mWritingView, mEmailView, mPhoneView, mSignUpView, mReplaceBtn, mLogOutBtn;
    MyPageReviewFragment myPageReviewFragment;

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

        TextView aboutCha = mRootView.findViewById(R.id.aboutCha);
        TextView notice = mRootView.findViewById(R.id.notice);
        TextView advertisement = mRootView.findViewById(R.id.advertisement);

        aboutCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("아직 준비중이애오");
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("아직 준비중이애오");
            }
        });

        advertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("아직 준비중이애오");
            }
        });

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
                MyPagePatchFragment myPagePatchFragment = new MyPagePatchFragment();
                ((MainActivity)getActivity()).replaceFragment(myPagePatchFragment);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                // 제목셋팅
                alertDialogBuilder.setTitle("로그아웃");

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("정말 로그아웃 하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("네",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                                        editor.putBoolean("isSaved", false);
                                        editor.commit();

                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                    }
                                })
                        .setNegativeButton("아니요",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });
    }

}
