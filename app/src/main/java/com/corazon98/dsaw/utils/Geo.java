package com.corazon98.dsaw.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Geo {
    public static void getAddressFromLocation(final double latitude, final double longitude, final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        sb.append("[" + latitude + ", " + longitude + "]");
                        for (int i = 0; i < addressList.size(); i++) {
                            sb.append(address.getAddressLine(i)).append(" ");
                        }
                        /*if (address.getThoroughfare() != null) sb.append(address.getThoroughfare()).append(",").append(" ");
                        if (address.getSubAdminArea() != null) sb.append(address.getSubAdminArea()).append(", ").append(" ");
                        if (address.getAdminArea() != null) sb.append(address.getAdminArea()).append(", ").append(" ");
                        if (address.getCountryName() != null) sb.append(address.getCountryName());*/
                        result = sb.toString();
                    }
                } catch (IOException e) {
                    Log.e("Location Address Loader", "Unable connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "[" + latitude + "," + longitude + "] Unable to get address for this location.";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }
}
