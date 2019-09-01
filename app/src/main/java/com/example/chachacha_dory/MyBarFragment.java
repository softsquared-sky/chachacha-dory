package com.example.chachacha_dory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyBarFragment extends Fragment implements StoreInstance{
    private ListView mListView;
    private BarListAdapter mAdapter;
    ViewGroup mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ServiceStore serviceStore = new ServiceStore(MyBarFragment.this);
        serviceStore.getBookMark();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup)inflater.inflate(R.layout.fragment_my_bar, container, false);
        mListView = mRootView.findViewById(R.id.mybarListView);
        ImageView backBtn = mRootView.findViewById(R.id.backBtn3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new MyPageFragment());
            }
        });
        return mRootView;
    }

    @Override
    public void validateSuccess(String text, int code) {

    }

    @Override
    public void validateStore(ArrayList<ResponseStore.StoreResult> stores) {
        mAdapter = new BarListAdapter(stores);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void validateFailure(String message) {

    }
}
