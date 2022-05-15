package com.example.retrofitagain2;

import androidx.annotation.NonNull;

import java.util.List;

public interface PhotosServiceListener {

    void onPhotoServiceSuccess(List<Photo> response);

    void onFailure(@NonNull Throwable throwable);
}
