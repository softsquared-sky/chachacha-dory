package com.example.chachacha_dory.src.mypage;

import java.util.ArrayList;

public interface MyPageReviewActivityView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<MyPageReviewResponse.MyPageReviewResult> reviews);
    void validateFailure(String message);
}
