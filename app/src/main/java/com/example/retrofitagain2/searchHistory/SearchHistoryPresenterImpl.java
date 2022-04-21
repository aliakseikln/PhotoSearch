package com.example.retrofitagain2.searchHistory;

import android.content.Context;

import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

public class SearchHistoryPresenterImpl implements SearchHistoryPresenter {

    private final SearchHistoryView view;
    private final SearchHistoryService searchHistoryService;
    Context context;

    public SearchHistoryPresenterImpl(SearchHistoryActivity view, Context context) {
        this.view = view;
        this.context = context;
        searchHistoryService = SearchHistoryServiceImpl.getInstance();
    }

    public void handleActivityOnCreate() {
        view.showHistoryItems(searchHistoryService.fetchAllHistory(context));
    }
}
