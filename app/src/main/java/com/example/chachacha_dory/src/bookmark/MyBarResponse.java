package com.example.chachacha_dory.src.bookmark;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyBarResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<MyBarResult> stores;

    public static class MyBarResult {
        @SerializedName("storename")
        String storename;
        @SerializedName("mode")
        String mood;
        @SerializedName("storewriting")
        String writing;
        @SerializedName("imageurl")
        String img;

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public String getWriting() {
            return writing;
        }

        public void setWriting(String writing) {
            this.writing = writing;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

    public ArrayList<MyBarResult> getStores() {
        return stores;
    }

}
