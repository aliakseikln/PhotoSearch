package com.example.retrofitagain2.di.module;

import com.example.retrofitagain2.Application;
import com.example.retrofitagain2.photoList.PhotoListActivity;
import com.example.retrofitagain2.photoList.PhotoListPresenter;
import com.example.retrofitagain2.photoList.PhotoListPresenterImpl;
import com.example.retrofitagain2.photoList.PhotoListView;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoListViewModule {

    private final PhotoListView photoListView;

    public PhotoListViewModule(PhotoListActivity photoListView) {
        this.photoListView = photoListView;
    }

    @Provides
    PhotoListView providePhotoListView() {
        return photoListView;
    }

    @Provides
    PhotoListPresenter providePhotoListPresenter(PhotosService photoService, SearchHistoryService searchHistoryService, PhotoListView photoListView) {
        return new PhotoListPresenterImpl(photoService, searchHistoryService, photoListView);
    }
}