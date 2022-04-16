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

import com.example.retrofitagain2.interfaces.PhotoListPresenter;
import com.example.retrofitagain2.interfaces.PhotoListView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PhotoListViewImpl extends AppCompatActivity implements PhotoListView {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PhotoListRecyclerViewAdapter recyclerViewAdapter;
    SearchView searchView;
    PhotoListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.handleSubmitSearchQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void init() {
        presenter = new PhotoListPresenterImpl();
        presenter.attachView(this);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new PhotoListRecyclerViewAdapter();
        recyclerViewAdapter.presenter = presenter;
        recyclerView.setAdapter(recyclerViewAdapter);
        searchView = findViewById(R.id.searchView);
    }

    @SuppressLint("NotifyDataSetChanged")
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

    public void showFullScreenPhotoActivity(Bitmap bitmap) {

        Intent intent = new Intent(PhotoListViewImpl.this, FullScreenPhotoActivity.class);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        intent.putExtra("image", byteArray);
        startActivity(intent);
    }
}