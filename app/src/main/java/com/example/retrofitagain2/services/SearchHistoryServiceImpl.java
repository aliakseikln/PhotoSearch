package com.example.retrofitagain2.services;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchHistoryServiceImpl implements SearchHistoryService {

    private final Context context;
    private ArrayList<String> temporaryHistoryList;

    @Inject
    public SearchHistoryServiceImpl(Context context) {
        this.context = context;
    }

    public void addHistoryQuery(String query) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gsonToGet = new Gson();
        String json = sharedPreferences.getString("historyStrings", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        if ((gsonToGet.fromJson(json, type)) != null) {
            temporaryHistoryList = gsonToGet.fromJson(json, type);
        }
        temporaryHistoryList.add(query);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gsonToPut = new Gson();
        json = gsonToPut.toJson(temporaryHistoryList);
        editor.putString("historyStrings", json);
        editor.apply();
    }

    public ArrayList<String> fetchAllHistory() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gsonToGet = new Gson();
        String json = sharedPreferences.getString("historyStrings", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> historyQueriesList = gsonToGet.fromJson(json, type);
        if (historyQueriesList != null) {
            return historyQueriesList;
        } else {
            return new ArrayList<>();
        }
    }
}