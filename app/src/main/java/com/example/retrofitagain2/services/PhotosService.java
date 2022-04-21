package com.example.retrofitagain2.services;

public interface PhotosService {

    void fetchPhotosByQuery(String query);

    void loadPhotosByQuery(String urlString, String photoTitle);

}
