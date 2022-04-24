package com.example.retrofitagain2.photoList;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

public class PhotoListPresenterImpl implements PhotoListPresenter {

    private static final String TAG = "PhotoListActivity";
    private final PhotoListView view;
    private final PhotosService photosService;
    private final SearchHistoryService searchHistoryService;

    public PhotoListPresenterImpl(PhotoListView view) {
        this.view = view;
        photosService = new PhotosServiceImpl();
        searchHistoryService = SearchHistoryServiceImpl.getInstance();
    }

    public void handleHistoryButtonClick() {
        view.showSearchHistoryActivity(searchHistoryService);
    }

    public void handleSearchViewQuery(String query) {
        if (query != null) {
            searchHistoryService.addHistoryQuery(query);
            view.showProgressBar();
            view.showToast("Ищем фото по запросу: " + query);

            photosService.fetchPhotosByQuery(query, photoListFromResponse -> {
                view.showRecyclerView(photoListFromResponse);
                view.showToast("Вот что мы нашли по вашему запросу");
                view.hideProgressBar();
            });
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

    public void handleImageButtonClick(Bitmap bitmap) {
        view.hideKeyboard();
        view.showPhotoDetailsActivity(bitmap);
    }
}
