package com.example.chachacha_dory;

public interface MainInterface {

    void validateSuccess(String text, int code);
    void validateSuccessMyPage(MainResponse.Result result);
    void validateFailure(String message);
}
