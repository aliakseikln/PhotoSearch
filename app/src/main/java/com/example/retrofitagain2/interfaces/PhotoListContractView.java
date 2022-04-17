package com.example.retrofitagain2.interfaces;

import android.graphics.Bitmap;

import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoListContractView {

    void hideProgressBar();

    void showProgressBar();

    void showToast(String toastText);

    void showRecyclerView(List<Photo> listOfPhotos);

    void showFullScreenPhotoActivity(Bitmap bitmap);

    void hideKeyboard();

}
