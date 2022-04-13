package com.example.retrofitagain2;

import android.content.Context;
import android.util.Log;

import com.example.retrofitagain2.interfaces.PhotoListPresenter;
import java.util.ArrayList;
import java.util.List;

public class PhotoListPresenterImpl implements PhotoListPresenter {

    private final PhotosServiceImpl photosServiceImpl = new PhotosServiceImpl();
    List<Photo> downloadedPhotoList = new ArrayList<>();
    private PhotoSearchViewImpl activity;

    public void attachView(PhotoSearchViewImpl photoSearchViewImpl) {
        activity = photoSearchViewImpl;
        photosServiceImpl.photoListPresenterImpl = this;
    }

    public void handleSubmitSearchQuery(String query, String api_key) {
        if (query != null) {
            activity.showProgressBar();
            activity.showToast("Ищем фото по запросу: " + query);
            photosServiceImpl.loadDataOfPhotosByQuery(query,api_key);
        }
    }

    public void handleDownloadButtonClick(Context context, String urlO, String photoTitle) {

        try {
            photosServiceImpl.downloadSelectedPhoto(context, urlO, photoTitle);
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


}
