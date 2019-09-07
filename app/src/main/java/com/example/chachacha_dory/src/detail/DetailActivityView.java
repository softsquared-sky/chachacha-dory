package com.example.chachacha_dory.src.detail;

import java.util.ArrayList;

public interface DetailActivityView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<DetailResponse.DetailResult> stores);

    void validateFailure(String message);
}
