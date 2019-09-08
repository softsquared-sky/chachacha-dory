package com.example.chachacha_dory.src.mypage;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyPageReviewService {
    final MyPageReviewActivityView mMyPageReviewActivityView;

    MyPageReviewService(final MyPageReviewActivityView myPageReviewActivityView){
        mMyPageReviewActivityView = myPageReviewActivityView;
    }

    //6. 마이페이지 리뷰
    void getMyReview(){
        final MyPageReviewRetrofitInterface myPageReviewRetrofitInterface = getRetrofit().create(MyPageReviewRetrofitInterface.class);
        myPageReviewRetrofitInterface.getMyReview(sSharedPreferences.getString(USER_ID, ""), 0, 5).enqueue(new Callback<MyPageReviewResponse>() {
            @Override
            public void onResponse(Call<MyPageReviewResponse> call, Response<MyPageReviewResponse> response) {
                final MyPageReviewResponse reviewResponse = response.body();
                if (reviewResponse == null) {
                    mMyPageReviewActivityView.validateFailure("못가져옴");
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(reviewResponse.getMessage()));
                mMyPageReviewActivityView.validateSuccess(reviewResponse.getMessage(), reviewResponse.isSuccess(), reviewResponse.getReviews());
            }

            @Override
            public void onFailure(Call<MyPageReviewResponse> call, Throwable t) {
                mMyPageReviewActivityView.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }
}
