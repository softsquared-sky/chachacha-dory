package com.example.chachacha_dory.src.detail;

import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyChaSaveService {
    private final MyChaSaveActivityView mMyChaSaveActivityView;

    public MyChaSaveService(final MyChaSaveActivityView myChaSaveActivityView) {
        this.mMyChaSaveActivityView = myChaSaveActivityView;
    }

    public void postMyCha(int storeNum){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("storenum", storeNum);

        final MyChaSaveRetrofitInterface myChaSaveRetrofitInterface = getRetrofit().create(MyChaSaveRetrofitInterface.class);
        myChaSaveRetrofitInterface.postMyCha(sSharedPreferences.getString(USER_ID, ""), hashMap).enqueue(new Callback<MyChaSaveResponse>() {
            @Override
            public void onResponse(Call<MyChaSaveResponse> call, Response<MyChaSaveResponse> response) {
                final MyChaSaveResponse myChaSaveResponse = response.body();
                if (myChaSaveResponse == null) {
                    mMyChaSaveActivityView.validateFailure(myChaSaveResponse.getMessage());
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(myChaSaveResponse.getMessage()));
                mMyChaSaveActivityView.validateSuccess(myChaSaveResponse.getMessage(), myChaSaveResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<MyChaSaveResponse> call, Throwable t) {
                mMyChaSaveActivityView.validateFailure("연결실패");
            }
        });
    }

}
