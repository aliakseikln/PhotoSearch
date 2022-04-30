package com.example.retrofitagain2.searchHistory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.App;
import com.example.retrofitagain2.R;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchHistoryActivity extends AppCompatActivity implements SearchHistoryView {

    RecyclerView recyclerView;
    SearchHistoryRecyclerViewAdapter recyclerViewAdapter;
    @Inject
    SearchHistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_search_history);
        init();
        presenter.handleActivityOnCreate();
    }

    void init() {
        presenter.attachView(this);
        recyclerView = findViewById(R.id.recyclerViewSearchHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new SearchHistoryRecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void showHistoryItems(ArrayList<String> historyQueriesList) {
        recyclerViewAdapter.updatePhotosListHistory(historyQueriesList);
    }
}