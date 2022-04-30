package com.example.retrofitagain2.di.module;

import android.content.Context;

import com.example.retrofitagain2.photoList.PhotoListPresenter;
import com.example.retrofitagain2.photoList.PhotoListPresenterImpl;
import com.example.retrofitagain2.searchHistory.SearchHistoryPresenter;
import com.example.retrofitagain2.searchHistory.SearchHistoryPresenterImpl;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.context;
    }

    @Provides
    @Singleton
    PhotosService providePhotosService() {
        return new PhotosServiceImpl(context);
    }

    @Provides
    @Singleton
    SearchHistoryService provideSearchHistoryService() {
        return new SearchHistoryServiceImpl(context);
    }

    @Provides
    @Singleton
    PhotoListPresenter providePhotoListPresenter(PhotosService photoService, SearchHistoryService searchHistoryService) {
        return new PhotoListPresenterImpl(photoService, searchHistoryService);
    }

    @Provides
    @Singleton
    SearchHistoryPresenter provideSearchHistoryPresenter(SearchHistoryService searchHistoryService) {
        return new SearchHistoryPresenterImpl(searchHistoryService);
    }
}
