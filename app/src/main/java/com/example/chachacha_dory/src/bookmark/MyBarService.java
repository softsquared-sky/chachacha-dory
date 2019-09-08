package com.example.chachacha_dory.src.bookmark;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyBarService {
    final MyBarFragmentView mMyBarFragmentView;

    MyBarService(MyBarFragmentView myBarFragmentView){
        mMyBarFragmentView = myBarFragmentView;
    }

    //      즐겨찾기 조회
    void getBookMark() {
        final MyBarRetrofitInterface myBarRetrofitInterface = getRetrofit().create(MyBarRetrofitInterface.class);
        myBarRetrofitInterface.getBookMark(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<MyBarResponse>() {
            @Override
            public void onResponse(Call<MyBarResponse> call, Response<MyBarResponse> response) {
                final MyBarResponse myBarResponse = response.body();
                if ( myBarResponse == null) {
                    mMyBarFragmentView.validateFailure("실패");
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(myBarResponse.getMessage()));
                mMyBarFragmentView.validateSuccess(myBarResponse.getMessage(), myBarResponse.isSuccess(), myBarResponse.getStores());
            }

            @Override
            public void onFailure(Call<MyBarResponse> call, Throwable t) {
                mMyBarFragmentView.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString() + " ");
            }
        });
    }
}
