package com.example.chachacha_dory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyBarFragment extends Fragment implements StoreInterface {
    private ListView mListView;
    private BarListAdapter mAdapter;
    ViewGroup mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final StoreService storeService = new StoreService(MyBarFragment.this);
        storeService.getBookMark();
    }

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

        return mRootView;
    }

    @Override
    public void validateSuccess(String text, int code, ArrayList<StoreResponse.StoreResult> stores) {
        if (code == 204) {
            mAdapter = new BarListAdapter(stores);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void validateFailure(String message) {

    }

}
