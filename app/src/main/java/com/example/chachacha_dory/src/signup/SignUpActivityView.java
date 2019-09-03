package com.example.chachacha_dory.src.signup;

public interface SignUpActivityView {
    void validateSuccess(String text, boolean isSuccess);
    void validateFailure(String message);
}
