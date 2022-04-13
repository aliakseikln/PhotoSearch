package com.example.retrofitagain2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.interfaces.PhotoSearchView;

import java.util.ArrayList;
import java.util.List;

public class PhotoSearchViewImpl extends AppCompatActivity implements PhotoSearchView {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PhotoAdapter photoAdapter;
    List<Photo> photoList = new ArrayList<>();
    SearchView searchView;
    PhotoListPresenterImpl photoListPresenterImpl;
    String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                photoListPresenterImpl.handleSubmitSearchQuery(query,apiKey);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

        });
    }

    void init() {
        photoListPresenterImpl = new PhotoListPresenterImpl();
        photoListPresenterImpl.attachView(PhotoSearchViewImpl.this);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        photoAdapter = new PhotoAdapter(photoList);
        photoAdapter.photoListPresenterImpl = photoListPresenterImpl;
        recyclerView.setAdapter(photoAdapter);
        searchView = findViewById(R.id.searchView);
        apiKey = getResources().getString(R.string.my_flickr_api_key);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showRecyclerView(List<Photo> updatedPhotoList) {
        photoList.clear();
        photoList.addAll(updatedPhotoList);
        photoAdapter.notifyDataSetChanged();

    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }


}