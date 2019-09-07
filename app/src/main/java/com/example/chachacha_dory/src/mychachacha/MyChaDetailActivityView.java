package com.example.chachacha_dory.src.mychachacha;

import com.example.chachacha_dory.src.detail.DetailResponse;

public interface MyChaDetailActivityView {
    void validateSuccess(String text, boolean isSuccess, DetailResponse.DetailResult store);

    void validateFailure(String message);
}
