package com.example.chachacha_dory.src.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chachacha_dory.R;
import com.example.chachacha_dory.config.BaseFragment;
import com.example.chachacha_dory.src.detail.DetailActivity;
import com.example.chachacha_dory.src.mypage.MainActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchFragment extends BaseFragment implements TextWatcher, SearchFragmentView, TextView.OnEditorActionListener {
    EditText mSearchEdit;
    ListView mSearchList;
    RelativeLayout mNoSearch;
    ImageView mSearchBtn;
    SearchListAdapter mAdapter;
    ArrayList<SearchResponse.SearchResult> mArrayListSearch = new ArrayList<>();
    int mArrayListSearchSize = 0;
    String mStoreSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        mSearchEdit = rootView.findViewById(R.id.searchName);
        mSearchList = rootView.findViewById(R.id.searchList);
        mNoSearch = rootView.findViewById(R.id.noSearch);
        mSearchBtn = rootView.findViewById(R.id.searchBtn);

        LinearLayout searchLayout = rootView.findViewById(R.id.searchLayout);
        RelativeLayout searchView = rootView.findViewById(R.id.searchView);

        searchLayout.setOnClickListener(keyboardClick);
        searchView.setOnClickListener(keyboardClick);
        mSearchBtn.setOnClickListener(keyboardClick);
        mNoSearch.setOnClickListener(keyboardClick);
        mSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).hideKeyboard(mSearchEdit);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("storeNum", mAdapter.getItem(position).getStoreNum());
                startActivity(intent);
            }
        });

        mSearchEdit.setOnEditorActionListener(this);

        mSearchList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (!mSearchList.canScrollVertically(-1)) {
                    //최상단
//                    Log.v("알림", "home list 최상단. ImageView 띄우기");
                } else if (!mSearchList.canScrollVertically(1)) {
                    Log.v("결과", "home list 최하단. ImageView 없애기");
                    mArrayListSearchSize+=5;
                    trySearch(mArrayListSearchSize, mSearchEdit.getText().toString());
                    mStoreSearch = mSearchEdit.getText().toString();
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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSearchEdit.setText("");
        mSearchEdit.addTextChangedListener(this);
        mArrayListSearch = new ArrayList<>();
        mAdapter = new SearchListAdapter(mArrayListSearch);
        mSearchList.setAdapter(mAdapter);
        mArrayListSearchSize = 0;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mArrayListSearchSize = 0;
        mArrayListSearch.clear();
        if (s == null || s.length() == 0 || s.equals("")) {
            mSearchList.clearTextFilter();
            mNoSearch.setVisibility(View.VISIBLE);
        } else {
            mNoSearch.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        mArrayListSearchSize = 0;
        mArrayListSearch.clear();
        if (mSearchEdit.getText().length() == 0) {
            mSearchList.clearTextFilter();
        }
        trySearch(0, s.toString());
    }

    View.OnClickListener keyboardClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).hideKeyboard(mSearchEdit);
        }
    };

    private void trySearch(int size, String storeName) {
        showProgressDialog();
        final SearchService searchService = new SearchService(this);
        searchService.postSearch(size, storeName);
    }

    @Override
    public void validateSuccess(String text, boolean isSuccess, ArrayList<SearchResponse.SearchResult> arrayList) {
        hideProgressDialog();
        if (isSuccess) {
            mArrayListSearch.addAll(arrayList);
            mAdapter.notifyDataSetChanged();
        } else {
//            showCustomToast(text);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId() == R.id.searchName && actionId == EditorInfo.IME_ACTION_DONE) { // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
            ((MainActivity) getActivity()).hideKeyboard(mSearchEdit);
        }
        return false;
    }
}
