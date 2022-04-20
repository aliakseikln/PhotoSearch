package com.example.retrofitagain2.searchHistory;

import java.util.ArrayList;

public interface SearchHistoryServiceListener {

    void onDBSavedAndUpdated(ArrayList<String> historyQueriesList);

}
