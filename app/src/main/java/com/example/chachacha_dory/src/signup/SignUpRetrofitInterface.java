package com.example.chachacha_dory.src.signup;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    //    1. 회원가입
    @POST("/guest")
    Call<SignUpResponse> postSignUp(
            @Body HashMap<String, Object> params
    );
}
