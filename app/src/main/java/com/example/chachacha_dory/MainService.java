package com.example.chachacha_dory;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.chachacha_dory.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.ApplicationClass.sSharedPreferences;

class MainService {
    private final MainInterface mMainInterface;

    MainService(final MainInterface mainInterface) {
        this.mMainInterface = mainInterface;
    }

    //  2. 로그인
    void getLogin(final HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getLogin(hashMap).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse mainResponse = response.body();

                if (mainResponse == null) {
                    mMainInterface.validateFailure(mainResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(mainResponse.getMessage()));
//                USER_ID = (String) hashMap.get("userid");
                if(mainResponse.getCode()==113) {
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(X_ACCESS_TOKEN, mainResponse.getResult().getJwt());
                    editor.putString(USER_ID, (String) hashMap.get("userid"));
                    editor.commit();
//                    X_ACCESS_TOKEN = mainResponse.getResult().getJwt();
                }
                mMainInterface.validateSuccess(mainResponse.getMessage(), mainResponse.getCode());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainInterface.validateFailure("연결실패");
            }
        });
    }

    //  1. 회원가입
    void postSignUp(HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postSignUp(hashMap).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse mainResponse = response.body();
                if (mainResponse == null) {
                    mMainInterface.validateFailure(mainResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(mainResponse.getMessage()));
                mMainInterface.validateSuccess(mainResponse.getMessage(), mainResponse.getCode());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainInterface.validateFailure("연결 실패");
            }
        });
    }

    //  4. 마이페이지
    void getMyPage(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getMyPage(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse mainResponse = response.body();
                if (mainResponse == null) {
                    mMainInterface.validateFailure(mainResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(mainResponse.getMessage()));
                if(mainResponse.getResult().getWriting()==null){
                    mainResponse.getResult().setWriting(".");
                }
                mMainInterface.validateSuccessMyPage(mainResponse.getResult());
                mMainInterface.validateSuccess(mainResponse.getMessage(), mainResponse.getCode());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainInterface.validateFailure("연결실패");
            }
        });
    }

    //  5. 마이페이지 수정
    void patchMyPage(HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.patchMyPage(sSharedPreferences.getString(USER_ID, ""), hashMap).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse mainResponse = response.body();
                if (mainResponse == null) {
                    mMainInterface.validateFailure(mainResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(mainResponse.getMessage()));

//                mMainInterface.validateSuccessMyPage(mainResponse.getResult());
                mMainInterface.validateSuccess(mainResponse.getMessage(), mainResponse.getCode());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainInterface.validateFailure("연결실패");
            }
        });
    }

    void postMyCha(HashMap<String, Object> hashMap){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postMyCha(sSharedPreferences.getString(USER_ID, ""), hashMap).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse mainResponse = response.body();
                if (mainResponse == null) {
                    mMainInterface.validateFailure(mainResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(mainResponse.getMessage()));
                mMainInterface.validateSuccess(mainResponse.getMessage(), mainResponse.getCode());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainInterface.validateFailure("연결실패");
            }
        });
    }

//    void getTest() {
//        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
//        mainRetrofitInterface.getTest().enqueue(new Callback<MainResponse>() {
//            @Override
//            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
//                final MainResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mMainInterface.validateFailure(null);
//                    return;
//                }
//
//                mMainInterface.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
//            }
//
//            @Override
//            public void onFailure(Call<MainResponse> call, Throwable t) {
//                mMainInterface.validateFailure(null);
//            }
//        });
//    }
}
