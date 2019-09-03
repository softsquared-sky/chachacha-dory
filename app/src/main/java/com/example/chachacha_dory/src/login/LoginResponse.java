package com.example.chachacha_dory.src.login;

import com.example.chachacha_dory.src.detail.MyChaSaveResponse;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    LoginResult result;

    public static class LoginResult {
        @SerializedName("jwt")
        String jwt;

        public String getJwt() {
            return jwt;
        }
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

    public LoginResult getResult() {
        return result;
    }
}
