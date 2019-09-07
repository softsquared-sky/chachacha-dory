package com.example.chachacha_dory.src.mychachacha;

import android.util.Log;

import com.example.chachacha_dory.src.detail.DetailActivityView;
import com.example.chachacha_dory.src.detail.DetailResponse;
import com.example.chachacha_dory.src.detail.DetailRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyChaDetailService {

    private final MyChaDetailActivityView mDetailActivityView;

    MyChaDetailService(final MyChaDetailActivityView detailActivityView) {
        mDetailActivityView = detailActivityView;
    }

    //      17. 마이차차차 상세조회
    void getDetailMyCha(int chaNum) {
        final DetailRetrofitInterface detailRetrofitInterface = getRetrofit().create(DetailRetrofitInterface.class);
        detailRetrofitInterface.getDetailMyCha(sSharedPreferences.getString(USER_ID, ""), chaNum).enqueue(new Callback<MyChaDetailResponse>() {
            @Override
            public void onResponse(Call<MyChaDetailResponse> call, Response<MyChaDetailResponse> response) {
                final MyChaDetailResponse detailResponse = response.body();
                if (detailResponse == null) {
                    mDetailActivityView.validateFailure("실패");
                    Log.d("결과", "못가져옴");
                    return;
                }
                Log.d("결과", String.valueOf(detailResponse.getMessage()));
                mDetailActivityView.validateSuccess(detailResponse.getMessage(), detailResponse.isSuccess(), detailResponse.getStore());
            }

            @Override
            public void onFailure(Call<MyChaDetailResponse> call, Throwable t) {
                mDetailActivityView.validateFailure("연결 실패");
                Log.d("결과 실패 이유", t.toString() + " ");
            }
        });
    }
}
