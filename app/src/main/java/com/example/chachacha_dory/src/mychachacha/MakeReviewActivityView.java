package com.example.chachacha_dory.src.mychachacha;

public interface MakeReviewActivityView {
    void validateSuccess(String text, boolean isSuccess, int code);

    void validateFailure(String message);
}
