package com.example.retrofitagain2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PhotoAdapter photoAdapter;
    List<Photo> photoList = new ArrayList<>();
    SearchView searchView;
    Presenter presenter;
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchViewTrySubmitQuery(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

        });
    }

    void init() {
        presenter = new Presenter();
        presenter.attachView(MainActivity.this);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        photoAdapter = new PhotoAdapter(photoList);
        photoAdapter.presenter = presenter;
        recyclerView.setAdapter(photoAdapter);
        searchView = findViewById(R.id.searchView);
    }

    void showUpdatedRecyclerView(List<Photo> updatedPhotoList) {
        photoList.clear();
        photoList.addAll(updatedPhotoList);
        photoAdapter.notifyDataSetChanged();

    }

    void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    void showMessage(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }


}