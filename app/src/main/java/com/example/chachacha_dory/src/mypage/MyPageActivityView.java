package com.example.chachacha_dory.src.mypage;

public interface MyPageActivityView {
    void validateSuccess(String text, boolean isSuccess, MyPageResponse.MyPageResult myPageResult);
    void validateFailure(String message);
}
