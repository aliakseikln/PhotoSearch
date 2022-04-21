package com.example.retrofitagain2.photoList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.retrofitagain2.Photo;
import com.example.retrofitagain2.services.PhotoListServiceListener;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import java.util.List;

public class PhotoListPresenterImpl implements PhotoListPresenter, PhotoListServiceListener {

    private static final String TAG = "PhotoListActivity";
    private final PhotoListView view;
    private final PhotosService photosService;
    private final SearchHistoryService searchHistoryService;
    Context context;

    public PhotoListPresenterImpl(PhotoListView view, Context context) {
        this.view = view;
        this.context = context;
        photosService = new PhotosServiceImpl(context, this);
        searchHistoryService = SearchHistoryServiceImpl.getInstance();
    }

    public void handleHistoryButtonClick() {
        view.showSearchHistoryActivity(searchHistoryService);
    }

    public void handleSearchViewQuery(String query) {
        if (query != null) {
            searchHistoryService.addHistoryQuery(query,context);
            view.showProgressBar();
            view.showToast("Ищем фото по запросу: " + query);
            photosService.fetchPhotosByQuery(query);
        }
    }

    public void handleDownloadButtonClick(String urlO, String photoTitle) {
        try {
            photosService.loadPhotosByQuery(urlO, photoTitle);
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
        view.showPhotoDetailsActivity(bitmap);
    }
}
