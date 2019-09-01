package com.example.chachacha_dory;

import java.util.ArrayList;

public interface ReviewInterface {

    void validateSuccess(String text, int code);
    void validateReview(ArrayList<ResponseReview.ReviewResult.Review> reviews);
    void validateFailure(String message);
}
