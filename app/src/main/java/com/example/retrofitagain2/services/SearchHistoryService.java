package com.example.retrofitagain2.services;

import android.content.Context;

import java.util.ArrayList;

public interface SearchHistoryService {

    void addHistoryQuery(String query, Context context);

    ArrayList<String> fetchAllHistory(Context context);

}
