package com.example.chachacha_dory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

public class MyChaFragment extends Fragment {
    GridView mListView;
    MyChaListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mycha, container, false);

        mListView = rootView.findViewById(R.id.mychaList);
        mAdapter = new MyChaListAdapter();
        mListView.setAdapter(mAdapter);

        mAdapter.addMyCha(R.drawable.mycha1, "민국이네 외국포차");
        mAdapter.addMyCha(R.drawable.mycha2, "와인바");
        mAdapter.addMyCha(R.drawable.mycha3, "깩");
        mAdapter.addMyCha(R.drawable.mycha4, "꺩꽁");

        return rootView;
    }

}
