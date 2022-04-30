package com.example.retrofitagain2.photoList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.App;
import com.example.retrofitagain2.Photo;
import com.example.retrofitagain2.R;
import com.example.retrofitagain2.photoDetails.PhotoDetailsActivity;
import com.example.retrofitagain2.searchHistory.SearchHistoryActivity;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.inject.Inject;


public class PhotoListActivity extends AppCompatActivity implements PhotoListView {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PhotoListRecyclerViewAdapter recyclerViewAdapter;
    SearchView searchView;
    Toolbar toolbar;
    ActionBar actionBar;
    Button historyButton;
    @Inject
    PhotoListPresenter photoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        init();
        setUpToolbar();

        historyButton.setOnClickListener(v -> photoListPresenter.handleHistoryButtonClick());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                photoListPresenter.handleSearchViewQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void init() {
        photoListPresenter.attachView(this);
        progressBar = findViewById(R.id.progressBar);
        historyButton = findViewById(R.id.historyButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new PhotoListRecyclerViewAdapter();
        recyclerViewAdapter.presenter = photoListPresenter;
        recyclerView.setAdapter(recyclerViewAdapter);
        searchView = findViewById(R.id.searchView);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showRecyclerView(List<Photo> updatedPhotoList) {
        recyclerViewAdapter.updatePhotosList(updatedPhotoList);
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

    public void hideKeyboard() {
        searchView.clearFocus();
    }

    public void showPhotoDetailsActivity(@NonNull Bitmap bitmap) {
        Intent intent = new Intent(PhotoListActivity.this, PhotoDetailsActivity.class);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        intent.putExtra("image", byteArray);
        startActivity(intent);
    }

    public void showSearchHistoryActivity() {
        Intent intent = new Intent(this, SearchHistoryActivity.class);
        startActivity(intent);
    }
}