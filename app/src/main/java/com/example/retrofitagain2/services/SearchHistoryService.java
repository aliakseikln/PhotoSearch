package com.example.retrofitagain2.services;

import java.util.ArrayList;

public interface SearchHistoryService {

    void addHistoryQuery(String query);

    ArrayList<String> fetchAllHistory();
}