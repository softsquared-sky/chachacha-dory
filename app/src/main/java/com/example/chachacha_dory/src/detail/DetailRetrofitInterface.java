package com.example.chachacha_dory.src.detail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailRetrofitInterface {
    //      9. 음식점 상세조회
    @GET("/store/{storeNum}")
    Call<DetailResponse> getStoreDetail(
            @Path("storeNum") int storeNum
    );
}
