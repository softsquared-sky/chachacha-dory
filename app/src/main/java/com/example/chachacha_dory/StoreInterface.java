package com.example.chachacha_dory;

import java.util.ArrayList;

public interface StoreInterface {
    void validateSuccess(String text, int code, ArrayList<StoreResponse.StoreResult> stores);
    void validateFailure(String message);
}
