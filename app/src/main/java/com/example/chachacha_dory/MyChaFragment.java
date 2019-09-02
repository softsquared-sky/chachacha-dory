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
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyChaFragment extends Fragment implements StoreInterface {
    GridView mListView;
    MyChaListAdapter mAdapter;
    ArrayList<StoreResponse.StoreResult> mMyChaList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoreService storeService = new StoreService(this);
        storeService.getMyCha();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mycha, container, false);

        mListView = rootView.findViewById(R.id.mychaList);

//        mAdapter.addMyCha(R.drawable.mycha1, "민국이네 외국포차");
//        mAdapter.addMyCha(R.drawable.mycha2, "와인바");
//        mAdapter.addMyCha(R.drawable.mycha3, "깩");
//        mAdapter.addMyCha(R.drawable.mycha4, "꺩꽁");

        return rootView;
    }

    @Override
    public void validateSuccess(String text, int code, ArrayList<StoreResponse.StoreResult> stores) {
        if(code==224) {
            mMyChaList = stores;
            mAdapter = new MyChaListAdapter(mMyChaList);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void validateFailure(String message) {
        Log.d("결과", message);
    }
}
