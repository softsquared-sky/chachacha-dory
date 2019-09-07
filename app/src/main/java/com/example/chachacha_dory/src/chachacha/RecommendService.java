package com.example.chachacha_dory.src.chachacha;

import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class RecommendService {
    final RecommendActivityView mRecommendActivityView;

    RecommendService(RecommendActivityView view) {
        mRecommendActivityView = view;
    }

    //      8. 차차차 추천
    void getRecommend(int people, String kind, String mood) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("people", people);
        hashMap.put("kind", kind);
        hashMap.put("mode", mood);
        hashMap.put("page", 0);
        hashMap.put("size", 5);

        final RecommendChaRetrofitInterface recommendRetrofitInterface = getRetrofit().create(RecommendChaRetrofitInterface.class);
        recommendRetrofitInterface.getRecommend(hashMap).enqueue(new Callback<RecommendResponse>() {
            @Override
            public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                final RecommendResponse recommendResponse = response.body();
                if (recommendResponse == null) {
                    mRecommendActivityView.validateFailure("실패");
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(recommendResponse.getMessage()));
                mRecommendActivityView.validateSuccess(recommendResponse.getMessage(), recommendResponse.isSuccess(), recommendResponse.getStores());
            }

            @Override
            public void onFailure(Call<RecommendResponse> call, Throwable t) {
                mRecommendActivityView.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString() + " ");
            }
        });
    }
}
