package com.example.chachacha_dory.src.mychachacha;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyChaRetrofitInterface {

    //      15. 마이차차차 조회
    @GET("/user/{userid}/store")
    Call<MyChaResponse> getMyCha(
            @Path("userid") String userid
    );

}
