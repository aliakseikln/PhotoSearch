package com.example.retrofitagain2.interfaces;

import android.content.Context;


public interface PhotoListContractService {

    void setListener(PhotoServiceListener listener);

    void loadDataOfPhotosByQuery(String query);

    void downloadSelectedPhoto(String urlO, String photoTitle);

}
