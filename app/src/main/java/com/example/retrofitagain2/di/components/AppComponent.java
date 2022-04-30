package com.example.retrofitagain2.di.components;


import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitagain2.di.module.AppModule;
import com.example.retrofitagain2.photoList.PhotoListActivity;
import com.example.retrofitagain2.photoList.PhotoListView;
import com.example.retrofitagain2.searchHistory.SearchHistoryActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(PhotoListActivity photoListActivity);

    void inject(SearchHistoryActivity searchHistoryActivity);
}
