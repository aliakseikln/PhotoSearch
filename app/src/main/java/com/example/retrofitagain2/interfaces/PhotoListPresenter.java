package com.example.retrofitagain2.interfaces;

import android.content.Context;
import android.graphics.Bitmap;

public interface PhotoListPresenter  {

    void handleDownloadButtonClick(Context context, String urlO, String photoTitle);

    void handleSubmitSearchQuery(String query);

    void attachView(PhotoListView view);

    void handleImageButtonClick(Bitmap bitmap);

}