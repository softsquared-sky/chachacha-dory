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
        mainRetrofitInterface.getMyReview(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<MyReviewResponse>() {
            @Override
            public void onResponse(Call<MyReviewResponse> call, Response<MyReviewResponse> response) {
                final MyReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mReviewInterface.validateFailure(reviewResponse.getMessage());
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(reviewResponse.getMessage()));
                mReviewInterface.validateSuccess(reviewResponse.getMessage(), reviewResponse.getCode(), reviewResponse.getReviews());
            }

            @Override
            public void onFailure(Call<MyReviewResponse> call, Throwable t) {
                mReviewInterface.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }

    void getReview(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getStoreReview(1).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                final ReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mReviewInterface.validateFailure(reviewResponse.getMessage());
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(reviewResponse.getMessage()));
                mReviewInterface.validateSuccess(reviewResponse.getMessage(), reviewResponse.getCode(), reviewResponse.getResult().getReviews());
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                mReviewInterface.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }
}
