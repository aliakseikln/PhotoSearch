package com.example.retrofitagain2.searchHistory;

import com.example.retrofitagain2.services.SearchHistoryService;

import javax.inject.Inject;

public class SearchHistoryPresenterImpl implements SearchHistoryPresenter {

    private final SearchHistoryView view;
    private final SearchHistoryService searchHistoryService;

    @Inject
    public SearchHistoryPresenterImpl(SearchHistoryService searchHistoryService, SearchHistoryView view) {
        this.searchHistoryService = searchHistoryService;
        this.view = view;
    }

    public void handleActivityOnCreate() {
        view.showHistoryItems(searchHistoryService.fetchAllHistory());
    }
}