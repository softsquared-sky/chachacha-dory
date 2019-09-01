package com.example.chachacha_dory;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.ApplicationClass.sSharedPreferences;

class ServiceReview {
    private final ReviewInterface mReviewInterface;

    ServiceReview(final ReviewInterface reviewInterface){
        this.mReviewInterface = reviewInterface;
    }

    //6. 마이페이지 리뷰
    void getMyReview(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getMyReview(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<ResponseMyReview>() {
            @Override
            public void onResponse(Call<ResponseMyReview> call, Response<ResponseMyReview> response) {
                final ResponseMyReview reviewResponse = response.body();
                if (reviewResponse == null) {
                    mReviewInterface.validateFailure(reviewResponse.getMessage());
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(reviewResponse.getMessage()));
                mReviewInterface.validateReview(reviewResponse.getReviews());
                mReviewInterface.validateSuccess(reviewResponse.getMessage(), reviewResponse.getCode());
            }

            @Override
            public void onFailure(Call<ResponseMyReview> call, Throwable t) {
                mReviewInterface.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }
}
