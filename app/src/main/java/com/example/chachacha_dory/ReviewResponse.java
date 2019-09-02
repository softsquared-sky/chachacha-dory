package com.example.chachacha_dory;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    ReviewResult result;

    public static class ReviewResult{
//        @SerializedName("reviewcount") int count;
        @SerializedName("review")
        ArrayList<Review> reviews;

        public static class Review{
            //술집 리뷰
            @SerializedName("name") String name;
            @SerializedName("text") String text;
            @SerializedName("star") int star;

            //마이 리뷰
            @SerializedName("storename") String storename;
            @SerializedName("address") String address;

            public String getName() {
                return name;
            }

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

            public void setName(String name) {
                this.name = name;
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

//        public int getCount() {
//            return count;
//        }

        public ArrayList<Review> getReviews() {
            return reviews;
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

    public ReviewResult getResult() {
        return result;
    }
}

