package com.example.retrofitagain2;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    Model model;
    MainActivity activity;
    String query;
    List<Photo> downloadedPhotoList = new ArrayList<>();

    void searchViewTrySubmitQuery(MainActivity mainActivity) {
        activity = mainActivity;
        model = new Model();
        query = activity.getQueryFromSearchView();
        if (query != null) {
            activity.showProgressBar();
            Toast.makeText(activity, "Ищем фото по запросу" + query, Toast.LENGTH_SHORT).show();
            model.loadDataFromFlickrByQuery(query, activity);
        } else {
            Toast.makeText(activity, "Введите что нибудь для поиска", Toast.LENGTH_SHORT).show();
        }

    }

    void updateRecyclerView(List<Photo> photoList, MainActivity mainActivity) {
        downloadedPhotoList.addAll(photoList);
        Toast.makeText(mainActivity, "Вот что мы нашли по вашему запросу", Toast.LENGTH_SHORT).show();
        mainActivity.hideProgressBar();
        mainActivity.showUpdatedRecyclerView(downloadedPhotoList);

    }


}
