package com.example.chachacha_dory.src.review;

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

        @SerializedName("review")
        ArrayList<Review> reviews;

        public static class Review{
            //술집 리뷰
            @SerializedName("name") String name;
            @SerializedName("text") String text;
            @SerializedName("star") int star;

            public String getName() {
                return name;
            }

            public String getText() {
                return text;
            }

            public int getStar() {
                return star;
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
        }

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

