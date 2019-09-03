package com.example.chachacha_dory.src.login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    //    3. 로그인
    @POST("/token")
    Call<LoginResponse> getLogin(
            @Body HashMap<String, String> params
    );
}
