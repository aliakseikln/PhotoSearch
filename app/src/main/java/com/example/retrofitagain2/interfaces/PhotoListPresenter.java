package com.example.retrofitagain2.interfaces;

import android.content.Context;

import com.example.retrofitagain2.PhotoListActivityImpl;
import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoListPresenter {

    void handleRecyclerView(List<Photo> photoList);

    void handleDownloadButtonClick(Context context, String urlO, String photoTitle);

    void handleSubmitSearchQuery(String query);

    void attachView(PhotoListActivityImpl photoListActivityImpl);

}