package com.example.chachacha_dory.src.mychachacha;

import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class MakeReviewService {
    final MakeReviewActivityView mMakeReviewActivityView;

    MakeReviewService(MakeReviewActivityView makeReviewActivityView){
        mMakeReviewActivityView = makeReviewActivityView;
    }

    void postReview(int chaNum, String text, int star){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("text", text);
        hashMap.put("star", star);

        MakeReviewRetrofitInterface makeReviewRetrofitInterface = getRetrofit().create(MakeReviewRetrofitInterface.class);
        makeReviewRetrofitInterface.postReview(chaNum, hashMap).enqueue(new Callback<MakeReviewResponse>() {
            @Override
            public void onResponse(Call<MakeReviewResponse> call, Response<MakeReviewResponse> response) {
                MakeReviewResponse makeReviewResponse = response.body();
                if(makeReviewResponse==null){
                    mMakeReviewActivityView.validateFailure("실패");
                    return;
                }
                mMakeReviewActivityView.validateSuccess(makeReviewResponse.getMessage(), makeReviewResponse.isSuccess, makeReviewResponse.getCode());
                Log.d("결과", makeReviewResponse.getMessage());
            }

            @Override
            public void onFailure(Call<MakeReviewResponse> call, Throwable t) {
                mMakeReviewActivityView.validateFailure(t.toString());
                Log.d("실패결과", t.toString());
            }
        });
    }
}
