package com.example.retrofitagain2.photoList;

import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoListServiceListener {

    void onPhotosServiceSuccess(List<Photo> photoListResponse);

}