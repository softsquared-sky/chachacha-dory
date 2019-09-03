package com.example.chachacha_dory.src.mypage;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface MyPageRetrofitInterface {

    //      4. 마이페이지
    @GET("/user/{userid}")
    Call<MyPageResponse> getMyPage(@Path("userid") String userid);

    //      5. 마이페이지 수정
    @PATCH("/user/{userid}")
    Call<MyPagePatchResponse> patchMyPage(
            @Path("userid") String userid,
            @Body HashMap<String, Object> params
    );
}
