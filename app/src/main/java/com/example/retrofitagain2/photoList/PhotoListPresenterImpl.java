package com.example.retrofitagain2.photoList;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.retrofitagain2.Photo;
import com.example.retrofitagain2.PhotosServiceListener;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.PhotosServiceImpl;
import com.example.retrofitagain2.services.SearchHistoryService;
import com.example.retrofitagain2.services.SearchHistoryServiceImpl;

import java.util.List;

import javax.inject.Inject;

public class PhotoListPresenterImpl implements PhotoListPresenter {

    private static final String TAG = "PhotoListActivity";
    private PhotoListView view;
    private final PhotosService photosService;
    private final SearchHistoryService searchHistoryService;

    @Inject
    public PhotoListPresenterImpl(PhotosService photosService, SearchHistoryService searchHistoryService) {
        this.photosService = photosService;
        this.searchHistoryService = searchHistoryService;
    }

    public void attachView(PhotoListView view) {
        this.view = view;
    }

    public void handleHistoryButtonClick() {
        view.showSearchHistoryActivity();
    }

    public void handleSearchViewQuery(String query) {
        if (query != null) {
            searchHistoryService.addHistoryQuery(query);
            view.showProgressBar();
            view.showToast("Ищем фото по запросу: " + query);

            photosService.fetchPhotosByQuery(query, new PhotosServiceListener() {
                        @Override
                        public void onPhotoServiceSuccess(List<Photo> response) {
                            view.showRecyclerView(response);
                            view.showToast("Вот что мы нашли по вашему запросу");
                            view.hideProgressBar();
                        }

                        @Override
                        public void onFailure(@NonNull Throwable t) {
                            view.showToast(t.getMessage());
                            view.hideProgressBar();
                        }
                    }
            );
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
