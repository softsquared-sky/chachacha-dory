package com.example.chachacha_dory;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.getRetrofit;

public class ServiceMenu {

    private StoreMenuInterface mStoreMenuInterface;

    ServiceMenu(final StoreMenuInterface storeMenuInterface){
        mStoreMenuInterface = storeMenuInterface;
    }

    void getStoreMenu(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getStoreMenu(1).enqueue(new Callback<ResponseMenu>() {
            @Override
            public void onResponse(Call<ResponseMenu> call, Response<ResponseMenu> response) {
                final ResponseMenu responseMenu = response.body();
                if(responseMenu==null){
                    mStoreMenuInterface.validateFailureMenu("실패");
                    Log.d("결과", "못가져옴");
                    return;
                }
                Log.d("결과", String.valueOf(responseMenu.getMessage()));
                mStoreMenuInterface.validateSuccessMenu(responseMenu.getMessage(), responseMenu.getCode(), responseMenu.getResult().getFoods());
            }

            @Override
            public void onFailure(Call<ResponseMenu> call, Throwable t) {
                mStoreMenuInterface.validateFailureMenu("연결 실패");
                Log.d("결과 실패 이유", t.toString()+" ");
            }
        });
    }
}
