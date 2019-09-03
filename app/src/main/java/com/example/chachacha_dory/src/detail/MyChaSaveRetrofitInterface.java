package com.example.chachacha_dory.src.detail;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyChaSaveRetrofitInterface {

    //      13. 마이차차차
    @POST("/user/{userid}/store")
    Call<MyChaSaveResponse> postMyCha(
            @Path("userid") String userid,
            @Body HashMap<String, Object> hashMap
            );

}

