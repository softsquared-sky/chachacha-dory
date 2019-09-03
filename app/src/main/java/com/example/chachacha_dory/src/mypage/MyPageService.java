package com.example.chachacha_dory.src.mypage;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyPageService {
    private final MyPageActivityView mMyPageActivityView;

    MyPageService(final MyPageActivityView mainInterface) {
        this.mMyPageActivityView = mainInterface;
    }
    //  4. 마이페이지
    void getMyPage(){
        final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.getMyPage(sSharedPreferences.getString(USER_ID, "")).enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {
                final MyPageResponse myPageResponse = response.body();
                if (myPageResponse == null) {
                    mMyPageActivityView.validateFailure("못가져옴");
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(myPageResponse.getMessage()));
                if(myPageResponse.getResult().getWriting()==null){
                    myPageResponse.getResult().setWriting(".");
                }
                mMyPageActivityView.validateSuccess(myPageResponse.getMessage(), myPageResponse.isSuccess(), myPageResponse.getResult());
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                mMyPageActivityView.validateFailure("연결실패");
            }
        });
    }

}
