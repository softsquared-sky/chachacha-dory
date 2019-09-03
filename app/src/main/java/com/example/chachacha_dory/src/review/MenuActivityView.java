package com.example.chachacha_dory.src.review;

import java.util.ArrayList;

public interface MenuActivityView {
    void validateSuccessMenu(String text, int code, ArrayList<MenuResponse.MenuClass.FoodClass> menus);
    void validateFailureMenu(String message);
}
