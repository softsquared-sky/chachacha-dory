package com.example.chachacha_dory.src.login;

public interface LoginActivityView {
    void validateSuccess(String text, boolean isSuccess, String jwt);
    void validateFailure(String message);
}
