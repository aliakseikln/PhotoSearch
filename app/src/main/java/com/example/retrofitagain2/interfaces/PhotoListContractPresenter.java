package com.example.retrofitagain2.interfaces;

import android.graphics.Bitmap;

public interface PhotoListContractPresenter {

    void handleDownloadButtonClick(String urlO, String photoTitle);

    void handleSubmitSearchQuery(String query);

    void attachView(PhotoListContractView view);

    void handleImageButtonClick(Bitmap bitmap);

}