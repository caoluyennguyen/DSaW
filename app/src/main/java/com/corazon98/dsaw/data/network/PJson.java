package com.corazon98.dsaw.data.network;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// This class male by nguyenhongphuc98
// Convert buffer reader to json in a simple way
public class PJson {

    private Map<String,String> json;

    public PJson(BufferedReader reader) {

            String line = "";
            json = new HashMap<>();

            try {
                while ((line = reader.readLine()) != null) {

                    if (line.contains(":")) {
                        json.put(line.split("\"")[0],line.split("\"")[2]);
                    }
                }
            } catch (IOException e) {
                Log.d("ERROR", "PJson: fail to parse json");
            }
    }

    public String get(String key) {
        return json.get(key);
    }
}
