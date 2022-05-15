package com.example.retrofitagain2.di.components;

import com.example.retrofitagain2.di.module.SearchHistoryViewModule;
import com.example.retrofitagain2.searchHistory.SearchHistoryActivity;
import com.example.retrofitagain2.searchHistory.SearchHistoryPresenter;

import dagger.Component;

@Component(modules = SearchHistoryViewModule.class, dependencies = AppComponent.class)
public interface SearchHistoryViewComponent {

    void inject(SearchHistoryActivity searchHistoryActivity);

    SearchHistoryPresenter getSearchHistoryPresenter();
}