package com.example.retrofitagain2.searchHistory;

import android.content.Context;

public interface SearchHistoryPresenter {

    void handleActivityOnCreate();

    void attachView(SearchHistoryView view);
}
