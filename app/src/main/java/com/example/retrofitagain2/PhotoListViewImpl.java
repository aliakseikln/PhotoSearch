package com.example.retrofitagain2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.interfaces.PhotoListContractPresenter;
import com.example.retrofitagain2.interfaces.PhotoListContractView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PhotoListViewImpl extends AppCompatActivity implements PhotoListContractView {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PhotoListRecyclerViewAdapter recyclerViewAdapter;
    SearchView searchView;
    PhotoListContractPresenter presenter;
    Toolbar toolbar;
    ActionBar actionBar;
    Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        setSupportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handleHistoryButtonClick();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.handleSearchViewQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void init() {
        historyButton = findViewById(R.id.historyButton);
        toolbar = findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        presenter = new PhotoListPresenterImpl(this);
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

    public void showPhotoSearchHistoryActivity(List<String> historyPhotoSearchList) {
        Intent intent = new Intent(PhotoListViewImpl.this, PhotoSearchHistoryActivity.class);
        //   intent.putExtra();
        startActivity(intent);
    }
}