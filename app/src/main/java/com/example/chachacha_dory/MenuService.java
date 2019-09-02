package com.example.chachacha_dory;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.getRetrofit;

public class MenuService {

    private MenuInterface mMenuInterface;

    MenuService(final MenuInterface menuInterface) {
        mMenuInterface = menuInterface;
    }

    void getStoreMenu() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getStoreMenu(1).enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                final MenuResponse menuResponse = response.body();
                if (menuResponse == null) {
                    mMenuInterface.validateFailureMenu("실패");
                    Log.d("결과", "못가져옴");
                    return;
                }
                Log.d("결과", String.valueOf(menuResponse.getMessage()));
                mMenuInterface.validateSuccessMenu(menuResponse.getMessage(), menuResponse.getCode(), menuResponse.getResult().getFoods());
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                mMenuInterface.validateFailureMenu("연결 실패");
                Log.d("결과 실패 이유", t.toString() + " ");
            }
        });
    }
}
