package com.example.retrofitagain2.photoList;

import android.graphics.Bitmap;

import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class PhotoListPresenterImplTest {

    PhotosServiceImpl photosService = Mockito.mock(PhotosServiceImpl.class);
    SearchHistoryServiceImpl searchHistoryService = Mockito.mock(SearchHistoryServiceImpl.class);
    PhotoListActivity photoListActivity = Mockito.mock(PhotoListActivity.class);
    PhotoListPresenterImpl photoListPresenter = new PhotoListPresenterImpl(photosService, searchHistoryService, photoListActivity);

    @Test
    void TestOnHandleSearchHistoryButtonClick() {
        Assertions.assertNotNull(photoListPresenter);
        photoListPresenter.handleHistoryButtonClick();
        Mockito.verify(photoListActivity, Mockito.times(1)).showSearchHistoryActivity();
    }

    @Test
    void TestOnHandleSearchViewQuery() {
        Assertions.assertNotNull(photoListPresenter);
        photoListPresenter.handleSearchViewQuery(Mockito.anyString());
        Mockito.verify(searchHistoryService, Mockito.times(1)).addHistoryQuery(Mockito.anyString());
        Mockito.verify(photoListActivity, Mockito.times(1)).showProgressBar();
        Mockito.verify(photoListActivity, Mockito.times(1)).showToast(Mockito.anyString());
        Mockito.verify(photosService, Mockito.times(1)).fetchPhotosByQuery(Mockito.anyString(), Mockito.any());
    }

    @Test
    void TestOnHandleDownloadButtonClick() {
        Assertions.assertNotNull(photoListPresenter);
        photoListPresenter.handleDownloadButtonClick(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(photosService, Mockito.times(1)).loadPhotosByQuery(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void TestOnHandleImageButtonClick() {
        Assertions.assertNotNull(photoListPresenter);
        photoListPresenter.handleImageButtonClick(Mockito.mock(Bitmap.class));
        Mockito.verify(photoListActivity, Mockito.times(1)).hideKeyboard();
        Mockito.verify(photoListActivity, Mockito.times(1)).showPhotoDetailsActivity(Mockito.any());
    }
}