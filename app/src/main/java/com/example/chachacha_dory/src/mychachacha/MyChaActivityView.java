package com.example.chachacha_dory.src.mychachacha;

import java.util.ArrayList;

public interface MyChaActivityView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<MyChaResponse.MyChaResult> stores);
    void validateFailure(String message);
}
