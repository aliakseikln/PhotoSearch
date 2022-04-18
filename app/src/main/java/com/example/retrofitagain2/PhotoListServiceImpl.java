package com.example.retrofitagain2;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.retrofitagain2.interfaces.ApiInterfaceFlickr;
import com.example.retrofitagain2.interfaces.PhotoListContractService;
import com.example.retrofitagain2.interfaces.PhotoServiceListener;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListServiceImpl implements PhotoListContractService {

    private static final String TAG = "PhotoListViewImpl";
    ApiInterfaceFlickr apiInterfaceFlickr;
    PhotoServiceListener listener;
    Context context;

    public PhotoListServiceImpl(Context context, PhotoServiceListener listener) {
        this.context = context;
        this.listener = listener;
    }


    public void loadDataOfPhotosByQuery(String query) {
        String apiKey = context.getResources().getString(R.string.my_flickr_api_key);
        apiInterfaceFlickr = ApiClientFlickr.getClient().create(ApiInterfaceFlickr.class);
        Call<PhotosResponse> callGetAll = apiInterfaceFlickr.getAllBySearch(query, apiKey);
        callGetAll.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(@NonNull Call<PhotosResponse> call, @NonNull Response<PhotosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "onResponse: " + response.body());
                    List<Photo> photoListFromResponse = response.body().getPhotos().getPhoto();
                    listener.onPhotosServiceSuccess(photoListFromResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }

    public void downloadSelectedPhoto(String urlO, String photoTitle) {
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(urlO);
        DownloadManager.Request request = new DownloadManager.Request((Uri) downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("Photo")
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + "FlickrPhotos" + File.separator + photoTitle + System.currentTimeMillis() + ".jpg");
        dm.enqueue(request);
    }
}
