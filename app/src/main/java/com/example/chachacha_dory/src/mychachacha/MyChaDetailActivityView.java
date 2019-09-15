package com.example.chachacha_dory.src.mychachacha;

public interface MyChaDetailActivityView {
    void validateSuccess(String text, boolean isSuccess, MyChaDetailResponse.MyChaDetailResult store);

    void validateFailure(String message);
}
