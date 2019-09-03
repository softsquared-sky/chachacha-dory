package com.example.chachacha_dory.src.review;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuRetrofitInterface {
    //      11. 음식점 메뉴 조회
    @GET("/store/{storenum}/menu")
    Call<MenuResponse> getStoreMenu(
            @Path("storenum") int storenum
    );
}
