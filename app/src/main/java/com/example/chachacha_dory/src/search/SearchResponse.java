package com.example.chachacha_dory.src.search;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<SearchResult> stores;

    public static class SearchResult{
        @SerializedName("storename") String storename;
        @SerializedName("imageurl") String img;
        @SerializedName("storenum") int storeNum;
        @SerializedName("mode") String mood;
        @SerializedName("storewriting") String writing;

        public String getStorename() {
            return storename;
        }

        public String getImg() {
            return img;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(int storeNum) {
            this.storeNum = storeNum;
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
    }

    public ArrayList<SearchResult> getStores() {
        return stores;
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
