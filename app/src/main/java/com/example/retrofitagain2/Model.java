package com.example.retrofitagain2;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model {

    private static final String TAG = "MainActivity";
    ApiInterfaceFlickr apiInterfaceFlickr;
    List<Photo> photoListFromResponse = new ArrayList<Photo>();
    Presenter presenter;

    void loadDataOfPhotosFromFlickrByQuery(String query) {
        apiInterfaceFlickr = ApiClientFlickr.getClient().create(ApiInterfaceFlickr.class);
        Call<Matreshka> callGetAll = apiInterfaceFlickr.getAllBySearch(query);
        callGetAll.enqueue(new Callback<Matreshka>() {

            @Override
            public void onResponse(@NonNull Call<Matreshka> call, @NonNull Response<Matreshka> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "onResponse: " + response.body());
                    photoListFromResponse.clear();
                    photoListFromResponse.addAll(response.body().getPhotos().getPhoto());
                    presenter.updateRecyclerView(photoListFromResponse);

                }
            }

            @Override
            public void onFailure(Call<Matreshka> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });

    }

    void downloadSelectedPhoto(Context context, String urlO, String photoTitle) {

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
