package com.example.chachacha_dory.src.mychachacha;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;
import com.example.chachacha_dory.src.detail.DetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyChaFragment extends BaseFragment implements MyChaActivityView {
    GridView mListView;
    MyChaListAdapter mAdapter;
    ArrayList<MyChaResponse.MyChaResult> mMyChaList;
    LinearLayout mNoMyCha;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mycha, container, false);

        mNoMyCha = rootView.findViewById(R.id.noMyCha);
        mListView = rootView.findViewById(R.id.mychaList);
        mMyChaList = new ArrayList<>();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        tryGetMyCha();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void tryGetMyCha(){
        final MyChaService myChaService = new MyChaService(this);
        myChaService.getMyCha();
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<MyChaResponse.MyChaResult> stores) {
        hideProgressDialog();
        if(isSuccess){
            mMyChaList = stores;
            mAdapter = new MyChaListAdapter(mMyChaList);
            mListView.setAdapter(mAdapter);
            if(mAdapter.getCount()==0){
                mNoMyCha.setVisibility(View.VISIBLE);
            }
        }else{
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
