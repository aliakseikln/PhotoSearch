package com.example.retrofitagain2.interfaces;

import com.example.retrofitagain2.PhotosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceFlickr {

    @GET("/services/rest/?method=flickr.photos.search&api_key=&text=&sort=relevance&extras=url_s%2C+url_o&format=json&nojsoncallback=1")
    Call<PhotosResponse> getAllBySearch(@Query("text") String text, @Query("api_key") String key);

}
