package com.example.chachacha_dory;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.chachacha_dory.MainRetrofitInterface;
import com.example.chachacha_dory.DefaultResponse;
import com.example.chachacha_dory.MainActivityView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.chachacha_dory.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.ApplicationClass.sSharedPreferences;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getLogin(final HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getLogin(hashMap).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(defaultResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(defaultResponse.getMessage()));
//                USER_ID = (String) hashMap.get("userid");
                if(defaultResponse.getCode()==113) {
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(X_ACCESS_TOKEN, defaultResponse.getResult().getJwt());
                    editor.putString(USER_ID, (String) hashMap.get("userid"));
                    editor.commit();
//                    X_ACCESS_TOKEN = defaultResponse.getResult().getJwt();
                }
                mMainActivityView.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure("연결실패");
            }
        });
    }

    void postSignUp(HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postSignUp(hashMap).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(defaultResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(defaultResponse.getMessage()));
                mMainActivityView.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure("연결 실패");
            }
        });
    }

    void getMyPage(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getMyPage(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(defaultResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(defaultResponse.getMessage()));
                if(defaultResponse.getResult().getWriting()==null){
                    defaultResponse.getResult().setWriting(".");
                }
                mMainActivityView.validateSuccessMyPage(defaultResponse.getResult());
                mMainActivityView.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure("연결실패");
            }
        });
    }

    void patchMyPage(HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.patchMyPage(sSharedPreferences.getString(USER_ID, ""), hashMap).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(defaultResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(defaultResponse.getMessage()));

//                mMainActivityView.validateSuccessMyPage(defaultResponse.getResult());
                mMainActivityView.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure("연결실패");
            }
        });
    }

//    void getTest() {
//        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
//        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                final DefaultResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mMainActivityView.validateFailure(null);
//                    return;
//                }
//
//                mMainActivityView.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//                mMainActivityView.validateFailure(null);
//            }
//        });
//    }
}
