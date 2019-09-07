package com.example.chachacha_dory.src.mychachacha;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MakeReviewRetrofitInterface {
    @POST("/store/{chaNum}/review")
    Call<MakeReviewResponse> postReview(
            @Path("chaNum") int chaNum,
            @Body HashMap<String, Object> hashMap
    );
}
