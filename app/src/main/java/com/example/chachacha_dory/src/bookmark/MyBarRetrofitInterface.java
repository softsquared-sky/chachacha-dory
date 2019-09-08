package com.example.chachacha_dory.src.bookmark;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyBarRetrofitInterface {
    //      7. 손님 마이페이지 즐겨찾기
    @GET("/user/{userid}/bookmark")
    Call<MyBarResponse> getBookMark(
            @Path("userid") String userid
    );

    //      18. 북마크 저장, 삭제
    @POST("/store/{storeNum}/bookmark")
    Call<SaveMyBarResponse> saveBookmark(
            @Path("storeNum") int storeNum
    );
}
