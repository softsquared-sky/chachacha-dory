package com.example.chachacha_dory.src.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.chachacha_dory.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment implements TextWatcher{
    EditText mSearchEdit;
    ListView mSearchList;
    ArrayAdapter<String> mArrayAdapter;
    LinearLayout mNoSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_search, container, false);
        mSearchEdit = rootView.findViewById(R.id.searchName);
        mSearchList = rootView.findViewById(R.id.searchList);
        mNoSearch = rootView.findViewById(R.id.noSearch);

        mArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mSearchList.setAdapter(mArrayAdapter);
        mSearchEdit.addTextChangedListener(this);
        mSearchList.setTextFilterEnabled(true);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mSearchList.setFilterText(mSearchEdit.getText().toString());
        if(s==null||s.length()==0||s.equals("")){
            mNoSearch.setVisibility(View.VISIBLE);
        }else{
            mNoSearch.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(mSearchEdit.getText().length()==0) {
            mSearchList.clearTextFilter();
        }
    }
}
