package com.example.retrofitagain2.searchHistory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.R;

import java.util.ArrayList;

public class SearchHistoryActivity extends AppCompatActivity implements SearchHistoryView {

    RecyclerView recyclerView;
    SearchHistoryRecyclerViewAdapter recyclerViewAdapter;
    SearchHistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        init();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        ArrayList<String> queryHistoryList = (ArrayList<String>) bundle.getSerializable("historyArray");

        presenter.handleActivityOnCreate(queryHistoryList);
    }

    void init() {
        recyclerView = findViewById(R.id.recyclerViewSearchHistory);
        presenter = new SearchHistoryPresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new SearchHistoryRecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void showRecyclerView(ArrayList<String> historyQueriesList) {
        recyclerViewAdapter.updatePhotosListHistory(historyQueriesList);
    }
}