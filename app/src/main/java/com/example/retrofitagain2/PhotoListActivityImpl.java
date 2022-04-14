package com.example.retrofitagain2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.interfaces.PhotoListActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoListActivityImpl extends AppCompatActivity implements PhotoListActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Photo> photoList = new ArrayList<>();
    SearchView searchView;
    PhotoListPresenterImpl photoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                photoListPresenter.handleSubmitSearchQuery(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

        });

    }

    void init() {
        photoListPresenter = new PhotoListPresenterImpl();
        photoListPresenter.attachView(PhotoListActivityImpl.this);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(photoList);
        recyclerViewAdapter.photoListPresenter = photoListPresenter;
        recyclerView.setAdapter(recyclerViewAdapter);
        searchView = findViewById(R.id.searchView);

    }

    @SuppressLint("NotifyDataSetChanged")
    public void showRecyclerView(List<Photo> updatedPhotoList) {
        photoList.clear();
        photoList.addAll(updatedPhotoList);
        recyclerViewAdapter.notifyDataSetChanged();

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

    public void showAnotherActivity(Bitmap bitmap) {
        Intent intent = new Intent(PhotoListActivityImpl.this, com.example.retrofitagain2.PhotoActivity.class);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        intent.putExtra("image", byteArray);
        startActivity(intent);

    }

}