package com.example.retrofitagain2.interfaces;

import android.app.Activity;
import android.content.Context;

import com.example.retrofitagain2.MainActivity;
import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PresenterInterface {

    void handleRecyclerView(List<Photo> photoList);

    void handleDownloadButtonClick(Context context, String urlO, String photoTitle);

    void handleSubmitSearchQuery(String query, String api_key);

    void attachView(MainActivity mainActivity);

}