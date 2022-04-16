package com.example.retrofitagain2.interfaces;

import com.example.retrofitagain2.Photo;

import java.util.List;

public interface PhotoServiceListener {

    void onPhotosServiceSuccess(List<Photo> photoListResponse);

}