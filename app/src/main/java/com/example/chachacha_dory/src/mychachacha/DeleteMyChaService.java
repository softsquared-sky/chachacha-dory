package com.example.chachacha_dory.src.mychachacha;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class DeleteMyChaService {
    final DeleteMyChaActivityView mDeleteMyChaActivityView;

    DeleteMyChaService(DeleteMyChaActivityView deleteMyChaActivityView){
        mDeleteMyChaActivityView = deleteMyChaActivityView;
    }

    //  16. 마이차차차 삭제
    void deleteMyCha(int chaNum) {
        final DeleteMyChaRetrofitInterface myChaRetrofitInterface = getRetrofit().create(DeleteMyChaRetrofitInterface.class);
        myChaRetrofitInterface.deleteMyCha(sSharedPreferences.getString(USER_ID, ""), chaNum).enqueue(new Callback<DeleteMyChaResponse>() {
            @Override
            public void onResponse(Call<DeleteMyChaResponse> call, Response<DeleteMyChaResponse> response) {
                final DeleteMyChaResponse myChaResponse = response.body();
                if (myChaResponse == null) {
                    mDeleteMyChaActivityView.validateFailure("실패");
                    return;
                }
                Log.d("결과", String.valueOf(myChaResponse.getMessage()));
                mDeleteMyChaActivityView.validateSuccess(myChaResponse.getMessage(), myChaResponse.isSuccess());
            }

            @Override
            public void onFailure(Call<DeleteMyChaResponse> call, Throwable t) {
                mDeleteMyChaActivityView.validateFailure("연결 실패");
                Log.d("결과 실패 이유", t.toString()+" ");
            }
        });
    }
}
