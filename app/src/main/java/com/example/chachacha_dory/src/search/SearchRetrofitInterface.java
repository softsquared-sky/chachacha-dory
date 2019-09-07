package com.example.chachacha_dory.src.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRetrofitInterface {
    @GET("/stores/search")
    Call<SearchResponse> postSearch(
            @Query("storeName") String storeName,
            @Query("page") int page,
            @Query("size") int size
    );
}
