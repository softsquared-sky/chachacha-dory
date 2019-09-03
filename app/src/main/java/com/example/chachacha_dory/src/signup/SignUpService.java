package com.example.chachacha_dory.src.signup;

import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class SignUpService {

    private final SignUpActivityView mSignUpActivityView;

    SignUpService(final SignUpActivityView mainInterface) {
        this.mSignUpActivityView = mainInterface;
    }

    //  1. 회원가입
    void postSignUp(String id, String pw, String name, int age, int gender, String email, String phone){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userid", id);
        hashMap.put("userpw", pw);
        hashMap.put("name", name);
        hashMap.put("age", age);
        hashMap.put("gender", gender);
        hashMap.put("phone", phone);
        hashMap.put("email", email);

        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.postSignUp(hashMap).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.validateFailure("못가져옴");
                    return;
                }
                Log.d("결과 발표", String.valueOf(signUpResponse.getMessage()));
                mSignUpActivityView.validateSuccess(signUpResponse.getMessage(), signUpResponse.isSuccess());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure("연결 실패");
            }
        });
    }

}
