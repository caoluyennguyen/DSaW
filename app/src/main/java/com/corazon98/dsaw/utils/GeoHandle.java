package com.corazon98.dsaw.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.RouteData;
import com.corazon98.dsaw.data.model.TrackingStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeoHandle extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        String locationAddress;
        switch (msg.what) {
            case 1:
                Bundle bundle = msg.getData();
                locationAddress = bundle.getString("address");
                break;
            default:
                locationAddress = null;
        }
        Log.e("location Address=", locationAddress);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        Date date = new Date();
        String t = dateFormat.format(date);

        TrackingStatus status = new TrackingStatus(locationAddress,t);
        List<TrackingStatus> l = new ArrayList<>();
        l.add(status);

        try
        {
            RouteData routeData = new RouteData("set later",
                    DataCenter.currentUser.getIdentity(),
                    l,
                    DataCenter.currentUser.getUsername());

            DataManager.Instance().updateRouteData(routeData);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
