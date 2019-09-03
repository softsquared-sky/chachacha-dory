package com.example.chachacha_dory.src.review;

import java.util.ArrayList;

public interface ReviewActivityView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<ReviewResponse.ReviewResult.Review> reviews);
    void validateFailure(String message);
}
