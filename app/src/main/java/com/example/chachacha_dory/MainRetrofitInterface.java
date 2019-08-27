package com.example.chachacha_dory;

import com.example.chachacha_dory.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    //    @GET("/test")
    String URL = "http://106.10.50.207";

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
    @FormUrlEncoded
    @HTTP(method = "GET", path = "/user/{userid}", hasBody = true)
    Call<DefaultResponse> getLogin(@Field("userid") String userId,
                                   @Field("userpw") String userPw);

//    @GET("/user/{userid}")
//    Call<DefaultResponse> getLogin(
//            @Path("userId") String userId,
//            @Path("userPw") String userPw
//    );

    @POST("/quest")
    Call<DefaultResponse> postSignUp(
            @Body UserClass params
    );
}

