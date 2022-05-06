package com.example.retrofitagain2.di.components;

import com.example.retrofitagain2.di.module.PhotoListViewModule;
import com.example.retrofitagain2.photoList.PhotoListActivity;
import com.example.retrofitagain2.photoList.PhotoListPresenter;

import dagger.Component;

@Component(modules = PhotoListViewModule.class, dependencies = AppComponent.class)
public interface PhotoListViewComponent {

    void inject(PhotoListActivity photoListActivity);

    PhotoListPresenter getPhotoListPresenter();
}