package com.example.chachacha_dory.src.chachacha;

import com.example.chachacha_dory.src.detail.DetailResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecommendResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<RecommendResult> stores;

    public static class RecommendResult {
        @SerializedName("storenum") int storeNum;
        @SerializedName("storename") String storename;
        @SerializedName("mode") String mood;
        @SerializedName("storewriting") String writing;
        @SerializedName("imageurl") String img;

        public String getStorename() {
            return storename;
        }

        public String getMood() {
            return mood;
        }

        public String getWriting() {
            return writing;
        }

        public String getImg() {
            return img;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public void setWriting(String writing) {
            this.writing = writing;
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
    }

    public ArrayList<RecommendResult> getStores() {
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
