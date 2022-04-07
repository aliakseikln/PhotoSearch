package com.example.retrofitagain2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ApiInterfaceFlickr apiInterfaceFlickr;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PhotoAdapter adapter;
    List<Photo> photoList = new ArrayList<>();
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhotoAdapter(photoList);
        recyclerView.setAdapter(adapter);
        searchView = findViewById(R.id.searchView);

        apiInterfaceFlickr = ApiClientFlickr.getClient().create(ApiInterfaceFlickr.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<Matreshka> callGetAll = apiInterfaceFlickr.getAllBySearch(query);
                progressBar.setVisibility(View.VISIBLE);
                callGetAll.enqueue(new Callback<Matreshka>() {
                    @Override
                    public void onResponse(Call<Matreshka> call, Response<Matreshka> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body());
                            photoList.clear();
                            photoList.addAll(response.body().getPhotos().getPhoto());
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }



                    }

                    @Override
                    public void onFailure(Call<Matreshka> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

}