package com.example.chachacha_dory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MainRetrofitInterface {
    //    @GET("/test")
//    String URL = "http://106.10.50.207";

    @GET("/jwt")
    Call<MainResponse> getTest();

//    @GET("/test/{number}")
//    Call<MainResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<MainResponse> postTest(@Body RequestBody params);

//    @FormUrlEncoded
//    @HTTP(method = "GET", path = "/user/{userid}", hasBody = false)
//    Call<MainResponse> getLogin(
//            @Field("userid") String userid,
//            @Field("userpw") String userpw
//    );

    //    1. 회원가입
    @POST("/guest")
    Call<MainResponse> postSignUp(
            @Body HashMap<String, Object> params
    );

    //    3. 로그인
    @POST("/token")
    Call<MainResponse> getLogin(
            @Body HashMap<String, Object> params
    );

    //      4. 마이페이지
    @GET("/user/{userid}")
    Call<MainResponse> getMyPage(@Path("userid") String userid);

    //      5. 마이페이지 수정
    @PATCH("/user/{userid}")
    Call<MainResponse> patchMyPage(
            @Path("userid") String userid,
            @Body HashMap<String, Object> params
    );

    //      6. 손님 마이페이지 리뷰
    @GET("/user/{userid}/review")
    Call<MyReviewResponse> getMyReview(
            @Path("userid") String userid
    );

    //      7. 손님 마이페이지 즐겨찾기
    @GET("/user/{userid}/bookmark")
    Call<StoreResponse> getBookMark(
            @Path("userid") String userid
    );

    //      8. 손님 차차차 start


    //      9. 음식점 상세조회
    @POST("/store/{storenum}")
    Call<StoreResponse> getStoreDetail(
            @Path("storenum") int storenum
    );

    //      10. 음식점 리뷰 조회
    @GET("/store/{storenum}/review")
    Call<ReviewResponse> getStoreReview(
            @Path("storenum") int storenum
    );

    //      11. 음식점 메뉴 조회
    @GET("/store/{storenum}/menu")
    Call<MenuResponse> getStoreMenu(
            @Path("storenum") int storenum
    );

    //      12. 음식점 검색

    //      13. 마이차차차
    @POST("/user/{userid}/store")
    Call<MainResponse> postMyCha(
            @Path("userid") String userid,
            @Body HashMap<String, Object> hashMap
    );

    //      14.

    //      15.

    //      16. 마이차차차 조회
    @GET("/user/{userid}/store")
    Call<StoreResponse> getMyCha(
            @Path("userid") String userid
    );
}

