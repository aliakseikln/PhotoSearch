package com.example.retrofitagain2.photoList;

import android.graphics.Bitmap;

import com.example.retrofitagain2.Photo;
import com.example.retrofitagain2.services.SearchHistoryService;

import java.util.List;

import dagger.Module;
import dagger.Provides;


public interface PhotoListView {

    void hideProgressBar();

    void showProgressBar();

    void showToast(String toastText);

    void showRecyclerView(List<Photo> listOfPhotos);

    void showPhotoDetailsActivity(Bitmap bitmap);

    void hideKeyboard();

    void showSearchHistoryActivity();
}
