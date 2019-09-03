package com.example.chachacha_dory.src.chachacha;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyBarRetrofitInterface {
    //      7. 손님 마이페이지 즐겨찾기
    @GET("/user/{userid}/bookmark")
    Call<MyBarResponse> getBookMark(
            @Path("userid") String userid
    );
}
