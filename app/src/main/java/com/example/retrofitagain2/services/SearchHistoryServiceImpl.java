package com.example.retrofitagain2.services;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.retrofitagain2.searchHistory.SearchHistoryPresenterImpl;
import com.example.retrofitagain2.searchHistory.SearchHistoryServiceListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SearchHistoryServiceImpl implements SearchHistoryService{

    SearchHistoryServiceListener listener;
    Context context;
    ArrayList<String> temporaryHistoryList;

    public SearchHistoryServiceImpl(Context context, SearchHistoryPresenterImpl listener) {
        this.context = context;
        this.listener = listener;
    }

    public void createAndUpdateDB(ArrayList<String> queryHistoryList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gsonToGet = new Gson();
        String json = sharedPreferences.getString("historyStrings", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        if ((gsonToGet.fromJson(json, type)) != null) {
            temporaryHistoryList = gsonToGet.fromJson(json, type);
        }
         temporaryHistoryList.addAll(queryHistoryList);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gsonToPut = new Gson();
        json = gsonToPut.toJson(temporaryHistoryList);
        editor.putString("historyStrings", json);
        editor.apply();

        listener.onDBSavedAndUpdated(temporaryHistoryList);
    }
}
