package com.example.chachacha_dory.src.mypage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPageReviewFragment extends BaseFragment implements MyPageReviewActivityView, DeleteMyPageReviewActivityView {
    private ListView myReviewList;
    private MyPageReviewListAdapter mAdapter;
    ViewGroup mRootView;
    ImageView mBackBtn;
    LinearLayout mNoReviewLayout;
    ArrayList<MyPageReviewResponse.MyPageReviewResult> mArrayList;
    int mArrayListSize;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_review, container, false);
        myReviewList = mRootView.findViewById(R.id.myReviewList);
        mBackBtn = mRootView.findViewById(R.id.backBtn2);
        mNoReviewLayout = mRootView.findViewById(R.id.noReview);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPageFragment myPageFragment = new MyPageFragment();
                ((MainActivity) getActivity()).replaceFragment(myPageFragment);
            }
        });

        myReviewList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("마이 리뷰 삭제");
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyPageReviewResponse.MyPageReviewResult myReview = mAdapter.getItem(position);
                        tryDeleteMyReview(myReview.getReviewNum());
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            }
        });

        myReviewList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (!myReviewList.canScrollVertically(-1)) {
                    //최상단
//                    Log.v("알림", "home list 최상단. ImageView 띄우기");
                } else if (!myReviewList.canScrollVertically(1)) {
                    Log.v("결과", "home list 최하단. ImageView 없애기");
                    mArrayListSize += 10;
                    tryGetMyPageReview(mArrayListSize);
                } else {
                    //idle
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int position = firstVisibleItem + visibleItemCount;
                int limit = totalItemCount;

                // Check if bottom has been reached
                if (position >= limit && totalItemCount > 0) {

                }
            }

        });

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mArrayList = new ArrayList<>();
        mAdapter = new MyPageReviewListAdapter(mArrayList);
        myReviewList.setAdapter(mAdapter);

        mArrayListSize = 0;
        tryGetMyPageReview(0);
    }

    public void tryGetMyPageReview(int size) {
        showProgressDialog();
        final MyPageReviewService myPageReviewService = new MyPageReviewService(this);
        myPageReviewService.getMyReview(size);
    }

    private void tryDeleteMyReview(int reviewNum) {
        showProgressDialog();
        final DeleteMyPageReviewService deleteMyPageReviewService = new DeleteMyPageReviewService(this);
        deleteMyPageReviewService.deleteMyReview(reviewNum);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<MyPageReviewResponse.MyPageReviewResult> reviews) {
        hideProgressDialog();
        if (isSuccess) {
            mArrayList.addAll(reviews);
            mAdapter.notifyDataSetChanged();
            if (mAdapter.getCount() == 0) {
                mNoReviewLayout.setVisibility(View.VISIBLE);
            }
        } else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateSuccessDeleteReview(String text, boolean isSuccess) {
        hideProgressDialog();
        showCustomToast(text);
        if (isSuccess) {
            tryGetMyPageReview(mArrayListSize);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
        Log.d("결과", message);
    }

}
