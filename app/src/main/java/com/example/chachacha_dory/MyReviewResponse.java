package com.example.chachacha_dory;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyReviewResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<ReviewResponse.ReviewResult.Review> reviews;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public ArrayList<ReviewResponse.ReviewResult.Review> getReviews() {
        return reviews;
    }
}
