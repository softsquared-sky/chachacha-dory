package com.example.chachacha_dory;

import java.util.ArrayList;

public interface MenuInterface {
    void validateSuccessMenu(String text, int code, ArrayList<MenuResponse.MenuClass.FoodClass> menus);
    void validateFailureMenu(String message);
}
