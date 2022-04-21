package com.example.retrofitagain2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.retrofitagain2.interfaces.PhotoListContractPresenter;
import com.example.retrofitagain2.interfaces.PhotoListContractService;
import com.example.retrofitagain2.interfaces.PhotoListContractView;
import com.example.retrofitagain2.interfaces.PhotoServiceListener;

import java.util.ArrayList;
import java.util.List;

public class PhotoListPresenterImpl implements PhotoListContractPresenter, PhotoServiceListener {

    private static final String TAG = "PhotoListViewImpl";
    private final PhotoListContractView view;
    private final PhotoListContractService photosService;
    List<String> searchPhotoList = new ArrayList<>();

    public PhotoListPresenterImpl(PhotoListContractView photoListActivity) {
        view = photoListActivity;
        photosService = new PhotoListServiceImpl((Context) view, this);
    }

    public void handleHistoryButtonClick() {
        view.showPhotoSearchHistoryActivity(searchPhotoList);
    }

    public void handleSearchViewQuery(String query) {
        if (query != null) {
            searchPhotoList.add(query);
            view.showProgressBar();
            view.showToast("Ищем фото по запросу: " + query);
            photosService.loadDataOfPhotosByQuery(query);
        }
    }

    public void handleDownloadButtonClick(String urlO, String photoTitle) {
        try {
            photosService.downloadSelectedPhoto(urlO, photoTitle);
            view.showToast("Загрузка изображения началась.");
        } catch (Exception e) {
            Log.e(TAG, "onFailedDownload: " + e.getMessage());
            view.showToast("Автор не дал разрешения на загрузку фото.");
        }
    }

    public void onPhotosServiceSuccess(List<Photo> photoListResponse) {
        view.showToast("Вот что мы нашли по вашему запросу");
        view.hideProgressBar();
        view.showRecyclerView(photoListResponse);
    }

    public void handleImageButtonClick(Bitmap bitmap) {
        view.hideKeyboard();
        view.showFullScreenPhotoActivity(bitmap);
    }
}
