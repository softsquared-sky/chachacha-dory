package com.example.chachacha_dory.src.search;

import java.util.ArrayList;

public interface SearchFragmentView {
    void validateSuccess(String text, boolean isSuccess, ArrayList<SearchResponse.SearchResult> arrayList);

    void validateFailure(String message);
}
