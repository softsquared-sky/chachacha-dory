package com.example.chachacha_dory.src.mychachacha;

public interface MyChaDetailActivityView {
    void validateSuccess(String text, boolean isSuccess, MyChaDetailResponse.MyChaDetail store);

    void validateFailure(String message);
}
