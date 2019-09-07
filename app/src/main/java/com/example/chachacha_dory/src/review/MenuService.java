package com.example.chachacha_dory.src.review;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class MenuService {

    private MenuActivityView mMenuActivityView;

    MenuService(final MenuActivityView menuActivityView) {
        mMenuActivityView = menuActivityView;
    }

    void getStoreMenu(int storeNum) {
        final MenuRetrofitInterface menuRetrofitInterface = getRetrofit().create(MenuRetrofitInterface.class);
        menuRetrofitInterface.getStoreMenu(storeNum).enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                final MenuResponse menuResponse = response.body();
                if (menuResponse == null) {
                    mMenuActivityView.validateFailureMenu("실패");
                    Log.d("결과", "못가져옴");
                    return;
                }
                Log.d("결과", String.valueOf(menuResponse.getMessage()));
                mMenuActivityView.validateSuccessMenu(menuResponse.getMessage(), menuResponse.getCode(), menuResponse.getResult().getFoods());
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                mMenuActivityView.validateFailureMenu("연결 실패");
                Log.d("결과 실패 이유", t.toString() + " ");
            }
        });
    }
}
