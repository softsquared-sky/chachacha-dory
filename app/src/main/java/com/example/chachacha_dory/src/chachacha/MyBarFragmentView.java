package com.example.chachacha_dory.src.chachacha;

import java.util.ArrayList;

public interface MyBarFragmentView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<MyBarResponse.MyBarResult> stores);
    void validateFailure(String message);
}
