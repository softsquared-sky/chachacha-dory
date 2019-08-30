package com.example.chachacha_dory;

public interface MainActivityView {

    void validateSuccess(String text, int code);
    void validateSuccessMyPage(DefaultResponse.Result result);
    void validateFailure(String message);
}
