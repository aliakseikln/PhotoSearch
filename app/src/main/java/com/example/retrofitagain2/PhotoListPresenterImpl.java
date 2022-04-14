package com.example.retrofitagain2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.retrofitagain2.interfaces.PhotoListPresenter;
import com.example.retrofitagain2.interfaces.PhotoListService;

import java.util.ArrayList;
import java.util.List;

public class PhotoListPresenterImpl implements PhotoListPresenter {

    private final PhotoListService photosService = new PhotoListServiceImpl();
    List<Photo> downloadedPhotoList = new ArrayList<>();
    private PhotoListActivityImpl activity;

    public void attachView(PhotoListActivityImpl photoListActivity) {
        activity = photoListActivity;
        photosService.setPhotoListService(this);
    }

    public void handleSubmitSearchQuery(String query) {
        if (query != null) {
            activity.showProgressBar();
            activity.showToast("Ищем фото по запросу: " + query);
            photosService.loadDataOfPhotosByQuery(query, activity);
        }
    }

    public void handleDownloadButtonClick(Context context, String urlO, String photoTitle) {

        try {
            photosService.downloadSelectedPhoto(context, urlO, photoTitle);
            activity.showToast("Загрузка изображения началась.");
        } catch (Exception e) {
            Log.e("TAG", "onFailedDownload: " + e.getMessage());
            activity.showToast("Автор не дал разрешения на загрузку фото.");
        }

    }

    public void handleRecyclerView(List<Photo> photoList) {
        downloadedPhotoList.clear();
        downloadedPhotoList.addAll(photoList);
        activity.showToast("Вот что мы нашли по вашему запросу");
        activity.hideProgressBar();
        activity.showRecyclerView(downloadedPhotoList);

    }

    void handleImageButtonClick(Bitmap bitmap) {
        activity.showAnotherActivity(bitmap);
    }


}
