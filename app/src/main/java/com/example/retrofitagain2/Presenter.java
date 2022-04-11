package com.example.retrofitagain2;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    private final Model model = new Model();
    private MainActivity activity;
    List<Photo> downloadedPhotoList = new ArrayList<>();

    public void attachView(MainActivity MainActivity) {
        activity = MainActivity;
        model.presenter = this;
    }

    void searchViewTrySubmitQuery(String query) {
        if (query != null) {
            activity.showProgressBar();
            activity.showMessage("Ищем фото по запросу: " + query);
            model.loadDataOfPhotosFromFlickrByQuery(query);
        }
    }

    void buttonDownloadHasClicked(Context context, String urlo, String photoTitle) {

        try {
            model.downloadSelectedPhoto(context, urlo, photoTitle);
            activity.showMessage("Image download started.");
        } catch (Exception e) {
            Log.e("TAG", "onFailedDownload: " + e.getMessage());
            activity.showMessage("The author has not given permission to upload this photo.");
        }

    }


    void updateRecyclerView(List<Photo> photoList) {
        downloadedPhotoList.addAll(photoList);
        activity.showMessage("Вот что мы нашли по вашему запросу");
        activity.hideProgressBar();
        activity.showUpdatedRecyclerView(downloadedPhotoList);

    }


}
