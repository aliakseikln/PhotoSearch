package com.example.retrofitagain2.di.components;

import com.example.retrofitagain2.di.module.AppModule;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.SearchHistoryService;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {

    PhotosService getPhotosService();

    SearchHistoryService getSearchHistoryService();
}