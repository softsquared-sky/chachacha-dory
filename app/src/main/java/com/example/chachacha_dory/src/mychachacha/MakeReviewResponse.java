package com.example.chachacha_dory.src.mychachacha;

import com.google.gson.annotations.SerializedName;

public class MakeReviewResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
