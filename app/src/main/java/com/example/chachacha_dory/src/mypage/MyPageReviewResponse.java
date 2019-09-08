package com.example.chachacha_dory.src.mypage;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyPageReviewResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<MyPageReviewResult> reviews;

    public static class MyPageReviewResult {
        @SerializedName("text")
        String text;
        @SerializedName("star")
        int star;
        @SerializedName("storename")
        String storename;
        @SerializedName("address")
        String address;

        public String getText() {
            return text;
        }

        public int getStar() {
            return star;
        }

        public String getStorename() {
            return storename;
        }

        public String getAddress() {
            return address;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public void setAddress(String address) {
            this.address = address;
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

    public ArrayList<MyPageReviewResult> getReviews() {
        return reviews;
    }
}
