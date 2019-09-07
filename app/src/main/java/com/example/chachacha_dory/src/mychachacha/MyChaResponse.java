package com.example.chachacha_dory.src.mychachacha;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyChaResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<MyChaResult> stores;

    public static class MyChaResult{
        @SerializedName("storename") String storename;
        @SerializedName("imageurl") String img;
        @SerializedName("chanum") int chaNum;
        @SerializedName("storenum") int storeNum;

        public String getStorename() {
            return storename;
        }

        public String getImg() {
            return img;
        }

        public int getChaNum() {
            return chaNum;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setChaNum(int chaNum) {
            this.chaNum = chaNum;
        }

        public int getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(int storeNum) {
            this.storeNum = storeNum;
        }
    }

    public ArrayList<MyChaResult> getStores() {
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
