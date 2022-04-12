package com.example.retrofitagain2.interfaces;

import android.content.Context;

public interface ModelInterface {

    void loadDataOfPhotosByQuery(String query);

    void downloadSelectedPhoto(Context context, String urlO, String photoTitle);

}
