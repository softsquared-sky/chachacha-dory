package com.example.chachacha_dory;

import java.util.ArrayList;

public interface StoreInterface {
    void validateSuccess(String text, int code, ArrayList<ResponseStore.StoreResult> stores);
    void validateFailure(String message);
}
