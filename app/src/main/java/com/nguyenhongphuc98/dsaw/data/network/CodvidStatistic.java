package com.nguyenhongphuc98.dsaw.data.network;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.PublicData;
import com.nguyenhongphuc98.dsaw.data.model.PublicDataModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CodvidStatistic extends AsyncTask<String, String, String> {

    // area = vn | tg
    String area;

    public CodvidStatistic(String area) {
        this.area = area;
    }

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

        if (result == null)
            return;
        JSONObject json = null;
        try {

            if (area.equals("vn"))
            {
                json = new JSONObject(result);
                PublicData data = new PublicData("vn","nil",
                        "Việt Nam",json.get("infected").toString(),
                        json.get("deceased").toString(),
                        json.get("recovered").toString(),
                        json.get("lastUpdatedAtSource").toString());
                DataManager.Instance().SaveStatistic(data);
            } else {
                //area = tg
                ObjectMapper mapper = new ObjectMapper();
                List<PublicDataModel> publicDatas = mapper.readValue(result, new TypeReference<List<PublicDataModel>>(){});
                int deceased = 0;
                int recovered = 0;
                int infected = 0;
                String lastupdate = "";
                for (PublicDataModel p : publicDatas) {
                    if (p.getDeceased() != null && !p.getDeceased().equals("NA"))
                        deceased = deceased + Integer.parseInt(p.getDeceased());

                    if (p.getRecovered() != null && !p.getRecovered().equals("NA"))
                        recovered = recovered + Integer.parseInt(p.getRecovered());

                    if (p.getInfected() != null && !p.getInfected().equals("NA"))
                        infected = infected + Integer.parseInt(p.getInfected());

                    lastupdate = p.getLastUpdatedApify();
                }

                PublicData data = new PublicData("tg","nil",
                        "Thế Giới",String.valueOf(infected),
                        String.valueOf(deceased),
                        String.valueOf(recovered),
                        lastupdate);
                DataManager.Instance().SaveStatistic(data);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
