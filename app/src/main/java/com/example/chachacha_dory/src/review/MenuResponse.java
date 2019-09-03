package com.example.chachacha_dory.src.review;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MenuResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    MenuClass result;

    public static class MenuClass{
        @SerializedName("food")
        ArrayList<FoodClass> foods;

        public static class FoodClass{
            //술집 리뷰
            @SerializedName("menuname") String menuName;
            @SerializedName("menuprice") String menuPrice;

            public String getMenuName() {
                return menuName;
            }

            public void setMenuName(String menuName) {
                this.menuName = menuName;
            }

            public String getMenuPrice() {
                return menuPrice;
            }

            public void setMenuPrice(String menuPrice) {
                this.menuPrice = menuPrice;
            }

        }

        @SerializedName("drink")
        ArrayList<DrinkClass> drinks;

        public static class DrinkClass{

        }

        public ArrayList<FoodClass> getFoods() {
            return foods;
        }

        public ArrayList<DrinkClass> getDrinks() {
            return drinks;
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

    public MenuClass getResult() {
        return result;
    }
}
