package com.example.chachacha_dory;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.ApplicationClass.sSharedPreferences;

public class ServiceStore {
    private final StoreInstance mStoreInstance;

    ServiceStore(final StoreInstance storeInstance){
        mStoreInstance = storeInstance;
    }

    void getBookMark(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getBookMark(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                final ResponseStore storeResponse = response.body();
                if (storeResponse == null) {
                    mStoreInstance.validateFailure(storeResponse.getMessage());
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(storeResponse.getMessage()));
                mStoreInstance.validateStore(storeResponse.getStores());
                mStoreInstance.validateSuccess(storeResponse.getMessage(), storeResponse.getCode());
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                mStoreInstance.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }
}
