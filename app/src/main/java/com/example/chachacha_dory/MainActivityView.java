package com.example.chachacha_dory;

public interface MainActivityView {

    void validateSuccess(String text, int code);

    void validateFailure(String message);
}
