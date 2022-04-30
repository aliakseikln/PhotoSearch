package com.example.retrofitagain2.searchHistory;

import android.content.Context;

import com.example.retrofitagain2.photoList.PhotoListView;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import javax.inject.Inject;

public class SearchHistoryPresenterImpl implements SearchHistoryPresenter {

    private SearchHistoryView view;
    private final SearchHistoryService searchHistoryService;

    @Inject
    public SearchHistoryPresenterImpl(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    public void attachView(SearchHistoryView view) {
        this.view = view;
    }

    public void handleActivityOnCreate() {
        view.showHistoryItems(searchHistoryService.fetchAllHistory());
    }
}
