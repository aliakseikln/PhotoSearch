package com.example.retrofitagain2.interfaces;

import android.content.Context;


public interface PhotoListService {

    void setListener(PhotoServiceListener listener);

    void loadDataOfPhotosByQuery(String query, Context context);

    void downloadSelectedPhoto(Context context, String urlO, String photoTitle);

}
