package com.example.chachacha_dory.src.chachacha;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RecommendChaRetrofitInterface {

    //      8. 차차차 추천
    @POST("/stores/recommend")
    Call<RecommendResponse> getRecommend(
            @Body HashMap<String, Object> hashMap
    );
}
