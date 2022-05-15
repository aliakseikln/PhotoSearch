package com.example.retrofitagain2.di.module;

import android.content.Context;

import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context applicationContext) {
        this.context = applicationContext;
    }

    @Provides
    PhotosService providePhotosService() {
        return new PhotosServiceImpl(context);
    }

    @Provides
    SearchHistoryService provideSearchHistoryService() {
        return new SearchHistoryServiceImpl(context);
    }
}