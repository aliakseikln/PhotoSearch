package com.example.retrofitagain2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model {

    private static final String TAG = "MainActivity";
    ApiInterfaceFlickr apiInterfaceFlickr;

    List<Photo> photoListFromResponse = new ArrayList<Photo>();
    MainActivity activity;
    Presenter presenter;
    String queryFromSearch;


    void loadDataFromFlickrByQuery(String query, MainActivity mainActivity) {
        queryFromSearch = query;
        activity = mainActivity;
        presenter = new Presenter();
        apiInterfaceFlickr = ApiClientFlickr.getClient().create(ApiInterfaceFlickr.class);

        Call<Matreshka> callGetAll = apiInterfaceFlickr.getAllBySearch(queryFromSearch);
        callGetAll.enqueue(new Callback<Matreshka>() {

            @Override
            public void onResponse(@NonNull Call<Matreshka> call, @NonNull Response<Matreshka> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "onResponse: " + response.body());
                    photoListFromResponse.addAll(response.body().getPhotos().getPhoto());
                    presenter.updateRecyclerView(photoListFromResponse,activity);

                }
            }

            @Override
            public void onFailure(Call<Matreshka> call, Throwable t) {
                Toast.makeText(activity, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }


}
