package com.example.chachacha_dory.src.detail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ArrayList<DetailResult> stores;

    public static class DetailResult {
        @SerializedName("storename") String storename;
        @SerializedName("mode") String mood;
        @SerializedName("storewriting") String writing;
        @SerializedName("address") String addr;
        @SerializedName("opentime") String open;
        @SerializedName("closstime") String close;
        @SerializedName("imageurl") String img;
        @SerializedName("phone") String phone;

        public String getStorename() {
            return storename;
        }

        public String getMood() {
            return mood;
        }

        public String getWriting() {
            return writing;
        }

        public String getAddr() {
            return addr;
        }

        public String getOpen() {
            return open;
        }

        public String getClose() {
            return close;
        }

        public String getImg() {
            return img;
        }

        public String getPhone() {
            return phone;
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

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public void setClose(String close) {
            this.close = close;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }

    public ArrayList<DetailResult> getStores() {
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
