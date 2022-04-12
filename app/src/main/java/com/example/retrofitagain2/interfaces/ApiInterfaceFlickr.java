package com.example.retrofitagain2.interfaces;

import com.example.retrofitagain2.Matreshka;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceFlickr {

    @GET("/services/rest/?method=flickr.photos.search&api_key=8211b5fc0c876e1457d0079e848b5a4f&text=&sort=relevance&extras=url_s%2C+url_o&format=json&nojsoncallback=1")
    Call<Matreshka> getAllBySearch(@Query("text") String text);

}
