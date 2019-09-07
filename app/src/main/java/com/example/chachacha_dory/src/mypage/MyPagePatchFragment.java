package com.example.chachacha_dory.src.mypage;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPagePatchFragment extends BaseFragment implements MyPageActivityView, MyPagePatchActivityView{
    TextView mNameView, mFinishBtn;
    EditText mWritingView, mEditEmail, mEditPhone;
    String mName, mWriting, mEmail, mPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_my_page_patch, container, false);

        mNameView = rootView.findViewById(R.id.profileName);
        mWritingView = rootView.findViewById(R.id.profileWriting);
        mEditEmail = rootView.findViewById(R.id.profileEmail);
        mEditPhone = rootView.findViewById(R.id.profilePhone);
        mFinishBtn = rootView.findViewById(R.id.finishBtn);
        LinearLayout profile = rootView.findViewById(R.id.profileLayout);
        LinearLayout scrollView = rootView.findViewById(R.id.profileScroll);
        scrollView.setOnClickListener(keyboardClickListener);
        profile.setOnClickListener(keyboardClickListener);
        mFinishBtn.setOnClickListener(keyboardClickListener);

        tryGetMyPage();

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPatchMyPage();
                ((MainActivity) getActivity()).hideKeyboard(mWritingView);
                ((MainActivity) getActivity()).hideKeyboard(mEditEmail);
                ((MainActivity) getActivity()).hideKeyboard(mEditPhone);
            }
        });

        ImageView backBtn = rootView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new MyPageFragment());
            }
        });

        mWritingView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    mWritingView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_ban, 0);
                }else {
                    mWritingView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
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
                s=s.toString().replaceAll("-", "");
                if (s.length() == 0 || !Pattern.matches("^01(?:[0-9]{8,10})$", s)) {
                    mEditPhone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_ban, 0);
                } else {
                    mEditPhone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
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
                if (s.length() == 0||!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    mEditEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_ban, 0);
                } else {
                    mEditEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK){
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    private void tryGetMyPage() {
        final MyPageService myPageService = new MyPageService(this);
        myPageService.getMyPage();
    }

    private void tryPatchMyPage() {
        showProgressDialog();

        mName = mNameView.getText().toString();
        mWriting = mWritingView.getText().toString();
        mEmail = mEditEmail.getText().toString();
        mPhone = mEditPhone.getText().toString();
        mPhone = mPhone.replaceAll("-", "");

        final MyPagePatchService myPageService = new MyPagePatchService(this);
        myPageService.patchMyPage(mName, mWriting, mEmail, mPhone);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        if (isSuccess) {
            ((MainActivity) getActivity()).replaceFragment(new MyPageFragment());
        } else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, MyPageResponse.MyPageResult myPageResult) {
        if (isSuccess) {
            String name = myPageResult.getName();
            String writing = myPageResult.getWriting();
            String email = myPageResult.getEmail();
            String phone = myPageResult.getPhone();

            mNameView.setText(name);
            mWritingView.setText(writing);
            mEditEmail.setText(email);
            mEditPhone.setText(phone);
        } else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        Log.d("메시지내용", message);
        showCustomToast(message);
    }

    View.OnClickListener keyboardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).hideKeyboard(mWritingView);
            ((MainActivity) getActivity()).hideKeyboard(mEditEmail);
            ((MainActivity) getActivity()).hideKeyboard(mEditPhone);
        }
    };


}
