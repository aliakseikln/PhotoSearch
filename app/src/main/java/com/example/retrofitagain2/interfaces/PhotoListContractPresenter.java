package com.example.retrofitagain2.interfaces;

import android.graphics.Bitmap;

public interface PhotoListContractPresenter {

    void handleDownloadButtonClick(String urlO, String photoTitle);

    void handleSearchViewQuery(String query);

    void handleImageButtonClick(Bitmap bitmap);

    void handleHistoryButtonClick();
}