package com.example.retrofitagain2.interfaces;

import android.app.Activity;
import android.content.Context;

import com.example.retrofitagain2.PhotoListPresenterImpl;


public interface PhotoListService {

    void setPhotoListService(PhotoListPresenter photoListPresenter);

    void loadDataOfPhotosByQuery(String query, Activity context);

    void downloadSelectedPhoto(Context context, String urlO, String photoTitle);

}
