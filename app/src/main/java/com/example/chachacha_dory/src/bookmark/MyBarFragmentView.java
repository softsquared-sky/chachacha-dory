package com.example.chachacha_dory.src.bookmark;

import java.util.ArrayList;

public interface MyBarFragmentView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<MyBarResponse.MyBarResult> stores);
    void validateFailure(String message);
}
