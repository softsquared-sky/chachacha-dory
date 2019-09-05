package com.example.chachacha_dory.src.mychachacha;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface DeleteMyChaRetrofitInterface {

    //      16. 마이차차차 삭제
    @DELETE("/user/{userid}/store/{chaNum}")
    Call<DeleteMyChaResponse> deleteMyCha(
            @Path("userid") String userid,
            @Path("chaNum") int chaNum
    );
}
