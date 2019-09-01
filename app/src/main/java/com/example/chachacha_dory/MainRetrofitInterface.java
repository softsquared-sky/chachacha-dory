package com.example.chachacha_dory;

import com.example.chachacha_dory.DefaultResponse;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    //    @GET("/test")
//    String URL = "http://106.10.50.207";

    @GET("/jwt")
    Call<DefaultResponse> getTest();

//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<DefaultResponse> postTest(@Body RequestBody params);

//    @FormUrlEncoded
//    @HTTP(method = "GET", path = "/user/{userid}", hasBody = false)
//    Call<DefaultResponse> getLogin(
//            @Field("userid") String userid,
//            @Field("userpw") String userpw
//    );

    //    1. 회원가입
    @POST("/guest")
    Call<DefaultResponse> postSignUp(
            @Body HashMap<String, Object> params
    );

    //    3. 로그인
    @POST("/token")
    Call<DefaultResponse> getLogin(
            @Body HashMap<String, Object> params
    );

    //      4. 마이페이지
    @GET("/user/{userid}")
    Call<DefaultResponse> getMyPage(@Path("userid") String userid);

    //      5. 마이페이지 수정
    @PATCH("/user/{userid}")
    Call<DefaultResponse> patchMyPage(
            @Path("userid") String userid,
            @Body HashMap<String, Object> params
    );

    //      6. 손님 마이페이지 리뷰
    @GET("/user/{userid}/reView")
    Call<ResponseMyReview> getMyReview(
            @Path("userid") String userid
    );

    //      7. 손님 마이페이지 즐겨찾기
    @GET("/user/{userid}/bookMark")
    Call<ResponseStore> getBookMark(
            @Path("userid") String userid
    );

    //      8. 손님 차차차 start


    //      9. 음식점 상세조회
    @POST("/store/{storenum}")
    Call<ResponseStore> getStoreDetail(
            @Path("storenum") int storenum
    );

    //      10. 음식점 리뷰 조회
    @GET("/store/{storenum}/reView")
    Call<ResponseReview> getStoreReview(
            @Path("storenum") int storenum
    );

    //      11. 음식점 메뉴 조회
    @GET("/store/{storenum}/meNu")
    Call<DefaultResponse> getStoreMenu(
            @Path("storenum") int storenum
    );
}

