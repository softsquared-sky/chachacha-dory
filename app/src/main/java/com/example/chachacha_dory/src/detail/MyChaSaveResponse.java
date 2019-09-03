package com.example.chachacha_dory.src.detail;

import com.google.gson.annotations.SerializedName;

public class MyChaSaveResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

}