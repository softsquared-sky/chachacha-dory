package com.example.chachacha_dory.src.mychachacha;

import com.example.chachacha_dory.src.detail.DetailResponse;
import com.google.gson.annotations.SerializedName;

public class MyChaDetailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    DetailResponse.DetailResult store;

    public DetailResponse.DetailResult getStore() {
        return store;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
