package com.example.chachacha_dory;

import java.util.ArrayList;

public interface StoreMenuInterface {
    void validateSuccessMenu(String text, int code, ArrayList<ResponseMenu.MenuClass.FoodClass> menus);
    void validateFailureMenu(String message);
}
