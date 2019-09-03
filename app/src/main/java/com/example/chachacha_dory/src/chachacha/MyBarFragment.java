package com.example.chachacha_dory.src.chachacha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chachacha_dory.src.mypage.MainActivity;
import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;
import com.example.chachacha_dory.src.mypage.MyPageFragment;

import java.util.ArrayList;

public class MyBarFragment extends BaseFragment implements MyBarFragmentView {
    private ListView mListView;
    private MyBarListAdapter mAdapter;
    ViewGroup mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_bar, container, false);
        mListView = mRootView.findViewById(R.id.mybarListView);
        ImageView backBtn = mRootView.findViewById(R.id.backBtn3);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new MyPageFragment());
            }
        });

        tryGetBookMark();

        return mRootView;
    }

    public void tryGetBookMark(){
        final MyBarService myChaService = new MyBarService(this);
        myChaService.getBookMark();
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<MyBarResponse.MyBarResult> stores) {
        hideProgressDialog();
        if(isSuccess){
            mAdapter = new MyBarListAdapter(stores);
            mListView.setAdapter(mAdapter);
        }else{
            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

}
