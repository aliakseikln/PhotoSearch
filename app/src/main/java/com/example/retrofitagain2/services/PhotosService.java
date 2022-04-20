package com.example.retrofitagain2.services;

public interface PhotosService {

    void loadDataOfPhotosByQuery(String query);

    void downloadSelectedPhoto(String urlO, String photoTitle);

}
