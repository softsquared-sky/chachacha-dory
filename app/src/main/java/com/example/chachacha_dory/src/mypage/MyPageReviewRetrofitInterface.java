package com.example.chachacha_dory.src.mypage;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyPageReviewRetrofitInterface {
    //      6. 손님 마이페이지 리뷰
    @GET("/user/{userid}/review")
    Call<MyPageReviewResponse> getMyReview(
            @Path("userid") String userid,
            @Query("page") int page,
            @Query("size") int size
    );

    //      20. 마이리뷰 삭제
    @DELETE("/user/{userId}/review/{reviewNum}")
    Call<DeleteMyPageReviewResponse> deleteMyReview(
            @Path("userId") String userId,
            @Path("reviewNum") int num
    );
}
