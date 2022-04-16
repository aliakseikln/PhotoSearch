package com.example.retrofitagain2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.retrofitagain2.interfaces.PhotoListActivity;
import com.example.retrofitagain2.interfaces.PhotoListPresenter;
import com.example.retrofitagain2.interfaces.PhotoListService;
import com.example.retrofitagain2.interfaces.PhotoServiceListener;

import java.util.ArrayList;
import java.util.List;

public class PhotoListPresenterImpl implements PhotoListPresenter, PhotoServiceListener {

    private final PhotoListService photosService = new PhotoListServiceImpl();
    List<Photo> downloadedPhotoList = new ArrayList<>();
    private PhotoListActivity view;

    public void attachView(PhotoListActivity photoListActivity) {
        view = photoListActivity;
        photosService.setListener(this);
    }

    public void handleSubmitSearchQuery(String query) {
        if (query != null) {
            view.showProgressBar();
            view.showToast("Ищем фото по запросу: " + query);
            photosService.loadDataOfPhotosByQuery(query, (Activity) view);
        }
    }

    public void handleDownloadButtonClick(Context context, String urlO, String photoTitle) {
        try {
            photosService.downloadSelectedPhoto(context, urlO, photoTitle);
            view.showToast("Загрузка изображения началась.");
        } catch (Exception e) {
            Log.e("TAG", "onFailedDownload: " + e.getMessage());
            view.showToast("Автор не дал разрешения на загрузку фото.");
        }
    }

    public void onPhotosServiceSuccess(List<Photo> photoListResponse) {
        downloadedPhotoList.clear();
        downloadedPhotoList.addAll(photoListResponse);
        view.showToast("Вот что мы нашли по вашему запросу");
        view.hideProgressBar();
        view.showRecyclerView(downloadedPhotoList);
    }

    public void handleImageButtonClick(Bitmap bitmap) {
        view.showFullScreenPhotoActivity(bitmap);
    }
}
