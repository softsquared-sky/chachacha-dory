package com.example.chachacha_dory.src.login;

import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView mainInterface) {
        this.mLoginActivityView = mainInterface;
    }

    //  2. 로그인
    void getLogin(String id, String pw){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userid", id);
        hashMap.put("userpw", pw);

        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.getLogin(hashMap).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();

                if (loginResponse == null) {
                    mLoginActivityView.validateFailure(loginResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(loginResponse.getMessage()));
                if(loginResponse.isSuccess()){
                    mLoginActivityView.validateSuccess(loginResponse.getMessage(), loginResponse.isSuccess(), loginResponse.getResult().getJwt());
                }else {
                    mLoginActivityView.validateSuccess(loginResponse.getMessage(), loginResponse.isSuccess(), ".");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure(t.toString()+"");
            }
        });
    }
}
