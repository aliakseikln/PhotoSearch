package com.example.retrofitagain2.searchHistory;

import android.content.Context;

import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

public class SearchHistoryPresenterImpl implements SearchHistoryPresenter {

    private final SearchHistoryView view;
    private final SearchHistoryService searchHistoryService;

    public SearchHistoryPresenterImpl(SearchHistoryActivity view) {
        this.view = view;
        searchHistoryService = SearchHistoryServiceImpl.getInstance();
    }

    public void handleActivityOnCreate() {
        view.showHistoryItems(searchHistoryService.fetchAllHistory());
    }
}
