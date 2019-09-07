package com.example.chachacha_dory.src.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MapFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_map, container, false);

        return rootView;
    }
}
