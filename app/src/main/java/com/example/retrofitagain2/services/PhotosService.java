package com.example.retrofitagain2.services;

import com.example.retrofitagain2.PhotosServiceListener;

public interface PhotosService {

    void fetchPhotosByQuery(String query, PhotosServiceListener photosServiceListener);

    void loadPhotosByQuery(String urlString, String photoTitle);
}
