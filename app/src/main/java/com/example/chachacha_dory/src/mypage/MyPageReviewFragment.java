package com.example.chachacha_dory.src.mypage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chachacha_dory.config.BaseFragment;
import com.example.chachacha_dory.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPageReviewFragment extends BaseFragment implements MyPageReviewActivityView {
    private ListView myReviewList;
    private MyPageReviewListAdapter mAdapter;
    ViewGroup mRootView;
    ImageView backBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup)inflater.inflate(R.layout.fragment_my_review, container, false);
        myReviewList = mRootView.findViewById(R.id.myReviewList);
        backBtn = mRootView.findViewById(R.id.backBtn2);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPageFragment myPageFragment = new MyPageFragment();
                ((MainActivity)getActivity()).replaceFragment(myPageFragment);
            }
        });

        tryGetMyPageReview();

        return mRootView;
    }

    public void tryGetMyPageReview(){
        showProgressDialog();
        final MyPageReviewService myPageReviewService = new MyPageReviewService(this);
        myPageReviewService.getMyReview();
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<MyPageReviewResponse.MyPageReviewResult> reviews) {
        hideProgressDialog();
        if(isSuccess) {
            mAdapter = new MyPageReviewListAdapter(reviews);
            myReviewList.setAdapter(mAdapter);
        }else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
        Log.d("결과", message);
    }

}
