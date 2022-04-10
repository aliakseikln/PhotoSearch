package com.example.retrofitagain2;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    private final Model model = new Model();
    private static MainActivity mainActivity;
    List<Photo> downloadedPhotoList = new ArrayList<>();

    public void attachView(MainActivity MainActivity) {
        mainActivity = MainActivity;
    }

    void searchViewTrySubmitQuery(String query) {
        if (query != null) {
            mainActivity.showProgressBar();
            mainActivity.showToast("Ищем фото по запросу: " + query);
            model.loadDataFromFlickrByQuery(query);
        }
    }

    void buttonDownloadHasClicked(Context context, String urlo, String photoTitle) {

        try {
            model.downloadChosedPicture(context, urlo, photoTitle);
            mainActivity.showToast("Image download started.");
        } catch (Exception e) {
            Log.e("TAG", "onFailedDownload: " + e.getMessage());
            mainActivity.showToast("The author has not given permission to upload this photo.");
        }

    }


    void updateRecyclerView(List<Photo> photoList) {
        downloadedPhotoList.addAll(photoList);
        mainActivity.showToast("Вот что мы нашли по вашему запросу");
        mainActivity.hideProgressBar();
        mainActivity.showUpdatedRecyclerView(downloadedPhotoList);

    }


}
