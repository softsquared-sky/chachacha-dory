package com.example.chachacha_dory.src.bookmark;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chachacha_dory.config.ApplicationClass.getRetrofit;

public class SaveMyBarService {
    final SaveMyBarActivityView mMyBarFragmentView;

    public SaveMyBarService(SaveMyBarActivityView myBarFragmentView) {
        mMyBarFragmentView = myBarFragmentView;
    }

    //      18. 즐겨찾기 저장
    public void saveBookMark(int storeNum) {
        final MyBarRetrofitInterface myBarRetrofitInterface = getRetrofit().create(MyBarRetrofitInterface.class);
        myBarRetrofitInterface.saveBookmark(storeNum).enqueue(new Callback<SaveMyBarResponse>() {
            @Override
            public void onResponse(Call<SaveMyBarResponse> call, Response<SaveMyBarResponse> response) {
                final SaveMyBarResponse myBarResponse = response.body();
                if (myBarResponse == null) {
                    mMyBarFragmentView.validateFailure("실패");
                    Log.d("결과 : 못가져옴", "null");
                    return;
                }
                Log.d("결과 발표", String.valueOf(myBarResponse.getMessage()));
                mMyBarFragmentView.validateSuccess(myBarResponse.getMessage(), myBarResponse.isSuccess());
            }

            @Override
            public void onFailure(Call<SaveMyBarResponse> call, Throwable t) {
                mMyBarFragmentView.validateFailure("연결실패");
                Log.d("결과 왜 실패하니", t.toString() + " ");
            }
        });
    }
}
