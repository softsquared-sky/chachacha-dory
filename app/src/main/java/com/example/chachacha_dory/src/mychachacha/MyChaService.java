package com.example.chachacha_dory.src.mychachacha;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyChaService {
    final MyChaActivityView mMyChaActivityView;

    MyChaService(MyChaActivityView myChaActivityView){
        mMyChaActivityView = myChaActivityView;
    }

    //  16. 마이차차차 조회
    void getMyCha() {
        final MyChaRetrofitInterface myChaRetrofitInterface = getRetrofit().create(MyChaRetrofitInterface.class);
        myChaRetrofitInterface.getMyCha(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<MyChaResponse>() {
            @Override
            public void onResponse(Call<MyChaResponse> call, Response<MyChaResponse> response) {
                final MyChaResponse myChaResponse = response.body();
                if (myChaResponse == null) {
                    mMyChaActivityView.validateFailure("실패");
                    return;
                }
                Log.d("결과", String.valueOf(myChaResponse.getMessage()));
                mMyChaActivityView.validateSuccess(myChaResponse.getMessage(), myChaResponse.isSuccess(), myChaResponse.getStores());
            }

            @Override
            public void onFailure(Call<MyChaResponse> call, Throwable t) {
                mMyChaActivityView.validateFailure("연결 실패");
                Log.d("결과 실패 이유", t.toString()+" ");
            }
        });
    }
}
