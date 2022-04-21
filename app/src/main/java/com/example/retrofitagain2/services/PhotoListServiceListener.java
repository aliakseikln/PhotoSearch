package com.example.retrofitagain2.services;

import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoListServiceListener {

    void onPhotosServiceSuccess(List<Photo> photoListResponse);

}