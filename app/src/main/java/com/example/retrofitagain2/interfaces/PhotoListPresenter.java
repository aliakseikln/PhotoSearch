package com.example.retrofitagain2.interfaces;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.retrofitagain2.PhotoListActivityImpl;
import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoListPresenter  {

    void handleDownloadButtonClick(Context context, String urlO, String photoTitle);

    void handleSubmitSearchQuery(String query);

    void attachView(PhotoListActivity photoListActivityImpl);

    void handleImageButtonClick(Bitmap bitmap);

}