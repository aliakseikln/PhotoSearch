package com.example.retrofitagain2.photoList;

import android.graphics.Bitmap;

public interface PhotoListPresenter {

    void handleDownloadButtonClick(String urlO, String photoTitle);

    void handleSearchViewQuery(String query);

    void handleImageButtonClick(Bitmap bitmap);

    void handleHistoryButtonClick();
}