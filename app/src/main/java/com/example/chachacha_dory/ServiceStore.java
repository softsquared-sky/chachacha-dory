package com.example.chachacha_dory;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.ApplicationClass.sSharedPreferences;

public class ServiceStore {
    private final StoreInterface mStoreInterface;

    ServiceStore(final StoreInterface storeInterface){
        mStoreInterface = storeInterface;
    }

    void getBookMark(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getBookMark(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                final ResponseStore storeResponse = response.body();
                if (storeResponse == null) {
                    mStoreInterface.validateFailure("실패");
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(storeResponse.getMessage()));
                mStoreInterface.validateSuccess(storeResponse.getMessage(), storeResponse.getCode(), storeResponse.getStores());
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                mStoreInterface.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString()+" ");
            }
        });
    }

    void getStoreDetail(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getStoreDetail(1).enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                final ResponseStore responseStore = response.body();
                if(responseStore==null){
                    mStoreInterface.validateFailure("실패");
                    Log.d("결과", "못가져옴");
                    return;
                }
                Log.d("결과", String.valueOf(responseStore.getMessage()));
                mStoreInterface.validateSuccess(responseStore.getMessage(), responseStore.getCode(), responseStore.getStores());
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                mStoreInterface.validateFailure("연결 실패");
                Log.d("결과 실패 이유", t.toString()+" ");
            }
        });
    }
}
