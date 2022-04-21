package com.example.retrofitagain2.interfaces;

import android.content.Context;


public interface PhotoListContractService {

    void loadDataOfPhotosByQuery(String query);

    void downloadSelectedPhoto(String urlO, String photoTitle);

}
