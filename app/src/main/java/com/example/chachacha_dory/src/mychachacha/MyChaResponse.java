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
        @SerializedName("phone") String phone;

        public String getStorename() {
            return storename;
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

        public void setImg(String img) {
            this.img = img;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
