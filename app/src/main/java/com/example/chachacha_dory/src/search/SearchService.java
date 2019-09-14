package com.example.chachacha_dory.src.search;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class SearchService {
    final SearchFragmentView mSearchFragmentView;

    SearchService(SearchFragmentView searchFragmentView){
        mSearchFragmentView = searchFragmentView;
    }

    void postSearch(int startPage, String storeName){
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.postSearch(storeName, startPage, 5).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                final SearchResponse searchResponse = response.body();
                if(searchResponse==null){
                    mSearchFragmentView.validateFailure("연결 못함");
                    return;
                }
                Log.d("결과", searchResponse.getMessage());
                mSearchFragmentView.validateSuccess(searchResponse.getMessage(), searchResponse.isSuccess(), searchResponse.getStores());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                mSearchFragmentView.validateFailure(t.toString()+" ");
                Log.d("결과 실패 이유", t.toString());
            }
        });
    }
}
