package com.example.chachacha_dory.src.mypage;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class DeleteMyPageReviewService {
    final DeleteMyPageReviewActivityView mDeleteMyReview;

    DeleteMyPageReviewService(DeleteMyPageReviewActivityView deleteMyPageReviewActivityView){
        mDeleteMyReview = deleteMyPageReviewActivityView;
    }

    void deleteMyReview(int reviewNum){
        final MyPageReviewRetrofitInterface myPageReviewRetrofitInterface = getRetrofit().create(MyPageReviewRetrofitInterface.class);
        myPageReviewRetrofitInterface.deleteMyReview(sSharedPreferences.getString(USER_ID, ""), reviewNum).enqueue(new Callback<DeleteMyPageReviewResponse>() {
            @Override
            public void onResponse(Call<DeleteMyPageReviewResponse> call, Response<DeleteMyPageReviewResponse> response) {
                DeleteMyPageReviewResponse deleteMyPageReviewResponse = response.body();
                if(deleteMyPageReviewResponse==null){
                    mDeleteMyReview.validateFailure("못가져옴");
                    return;
                }
                Log.d("결과", deleteMyPageReviewResponse.getMessage());
                mDeleteMyReview.validateSuccessDeleteReview(deleteMyPageReviewResponse.getMessage(), deleteMyPageReviewResponse.isSuccess());
            }

            @Override
            public void onFailure(Call<DeleteMyPageReviewResponse> call, Throwable t) {
                mDeleteMyReview.validateFailure(t.toString()+"");
            }
        });
    }
}
