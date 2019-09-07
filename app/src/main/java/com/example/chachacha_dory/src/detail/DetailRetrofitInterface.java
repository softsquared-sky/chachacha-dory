package com.example.chachacha_dory.src.detail;

import com.example.chachacha_dory.src.mychachacha.MyChaDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailRetrofitInterface {

    //      9. 음식점 상세조회
    @GET("/store/{storeNum}")
    Call<DetailResponse> getStoreDetail(
            @Path("storeNum") int storeNum
    );

    //      17. 마이차차차 상세조회
    @GET("/user/{userId}/store/{chaNum}")
    Call<MyChaDetailResponse> getDetailMyCha(
            @Path("userId") String userId,
            @Path("chaNum") int chaNum
    );
}
