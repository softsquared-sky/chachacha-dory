package com.example.chachacha_dory;

import com.google.gson.annotations.SerializedName;

public class MyPageResponse {

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

    public boolean getIsSuccess() { return isSuccess; }

}
