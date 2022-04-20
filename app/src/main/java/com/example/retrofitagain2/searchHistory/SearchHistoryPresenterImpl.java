package com.example.retrofitagain2.searchHistory;

import android.content.Context;

import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import java.util.ArrayList;

public class SearchHistoryPresenterImpl implements SearchHistoryPresenter, SearchHistoryServiceListener {

    private final SearchHistoryView view;
    private final SearchHistoryService service;

    public SearchHistoryPresenterImpl(SearchHistoryActivity activity) {
        view = activity;
        service = new SearchHistoryServiceImpl((Context) view, this);
    }

    public void handleActivityOnCreate(ArrayList<String> searchQueriesList) {
        service.createAndUpdateDB(searchQueriesList);
    }

    public void onDBSavedAndUpdated(ArrayList<String> historyQueriesList) {
        view.showRecyclerView(historyQueriesList);
    }
}
