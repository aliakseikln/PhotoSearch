package com.example.retrofitagain2.interfaces;

import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoSearchView {


    void hideProgressBar();

    void showProgressBar();

    void showToast(String toastText);

    void showRecyclerView(List<Photo> listOfPhotos);






}
