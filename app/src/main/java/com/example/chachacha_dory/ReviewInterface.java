package com.example.chachacha_dory;

import java.util.ArrayList;

public interface ReviewInterface {

    void validateSuccess(String text, int code, ArrayList<ReviewResponse.ReviewResult.Review> reviews);
    void validateFailure(String message);
}
