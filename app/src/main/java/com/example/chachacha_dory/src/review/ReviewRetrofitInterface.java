package com.example.chachacha_dory.src.review;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReviewRetrofitInterface {
    //      10. 음식점 리뷰 조회
    @GET("/store/{storenum}/review")
    Call<ReviewResponse> getStoreReview(
            @Path("storenum") int storenum
    );
}
