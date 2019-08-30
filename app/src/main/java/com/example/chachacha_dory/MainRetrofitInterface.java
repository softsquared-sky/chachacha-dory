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

//      마이페이지
    @GET("/user/{userid}")
    Call<DefaultResponse> getMyPage(@Path("userid") String userid);

    //마이페이지 수정
    @PATCH("/user/{userid}")
    Call<DefaultResponse> patchMyPage(
            @Path("userid") String userid,
            @Body HashMap<String, Object> params
    );

//    로그인
    @POST("/token")
    Call<DefaultResponse> getLogin(
            @Body HashMap<String, Object> params
    );

//    회원가입
    @POST("/guest")
    Call<DefaultResponse> postSignUp(
            @Body HashMap<String, Object> params
    );
}

