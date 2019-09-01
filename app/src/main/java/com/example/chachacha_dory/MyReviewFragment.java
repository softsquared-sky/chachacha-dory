package com.example.chachacha_dory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyReviewFragment extends Fragment implements ReviewInterface{
    private ListView myReviewList;
    private MyReviewListAdapter mAdapter;
    ViewGroup mRootView;
    ImageView backBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ServiceReview serviceReview = new ServiceReview(MyReviewFragment.this);
        serviceReview.getMyReview();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup)inflater.inflate(R.layout.fragment_my_review, container, false);
        myReviewList = mRootView.findViewById(R.id.myReviewList);
//        mAdapter.addMyReview(R.drawable.start_chicken, "교촌치킨", "서울시 관악구", "맛있어요", 4);
        backBtn = mRootView.findViewById(R.id.backBtn2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPageFragment myPageFragment = new MyPageFragment();
                ((MainActivity)getActivity()).replaceFragment(myPageFragment);
            }
        });
        return mRootView;
    }

    @Override
    public void validateSuccess(String text, int code) {
        Log.d("결과", text);
    }

    @Override
    public void validateReview(ArrayList<ResponseReview.ReviewResult.Review> reviews) {
        mAdapter = new MyReviewListAdapter(reviews);
        myReviewList.setAdapter(mAdapter);
    }

    @Override
    public void validateFailure(String message) {
        Log.d("결과", message);
    }


}
