package com.example.chachacha_dory.src.review;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

class ReviewService {
    private final ReviewActivityView mReviewActivityView;

    ReviewService(final ReviewActivityView reviewActivityView){
        this.mReviewActivityView = reviewActivityView;
    }

    void getReview(int storeNum){
        final ReviewRetrofitInterface reviewRetrofitInterface = getRetrofit().create(ReviewRetrofitInterface.class);
        reviewRetrofitInterface.getStoreReview(storeNum).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                final ReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mReviewActivityView.validateFailure("못가져옴");
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(reviewResponse.getMessage()));
                mReviewActivityView.validateSuccess(reviewResponse.getMessage(), reviewResponse.isSuccess(), reviewResponse.getResult().getReviews());
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                mReviewActivityView.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }
}
