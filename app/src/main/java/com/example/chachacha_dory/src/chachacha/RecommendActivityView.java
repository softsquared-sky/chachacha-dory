package com.example.chachacha_dory.src.chachacha;

import java.util.ArrayList;

public interface RecommendActivityView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<RecommendResponse.RecommendResult> stores);
    void validateFailure(String message);
}
