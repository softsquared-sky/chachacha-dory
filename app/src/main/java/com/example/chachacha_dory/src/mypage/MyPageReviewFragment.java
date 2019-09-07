package com.example.chachacha_dory.src.mypage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    ImageView mBackBtn;
    LinearLayout mNoReviewLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup)inflater.inflate(R.layout.fragment_my_review, container, false);
        myReviewList = mRootView.findViewById(R.id.myReviewList);
        mBackBtn = mRootView.findViewById(R.id.backBtn2);
        mNoReviewLayout = mRootView.findViewById(R.id.noReview);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
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
            if(mAdapter.getCount()==0){
                mNoReviewLayout.setVisibility(View.VISIBLE);
            }
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
