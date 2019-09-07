package com.example.chachacha_dory.src.mychachacha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;
import com.example.chachacha_dory.src.detail.DetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyChaFragment extends BaseFragment implements MyChaActivityView, DeleteMyChaActivityView {
    GridView mListView;
    MyChaListAdapter mAdapter;
    LinearLayout mNoMyCha;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mycha, container, false);

        mNoMyCha = rootView.findViewById(R.id.noMyCha);
        mListView = rootView.findViewById(R.id.mychaList);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("마이 차차차 삭제");
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyChaResponse.MyChaResult myChaResult = mAdapter.getItem(position);
                        tryDeleteMyCha(myChaResult.getChaNum());
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MyChaDetailActivity.class);
                intent.putExtra("chaNum", mAdapter.getItem(position).getChaNum());
                intent.putExtra("storeNum", mAdapter.getItem(position).getStoreNum());
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tryGetMyCha();
    }

    public void tryGetMyCha() {
        final MyChaService myChaService = new MyChaService(this);
        myChaService.getMyCha();
    }

    public void tryDeleteMyCha(int chaNum) {
        showProgressDialog();
        final DeleteMyChaService deleteMyChaService = new DeleteMyChaService(this);
        deleteMyChaService.deleteMyCha(chaNum);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<MyChaResponse.MyChaResult> stores) {
        hideProgressDialog();
        if (isSuccess) {
            mAdapter = new MyChaListAdapter(stores);
            mListView.setAdapter(mAdapter);
            if (mAdapter.getCount() == 0) {
                mNoMyCha.setVisibility(View.VISIBLE);
            }
            if(mAdapter.getCount()<=4){
                mListView.setNumColumns(2);
            }else if(mAdapter.getCount()>4){
                mListView.setNumColumns(3);
            }else if(mAdapter.getCount()>9){
                mListView.setNumColumns(4);
            }else{
                mListView.setNumColumns(8);
            }

        } else {
            showCustomToast(text);
        }
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess) {
        hideProgressDialog();
        if (isSuccess) {
            tryGetMyCha();
        } else {
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
