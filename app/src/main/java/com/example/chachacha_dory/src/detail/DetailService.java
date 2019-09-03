package com.example.chachacha_dory.src.detail;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class DetailService {
    private final DetailActivityView mDetailActivityView;

    DetailService(final DetailActivityView detailActivityView) {
        mDetailActivityView = detailActivityView;
    }


    //      상세조회
    void getStoreDetail() {
        final DetailRetrofitInterface detailRetrofitInterface = getRetrofit().create(DetailRetrofitInterface.class);
        detailRetrofitInterface.getStoreDetail(2).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                final DetailResponse detailResponse = response.body();
                if (detailResponse == null) {
                    mDetailActivityView.validateFailure("실패");
                    Log.d("결과", "못가져옴");
                    return;
                }
                Log.d("결과", String.valueOf(detailResponse.getMessage()));
                mDetailActivityView.validateSuccess(detailResponse.getMessage(), detailResponse.isSuccess(), detailResponse.getStores());
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                mDetailActivityView.validateFailure("연결 실패");
                Log.d("결과 실패 이유", t.toString() + " ");
            }
        });
    }

}
