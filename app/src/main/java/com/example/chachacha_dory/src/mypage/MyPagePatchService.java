package com.example.chachacha_dory.src.mypage;

import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.USER_ID;
import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class MyPagePatchService {
    private final MyPagePatchActivityView mMyPagePatchActivityView;

    MyPagePatchService(final MyPagePatchActivityView mainInterface) {
        this.mMyPagePatchActivityView = mainInterface;
    }

    //  5. 마이페이지 수정
    void patchMyPage(String name, String writing, String email, String phone){
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("name", name);
        hashMap.put("writing", writing);
        hashMap.put("phone", phone);
        hashMap.put("email", email);

        final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.patchMyPage(sSharedPreferences.getString(USER_ID, ""), hashMap).enqueue(new Callback<MyPagePatchResponse>() {
            @Override
            public void onResponse(Call<MyPagePatchResponse> call, Response<MyPagePatchResponse> response) {
                final MyPagePatchResponse myPageResponse = response.body();
                if (myPageResponse == null) {
                    mMyPagePatchActivityView.validateFailure("못가져옴");
                    Log.d("못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(myPageResponse.getMessage()));
                mMyPagePatchActivityView.validateSuccess(myPageResponse.getMessage(), myPageResponse.isSuccess());
            }

            @Override
            public void onFailure(Call<MyPagePatchResponse> call, Throwable t) {
                mMyPagePatchActivityView.validateFailure("연결실패");
            }
        });
    }
}
