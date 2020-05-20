package com.nguyenhongphuc98.dsaw.data.network;

import android.os.AsyncTask;
import android.util.Log;

import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.PublicData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CodvidStatistic extends AsyncTask<String, String, String> {

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(stream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0)
                return null;

            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //save to firebase here

        JSONObject json = null;
        try {
            json = new JSONObject(result);
            PublicData data = new PublicData("area1","nil",
                    "Viet Nam",json.get("infected").toString(),
                    json.get("deceased").toString(),
                    json.get("recovered").toString(),
                    json.get("lastUpdatedAtSource").toString());
            DataManager.Instance().SaveStatistic(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
