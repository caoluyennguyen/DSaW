package com.nguyenhongphuc98.dsaw.data.network;

public class DataService {

    private static DataService _instance;

    public static DataService Instance() {
        if (_instance == null) {
            _instance = new DataService();
        }
        return _instance;
    }

    public void updateCovidStatistic() {
        new CodvidStatistic().execute("https://api.apify.com/v2/key-value-stores/EaCBL1JNntjR3EakU/records/LATEST?disableRedirect=true");
    }
}
