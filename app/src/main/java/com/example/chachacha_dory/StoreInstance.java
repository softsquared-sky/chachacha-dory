package com.example.chachacha_dory;

import java.util.ArrayList;

public interface StoreInstance {
    void validateSuccess(String text, int code);
    void validateStore(ArrayList<ResponseStore.StoreResult> stores);
    void validateFailure(String message);

}
