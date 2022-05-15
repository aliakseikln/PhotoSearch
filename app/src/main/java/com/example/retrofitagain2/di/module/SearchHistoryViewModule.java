package com.example.retrofitagain2.di.module;

import com.example.retrofitagain2.searchHistory.SearchHistoryPresenter;
import com.example.retrofitagain2.searchHistory.SearchHistoryPresenterImpl;
import com.example.retrofitagain2.searchHistory.SearchHistoryView;
import com.example.retrofitagain2.services.SearchHistoryService;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchHistoryViewModule {

    private final SearchHistoryView searchHistoryView;

    public SearchHistoryViewModule(SearchHistoryView searchHistoryView) {
        this.searchHistoryView = searchHistoryView;
    }

    @Provides
    SearchHistoryView provideSearchHistoryView() {
        return searchHistoryView;
    }

    @Provides
    SearchHistoryPresenter provideSearchHistoryPresenter(SearchHistoryService searchHistoryService, SearchHistoryView searchHistoryView) {
        return new SearchHistoryPresenterImpl(searchHistoryService, searchHistoryView);
    }
}