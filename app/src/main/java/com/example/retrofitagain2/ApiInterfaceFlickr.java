package com.example.retrofitagain2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceFlickr {

//  Get all
  //  @GET("/services/rest/?method=flickr.photos.search&api_key=8211b5fc0c876e1457d0079e848b5a4f&text=hello&extras=url_s&format=json&nojsoncallback=1")
  //  Call<Matreshka> getAll();

    @GET("/services/rest/?method=flickr.photos.search&api_key=8211b5fc0c876e1457d0079e848b5a4f&text=&sort=relevance&extras=url_s%2C+url_o&format=json&nojsoncallback=1")
    Call<Matreshka> getAllBySearch(@Query("text") String text);

   // @GET("/services/rest/?method=flickr.photos.search&api_key=8211b5fc0c876e1457d0079e848b5a4f&text=&sort=relevance&extras=url_s&format=json&nojsoncallback=1")
  //  Call<Matreshka> getAllBySearch(@Query("text") String text);




}
